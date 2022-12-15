package bcsoft.it.glam.controller;

import bcsoft.it.glam.dto.AuthenticationResponse;
import bcsoft.it.glam.dto.LoginRequest;
import bcsoft.it.glam.dto.RefreshTokenRequest;
import bcsoft.it.glam.dto.RegisterRequest;
import bcsoft.it.glam.service.AuthService;
import bcsoft.it.glam.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping ("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup (@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("Registrazione avvenuta.", HttpStatus.CREATED);
    }

    @GetMapping("/verifica/{token}")
    public ResponseEntity<String> verificaAccount(@PathVariable String token) {
        authService.verificaAccount(token);
        return new ResponseEntity<>("Account verificato", HttpStatus.OK);
    }


    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!!");
    }
}
