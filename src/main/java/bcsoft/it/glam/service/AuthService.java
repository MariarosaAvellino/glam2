package bcsoft.it.glam.service;

import bcsoft.it.glam.dto.RegisterRequest;
import bcsoft.it.glam.model.Utente;
import bcsoft.it.glam.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

public class AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UtenteRepository utenteRepository;

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




}
