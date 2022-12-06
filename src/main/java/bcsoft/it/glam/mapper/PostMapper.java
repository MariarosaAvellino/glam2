package bcsoft.it.glam.mapper;

import bcsoft.it.glam.dto.PostRequest;
import bcsoft.it.glam.dto.PostResponse;
import bcsoft.it.glam.model.Post;
import bcsoft.it.glam.model.SubReddit;
import bcsoft.it.glam.model.TipoVoto;
import bcsoft.it.glam.model.Utente;
import bcsoft.it.glam.repository.CommentoRepository;
import bcsoft.it.glam.repository.VotoRepository;
import bcsoft.it.glam.service.AuthService;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@AllArgsConstructor
public abstract class PostMapper {

    private final CommentoRepository commentoRepository;
    private final AuthService authService;
    private final VotoRepository votoRepository;
/*
    @Mapping(target = "dataCreazione", expression = "java(java.time.Instant.now() )")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target ="subReddit", source = "subReddit")
    @Mapping(target ="voteCount", constant ="0")
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

    public boolean isPostUpVoted(Post post){}

    public boolean isPostDownVoted(Post post){}

    private boolean checkVoteType(TipoVoto tipoVoto){
        if(){

        }
    }

 */
}
