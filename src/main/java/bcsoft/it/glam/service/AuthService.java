package bcsoft.it.glam.service;

import bcsoft.it.glam.dto.AuthenticationResponse;
import bcsoft.it.glam.dto.LoginRequest;
import bcsoft.it.glam.dto.RefreshTokenRequest;
import bcsoft.it.glam.dto.RegisterRequest;
import bcsoft.it.glam.exception.MyException;
import bcsoft.it.glam.model.EmailDiNotifica;
import bcsoft.it.glam.model.Utente;
import bcsoft.it.glam.model.VerificationToken;
import bcsoft.it.glam.repository.UtenteRepository;
import bcsoft.it.glam.repository.VerificationTokenRepository;
import bcsoft.it.glam.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
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
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    RefreshTokenService refreshTokenService;

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

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();
    }
    //principal è l'utente loggato
    public Utente getUtenteCorrente (){
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return utenteRepository.findByUsername(principal.getSubject()).orElseThrow(()-> new MyException(("utente non trovato")));
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWhitUsername(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

}