package bcsoft.it.glam.controller;

import bcsoft.it.glam.dto.RegisterRequest;
import bcsoft.it.glam.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/auth")
@AllArgsConstructor
public class AuthController {
    //@Autowired
    private final AuthService authService;

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

}
