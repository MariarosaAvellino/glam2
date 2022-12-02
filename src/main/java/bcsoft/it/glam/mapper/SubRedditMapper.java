package bcsoft.it.glam.mapper;

import bcsoft.it.glam.dto.SubRedditRequest;
import bcsoft.it.glam.model.SubReddit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubRedditMapper {

    @Mapping(target = "numeroPost", expression = "java(mapPosts(subReddit.getPosts()))")
    SubRedditRequest mapSubRedditToDto(SubReddit subReddit);



}
