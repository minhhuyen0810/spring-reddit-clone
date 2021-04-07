package com.company.springredditclone.service;

import com.company.springredditclone.dto.RegisterRequest;
import com.company.springredditclone.exception.SpringRedditException;
import com.company.springredditclone.model.NotificationEmail;
import com.company.springredditclone.model.User;
import com.company.springredditclone.model.VerificationToken;
import com.company.springredditclone.repository.UserRepository;
import com.company.springredditclone.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor

@Transactional
public class AuthService {



    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final MailContentBuilder mailContentBuilder;




    public  void signup(RegisterRequest registerRequest) throws SpringRedditException {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));

    }

    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }


}
