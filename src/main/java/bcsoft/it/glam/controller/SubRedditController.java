package bcsoft.it.glam.controller;

import bcsoft.it.glam.dto.SubRedditDto;
import bcsoft.it.glam.service.SubRedditService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/subreddit")
public class SubRedditController {
    private final SubRedditService subRedditService;

    @PostMapping("/save")
    public ResponseEntity<SubRedditDto> save(@RequestBody SubRedditDto subRedditDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(subRedditService.save(subRedditDto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SubRedditDto>> getAll(){
        return  ResponseEntity.status(HttpStatus.OK).body(subRedditService.getAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<SubRedditDto> getById(@PathVariable long id){
        return  ResponseEntity.status(HttpStatus.OK).body(subRedditService.getById(id));
    }
}
