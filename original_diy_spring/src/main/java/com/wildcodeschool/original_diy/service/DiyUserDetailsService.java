package com.wildcodeschool.original_diy.service;

import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.model.DiyUserDetails;
import com.wildcodeschool.original_diy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * Pil : Get a fully customized user using UserRepository and create a UserDetails object using the static build method
 */
@Service
public class DiyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Value("${spring.mail.host}")
    private String SMTP;

    @Value("${spring.mail.port}")
    private String port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Override
    @Transactional
    public DiyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DiyUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'identifiant : " + username));

        return DiyUserDetails.build(user);
    }

    public void updateResetPassword(String token, String email) {
        DiyUser user = userRepository.findByEmail(email);

        if (user != null) {
            user.setResetPasswordToken(token);
            user.setTokenDate(new Date());
            userRepository.save(user);
        }
    }


    public void updatePassword(DiyUser user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(newPassword);

        user.setPassword(encodePassword);
        user.setResetPasswordToken("");

        userRepository.save(user);
    }

    public void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("projet.original.diy@gmail.com", "Original DIY");
        helper.setTo(email);
        String subject = "Lien de mot de passe oublié";
        String content = "<p>Bonjour,</p>"
                + "<p>Vous trouverez ci-dessous un lien pour modifier votre mot de passe sur le site OriginalDIY</p>"
                + "<p><b><a href=\"" + resetPasswordLink + "\">Modifier mon mot de passe</a><b></p>"
                + "<p>Ignorez cette email si vous n'etes pas a la demande de cette action</p>";

        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.setHost(SMTP);
        mailSender.setPort(Integer.parseInt(port));
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");


        mailSender.send(message);
    }
}
