package bcsoft.it.glam.service;

import bcsoft.it.glam.dto.VotoDto;
import bcsoft.it.glam.exception.MyException;
import bcsoft.it.glam.model.Post;
import bcsoft.it.glam.model.Voto;
import bcsoft.it.glam.repository.PostRepository;
import bcsoft.it.glam.repository.VotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static bcsoft.it.glam.model.TipoVoto.UP_VOTE;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VotoService {

    private final PostRepository postRepository;
    private final VotoRepository votoRepository;
    private final AuthService authService;



    @Transactional
    public void vota(VotoDto votoDto) {
        Post post = postRepository.findById(votoDto.getPostId())
                .orElseThrow(() -> new MyException("Post Not Found with ID - " ));
        Optional<Voto> voteByPostAndUser = votoRepository.findTopByPostAndUtenteOrderByIdVotoDesc(post, authService.getUtenteCorrente());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getTipoVoto()
                        .equals(votoDto.getTipoVoto())) {
            throw new MyException("hai già votato");
        }
        if (UP_VOTE.equals(votoDto.getTipoVoto())) {
            post.setContatoreVoti(post.getContatoreVoti() + 1);
        } else {
            post.setContatoreVoti(post.getContatoreVoti() - 1);
        }
        votoRepository.save(mapToVoto(votoDto, post));
        postRepository.save(post);
    }

    private Voto mapToVoto(VotoDto voteDto, Post post) {
        return Voto.builder()
                .tipoVoto(voteDto.getTipoVoto())
                .post(post)
                .utente(authService.getUtenteCorrente())
                .build();
    }
}
