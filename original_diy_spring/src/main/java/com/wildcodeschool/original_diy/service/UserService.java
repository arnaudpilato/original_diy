package com.wildcodeschool.original_diy.service;

import com.wildcodeschool.original_diy.entity.*;
import com.wildcodeschool.original_diy.model.ERole;
import com.wildcodeschool.original_diy.repository.*;
import com.wildcodeschool.original_diy.request.EmailRequest;
import com.wildcodeschool.original_diy.request.PasswordRequest;
import com.wildcodeschool.original_diy.request.UserRequest;
import com.wildcodeschool.original_diy.response.MessageResponse;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    WorkshopRepository workshopRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MailService mailService;

    @Value("${angular.url}")
    private String frontUrl;

    public Map<String, Object> getAllUsers(int page, int size) {
        Pageable paging = PageRequest.of(page, size);

        Page<DiyUser> pageUsers;
        pageUsers = userRepository.findAllByOrderByIdDesc(paging);

        List<DiyUser> users = pageUsers.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("users", users);
        response.put("currentPage", pageUsers.getNumber());
        response.put("totalItems", pageUsers.getTotalElements());
        response.put("totalPages", pageUsers.getTotalPages());

        return response;
    }

    public DiyUser createUser(@Valid @RequestBody UserRequest userRequest) {
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

        return user;
    }

    public DiyUser getUserById(long id) {
        Optional<DiyUser> user = userRepository.findById(id);

        return user.get();
    }

    public DiyUser updateUser(long id, @RequestBody UserRequest userRequest) {
        Optional<DiyUser> userData = userRepository.findById(id);
        DiyUser user = userData.get();

        if (userData.isPresent()) {
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
                        .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé !"));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            DiyRole adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé !"));
                            roles.add(adminRole);

                            break;
                        default:
                            DiyRole userRole = roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé !"));
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

            userRepository.save(user);
        }

        return user;
    }

    public void deleteUser(Long id) {
        DiyUser user = userRepository.getById(id);
        List<DiyComment> commentList = user.getComments();
        List<DiyWorkshop> workshopList = user.getWorkshops();

        for (DiyComment comment : commentList) {
            commentRepository.deleteById(comment.getId());
        }

        for (DiyWorkshop workshop : workshopList) {
            workshopRepository.deleteById(workshop.getId());
        }

        userRepository.deleteById(id);
    }

    public void recoverPassword(@Valid @RequestBody EmailRequest emailRequest) {
        String email = emailRequest.getEmail();
        String token = RandomString.make(45);

        try {
            mailService.updateResetPassword(token, email);
            String resetPasswordLink = frontUrl+"reset-password?token="+token;

            mailService.passwordRecoveryEmail(email, resetPasswordLink);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void newPassword(@Valid @RequestBody PasswordRequest passwordRequest) {
        if (!passwordRequest.getToken().isEmpty()) {
            DiyUser user = userRepository.findByResetPasswordToken(passwordRequest.getToken());
            mailService.updatePassword(user,passwordRequest.getPassword());
        }
    }
}
