package kg.bektur.mapper;

import kg.bektur.dto.PostCreateDto;
import kg.bektur.dto.PostDto;
import kg.bektur.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "content", target = "content")
    PostDto postToPostDto(Post post);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "content", target = "content")
    Post postCreateDtoToPost(PostCreateDto postCreateDto);
}
