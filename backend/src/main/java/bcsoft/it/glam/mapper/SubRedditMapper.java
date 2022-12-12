package bcsoft.it.glam.mapper;

import bcsoft.it.glam.dto.SubRedditDto;
import bcsoft.it.glam.model.Post;
import bcsoft.it.glam.model.SubReddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubRedditMapper {

    @Mapping(target = "numeroPost", expression = "java(mapPosts(subReddit.getPosts()))")
    SubRedditDto mapSubRedditToDto(SubReddit subReddit);

    default Integer mapPosts (List<Post> numeroPost){
        return numeroPost.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    SubReddit mapDtoToSubReddit(SubRedditDto subRedditDto);

}
