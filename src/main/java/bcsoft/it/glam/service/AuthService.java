package bcsoft.it.glam.service;

import bcsoft.it.glam.dto.RegisterRequest;
import bcsoft.it.glam.model.Utente;
import bcsoft.it.glam.model.VerificationToken;
import bcsoft.it.glam.repository.UtenteRepository;
import bcsoft.it.glam.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

public class AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Transactional
    public void signUp(RegisterRequest registerRequest){
        Utente user = new Utente();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setCreated(Instant.now());
        user.setEnable(false);
        utenteRepository.save(user);
    }

    private String generateVerificationToken (Utente utente){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUtente(utente);
        verificationToken.setToken(token);
        verificationTokenRepository.save(verificationToken);
        return token;
    }



}
