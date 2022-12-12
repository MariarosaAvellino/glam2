package bcsoft.it.glam.controller;

import bcsoft.it.glam.dto.VotoDto;
import bcsoft.it.glam.service.VotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/voti/")
public class VotoController {
    
    private final VotoService votoService;

    @PostMapping
    public ResponseEntity<Void> voto(@RequestBody VotoDto votoDto) {
        votoService.vota(votoDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
