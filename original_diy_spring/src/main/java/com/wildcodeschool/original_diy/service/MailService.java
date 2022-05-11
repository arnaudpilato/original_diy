package com.wildcodeschool.original_diy.service;

import com.wildcodeschool.original_diy.entity.DiyComment;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.repository.CommentRepository;
import com.wildcodeschool.original_diy.repository.UserRepository;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import com.wildcodeschool.original_diy.request.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

@Service
public class MailService {
    @Autowired
    private UserRepository userRepository;

    @Value("${spring.mail.host}")
    private String SMTP;

    @Value("${spring.mail.port}")
    private String port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    public void mailSenderProtocol(JavaMailSenderImpl mailSender) {
        mailSender.setHost(SMTP);
        mailSender.setPort(Integer.parseInt(port));
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
    }

    public void updateResetPassword(String token, String email) {
        DiyUser user = userRepository.findByEmail(email);

        if (user != null) {
            user.setResetPasswordToken(token);
            user.setTokenDate(new Date());
            userRepository.save(user);
        }
    }

    public void mailFromContactForm(String email, String body) throws MessagingException, UnsupportedEncodingException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String subject = "Nouveau message de : "+email;

        helper.setFrom("projet.original.diy@gmail.com", "Original DIY");
        helper.setTo("originaldiycreezvous@orange.fr");
        helper.setSubject(subject);
        helper.setText(body, true);

        mailSenderProtocol(mailSender);

        mailSender.send(message);
    }

    public void updatePassword(DiyUser user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(newPassword);

        user.setPassword(encodePassword);
        user.setResetPasswordToken("");

        userRepository.save(user);
    }

    public void passwordRecoveryEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);


        String subject = "Lien de mot de passe oublié";
        String content = "<p>Bonjour,</p>"
                + "<p>Vous trouverez ci-dessous un lien pour modifier votre mot de passe sur le site OriginalDIY</p>"
                + "<p><b><a href=\"" + resetPasswordLink + "\">Modifier mon mot de passe</a><b></p>"
                + "<p>Ignorez cette email si vous n'etes pas a la demande de cette action</p>";

        helper.setFrom("projet.original.diy@gmail.com", "Original DIY");
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSenderProtocol(mailSender);

        mailSender.send(message);
    }

}

