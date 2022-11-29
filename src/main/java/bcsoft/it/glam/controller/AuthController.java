package bcsoft.it.glam.controller;

import bcsoft.it.glam.dto.RegisterRequest;
import bcsoft.it.glam.model.Utente;
import bcsoft.it.glam.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping(value = "/signUp" )
    public ResponseEntity<String> signUp (@RequestBody RegisterRequest registerRequest){

        authService.signUp(registerRequest);
        return new ResponseEntity<>("Registrazione avvenuta.", HttpStatus.CREATED);


    }

}
