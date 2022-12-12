package bcsoft.it.glam.mapper;

import bcsoft.it.glam.dto.PostRequest;
import bcsoft.it.glam.dto.PostResponse;
import bcsoft.it.glam.model.*;
import static bcsoft.it.glam.model.TipoVoto.DOWN_VOTE;
import static bcsoft.it.glam.model.TipoVoto.UP_VOTE;
import bcsoft.it.glam.repository.CommentoRepository;
import bcsoft.it.glam.repository.VotoRepository;
import bcsoft.it.glam.service.AuthService;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private  CommentoRepository commentoRepository;
    @Autowired
    private  AuthService authService;
    @Autowired
    private  VotoRepository votoRepository;

    @Mapping(target = "dataCreazione", expression = "java(java.time.Instant.now() )")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target ="subReddit", source = "subReddit")
    @Mapping(target ="contatoreVoti", constant ="0")
    @Mapping(target ="utente", source ="utente")
    public abstract Post map(PostRequest postRequest, SubReddit subReddit, Utente utente);

    @Mapping(target ="id", source ="postId")
    @Mapping(target="subRedditName", source ="subReddit.nome")
    @Mapping(target="username", source="utente.username")
    @Mapping(target="commentCount", expression = "java(commentCount(post))")
    @Mapping(target="duration", expression = "java(getDuration(post))")
    @Mapping(target="upVote", expression = "java(isPostUpVoted(post))")
    @Mapping(target="downVote", expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapToDTO(Post post);

    public Integer commentCount(Post post){
        return commentoRepository.findAllByPost(post).size();
    }

    public String getDuration(Post post){
        return TimeAgo.using(post.getDataCreazione().toEpochMilli());
    }

    public boolean isPostUpVoted(Post post){
        return checkVoteType(post, UP_VOTE);}

    public boolean isPostDownVoted(Post post){
        return checkVoteType(post, DOWN_VOTE);}
    

    private boolean checkVoteType(Post post, TipoVoto tipoVoto) {
        if (authService.isLoggedIn()) {
            Optional<Voto> votoPostByUtente =
                    votoRepository.findTopByPostAndUtenteOrderByIdVotoDesc(post,
                            authService.getUtenteCorrente());
            return votoPostByUtente.filter(voto -> voto.getTipoVoto().equals(tipoVoto))
                    .isPresent();
        }
        return false;
    }


}
