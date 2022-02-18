package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyRole;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.model.ERole;
import com.wildcodeschool.original_diy.repository.RoleRepository;
import com.wildcodeschool.original_diy.repository.UserRepository;
import com.wildcodeschool.original_diy.request.UserRequest;
import com.wildcodeschool.original_diy.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/all")
    public ResponseEntity<List<DiyUser>> getAllUsers() {
        try {
            List<DiyUser> users = new ArrayList<>();
            userRepository.findAll().forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DiyUser> getUserById(@PathVariable("id") long id) {
        Optional<DiyUser> user = userRepository.findById(id);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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

            String role = userRequest.getRole();
            Set<DiyRole> roles = new HashSet<>();

            if (role == null) {
                DiyRole userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé!"));
                roles.add(userRole);
            } else {
                if (role.equals("ROLE_ADMIN")) {
                    DiyRole adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé!"));
                    roles.add(adminRole);
                } else {
                    DiyRole userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé"));
                    roles.add(userRole);
                }
            }

            user.setRoles(roles);

            userRepository.save(user);

            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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

            if (!userRequest.getPassword().equals(user.getPassword()) && (!Objects.equals(userRequest.getPassword(), ""))) {
                if (!passwordEncoder.encode(userRequest.getPassword()).equals(user.getPassword())) {
                    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
                }
            }

            String role = userRequest.getRole();
            Set<DiyRole> roles = new HashSet<>();

            System.out.println(role);

            if (role == null) {
                DiyRole userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé!"));
                roles.add(userRole);
            } else {
                if (role.equals("ROLE_ADMIN")) {
                    DiyRole adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé!"));
                    roles.add(adminRole);
                } else {

                    DiyRole userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé"));
                    roles.add(userRole);
                }
            }

            user.setRoles(roles);

            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        try {
            userRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}