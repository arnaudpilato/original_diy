package com.wildcodeschool.original_diy.service;

import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.model.DiyUserDetails;
import com.wildcodeschool.original_diy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Pil : Get a fully customized user using UserRepository and create a UserDetails object using the static build method
 */
@Service
public class DiyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public DiyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DiyUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'identifiant : " + username));

        return DiyUserDetails.build(user);
    }
}
