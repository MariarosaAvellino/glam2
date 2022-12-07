package bcsoft.it.glam.controller;

import bcsoft.it.glam.dto.CommentoDto;
import bcsoft.it.glam.exception.MyException;
import bcsoft.it.glam.model.Utente;
import bcsoft.it.glam.repository.UtenteRepository;
import bcsoft.it.glam.service.CommentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/commenti/")
public class CommentoController {

    private final CommentoService commentoService;

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody CommentoDto commentoDto){
        commentoService.save(commentoDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getAllByUtente/{username}")
    public ResponseEntity<List<CommentoDto>> getAllByUtente(@PathVariable String username){
        return  ResponseEntity.status(HttpStatus.OK).body(commentoService.getAllCommentoByUtente(username));
    }

    @GetMapping("/getAllByPost/{postId}")
    public ResponseEntity<List<CommentoDto>> getAllByUtente(@PathVariable long postId){
        return  ResponseEntity.status(HttpStatus.OK).body(commentoService.getAllCommentoByPost(postId));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CommentoDto> getById(@PathVariable long id){
        return  ResponseEntity.status(HttpStatus.OK).body(commentoService.getById(id));
    }
}
