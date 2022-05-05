package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyBadge;
import com.wildcodeschool.original_diy.entity.DiyComment;
import com.wildcodeschool.original_diy.entity.DiyRole;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.model.ERole;
import com.wildcodeschool.original_diy.repository.BadgeRepository;
import com.wildcodeschool.original_diy.repository.CommentRepository;
import com.wildcodeschool.original_diy.repository.RoleRepository;
import com.wildcodeschool.original_diy.repository.UserRepository;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import com.wildcodeschool.original_diy.request.EmailRequest;
import com.wildcodeschool.original_diy.request.PasswordRequest;
import com.wildcodeschool.original_diy.request.UserRequest;
import com.wildcodeschool.original_diy.response.MessageResponse;
import com.wildcodeschool.original_diy.service.BadgeService;
import com.wildcodeschool.original_diy.service.DiyUserDetailsService;
import com.wildcodeschool.original_diy.service.MailService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private BadgeService badgeService;

    @Autowired
    private DiyUserDetailsService diyUserDetailsService;

    @Autowired
    private MailService mailService;

    @Value("${angular.url}")
    private String frontUrl;

    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllUsers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        badgeService.badgesVerification();

        try {
            Pageable paging = PageRequest.of(page, size);

            Page<DiyUser> pageUsers;
            pageUsers = userRepository.findAllByOrderByIdDesc(paging);

            List<DiyUser> users = pageUsers.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("users", users);
            response.put("currentPage", pageUsers.getNumber());
            response.put("totalItems", pageUsers.getTotalElements());
            response.put("totalPages", pageUsers.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<DiyUser> getUserById(@PathVariable("id") long id) {
        Optional<DiyUser> user = userRepository.findById(id);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Erreur: L'identifiant existe déjà!"));
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Erreur: L'adresse email existe déjà"));
        }

        try {
            DiyUser user = new DiyUser(
                    userRequest.getUsername(),
                    userRequest.getEmail(),
                    passwordEncoder.encode(userRequest.getPassword())
            );

            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setPhone(userRequest.getPhone());

            Set<String> strRoles = userRequest.getRoles();
            Set<DiyRole> roles = new HashSet<>();

            if (strRoles == null) {
                DiyRole userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé!"));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            DiyRole adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);

                            break;
                        default:
                            DiyRole userRole = roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }

            user.setRoles(roles);

            userRepository.save(user);

            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DiyUser> updateUser(@PathVariable("id") long id, @RequestBody UserRequest userRequest) {
        Optional<DiyUser> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            DiyUser user = userData.get();
            user.setUsername(userRequest.getUsername());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setEmail(userRequest.getEmail());
            user.setPhone(userRequest.getPhone());
            user.setBirthday(userRequest.getBirthday());

            if (!userRequest.getPassword().equals(user.getPassword()) && (!Objects.equals(userRequest.getPassword(), ""))) {
                if (!passwordEncoder.encode(userRequest.getPassword()).equals(user.getPassword())) {
                    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
                }
            }

            Set<String> strRoles = userRequest.getRoles();
            Set<DiyRole> roles = new HashSet<>();

            if (strRoles == null) {
                DiyRole userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé!"));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            DiyRole adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);

                            break;
                        default:
                            DiyRole userRole = roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }

            try {
                Set<DiyBadge> badges = new HashSet<>();
                for (Long badgeId : userRequest.getBadgesSelected()) {
                    DiyBadge badge = badgeRepository.getById(badgeId);
                    badges.add(badge);
                }

                user.setBadges(badges);
            } catch (Exception e) {
                System.out.println(e);
            }

            user.setRoles(roles);

            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        try {
            DiyUser user = userRepository.getById(id);
            List<DiyComment> commentList = user.getComments();
            List<DiyWorkshop> workshopList = user.getWorkshops();

            for (DiyComment comment : commentList
            ) {
                commentRepository.deleteById(comment.getId());
                ;
            }
            for (DiyWorkshop workshop : workshopList
            ) {
                workshopRepository.deleteById(workshop.getId());
            }

            userRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("permitAll()")
    @PutMapping("/recoverPassword")
    public ResponseEntity<?> recoverPassword(@Valid @RequestBody EmailRequest emailRequest) {
        String email = emailRequest.getEmail();
        String token = RandomString.make(45);
        try {

            mailService.updateResetPassword(token, email);
            String resetPasswordLink = frontUrl+"reset-password?token="+token;
            mailService.passwordRecoveryEmail(email, resetPasswordLink);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("permitAll()")
    @PutMapping("/newPassword")
    public ResponseEntity<?> newPassword(@Valid @RequestBody PasswordRequest passwordRequest) {
        if (!passwordRequest.getToken().isEmpty()) {
            DiyUser user = userRepository.findByResetPasswordToken(passwordRequest.getToken());
            mailService.updatePassword(user,passwordRequest.getPassword());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @PutMapping("/contact")
    public ResponseEntity<?> contactFromForm(@Valid @RequestBody EmailRequest emailRequest) throws MessagingException, UnsupportedEncodingException {
        mailService.mailFromContactForm(emailRequest.getEmail(), emailRequest.getMessage());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
