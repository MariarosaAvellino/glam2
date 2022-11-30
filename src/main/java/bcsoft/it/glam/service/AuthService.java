package bcsoft.it.glam.service;

import bcsoft.it.glam.dto.RegisterRequest;
import bcsoft.it.glam.exception.MyException;
import bcsoft.it.glam.model.EmailDiNotifica;
import bcsoft.it.glam.model.Utente;
import bcsoft.it.glam.model.VerificationToken;
import bcsoft.it.glam.repository.UtenteRepository;
import bcsoft.it.glam.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    VerificationTokenRepository verificationTokenRepository;
    @Autowired
    MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        Utente user = new Utente();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setCreated(Instant.now());
        user.setEnable(false);
        utenteRepository.save(user);
        String token = generateVerificationToken(user);
        mailService.sendMail(new EmailDiNotifica("Per favore attiva il tuo account", user.getEmail(), "Grazie per registrazione "
                + "Click sul link per attivare l'account: " + "http://localhost:8080/api/auth/verifica/" + token));
    }

    private String generateVerificationToken(Utente utente) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUtente(utente);
        verificationToken.setToken(token);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    @Transactional
    public void verificaAccount(String token) {
        Optional<VerificationToken> verificationalToken = verificationTokenRepository.findByToken(token);
        verificationalToken.orElseThrow(
                () -> new MyException("Token non è verificato"));
        trovaUtenteEAbilita(verificationalToken.get());

    }

    @Transactional
    public void trovaUtenteEAbilita(VerificationToken verificationToken) {
        String username = verificationToken.getUtente().getUsername();
        Utente user = utenteRepository.findByUsername((username)).orElseThrow(() -> {
            throw new MyException("errore");
        });
        user.setEnable(true);
        utenteRepository.save(user);
    }

}