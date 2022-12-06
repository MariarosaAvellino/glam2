package bcsoft.it.glam.service;

import bcsoft.it.glam.dto.CommentoDto;
import bcsoft.it.glam.exception.MyException;
import bcsoft.it.glam.mapper.CommentoMapper;
import bcsoft.it.glam.model.Commento;
import bcsoft.it.glam.model.EmailDiNotifica;
import bcsoft.it.glam.model.Post;
import bcsoft.it.glam.model.Utente;
import bcsoft.it.glam.repository.CommentoRepository;
import bcsoft.it.glam.repository.PostRepository;
import bcsoft.it.glam.repository.UtenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentoService {

    private final CommentoRepository commentoRepository;
    private final PostRepository postRepository;
    private final UtenteRepository utenteRepository;
    private final AuthService authService;
    private final CommentoMapper commentoMapper;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    @Transactional
    public void save(CommentoDto commentoDto) {
        Post post = postRepository.findById(commentoDto.getPostId()).orElseThrow(()->new MyException(("post non trovato con questo id")));
        Commento commento = commentoMapper.mapDtoToCommento(commentoDto, post, authService.getUtenteCorrente());
        commentoRepository.save(commento);
        String message = mailContentBuilder.build(post.getUtente().getUsername() + "ha commentato il tuo post");
        sendNotificaDiCommento(message,post.getUtente());
    }

    @Transactional
    public List<CommentoDto> getAllCommento() {
        return commentoRepository
                .findAll()
                .stream()
                .map(commentoMapper::mapCommentoToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CommentoDto> getAllCommentoByUtente(String username) {
        Utente utente = utenteRepository.findByUsername(username)
                .orElseThrow(() -> new MyException("utente non trovato"));
        return commentoRepository.findAllByUtente(utente)
                .stream()
                .map(commentoMapper::mapCommentoToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CommentoDto> getAllCommentoByPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new MyException("post non trovato"));
        return commentoRepository.findAllByPost(post)
                .stream()
                .map(commentoMapper::mapCommentoToDto).collect(Collectors.toList());
    }

    @Transactional
    public CommentoDto getById(long id ){
        return commentoRepository.findById(id)
                .map(commentoMapper::mapCommentoToDto)
                .orElseThrow(()->new MyException("Commento non trovato con questo id" +id));
    }

    public void sendNotificaDiCommento ( String message, Utente utente){
        mailService.sendMail(new EmailDiNotifica(utente.getUsername() + " ha commentato il tuo post", utente.getEmail(), message));

    }

}
