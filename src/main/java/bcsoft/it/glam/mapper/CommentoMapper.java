package bcsoft.it.glam.mapper;

import bcsoft.it.glam.dto.CommentoDto;
import bcsoft.it.glam.model.Commento;
import bcsoft.it.glam.model.Post;
import bcsoft.it.glam.model.Utente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentoMapper {

    @Mapping(target= "id", ignore= true)
    @Mapping(target = "testo", source="commentoDto.testo")
    @Mapping(target = "dataCreazione", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target ="utente", source = "utente")
    Commento mapDtoToCommento (CommentoDto commentoDto, Post post, Utente utente);

    @Mapping(target = "postId", expression = "java(commento.getPost().getPostId())")
    @Mapping(target = "username", expression = "java(commento.getUtente().getUsername())")
    CommentoDto mapCommentoToDto(Commento commento);


}
