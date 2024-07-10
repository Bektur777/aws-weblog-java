package kg.bektur.service.impl;

import kg.bektur.dto.PostCreateDto;
import kg.bektur.dto.PostDto;
import kg.bektur.entity.Post;
import kg.bektur.mapper.PostMapper;
import kg.bektur.repository.PostRepository;
import kg.bektur.repository.impl.PostRepositoryImpl;
import kg.bektur.service.PostService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    {
        postRepository = new PostRepositoryImpl();
    }

    @Override
    public List<PostDto> findAll() {
        return postRepository.findAll().stream()
                .map(PostMapper.INSTANCE::postToPostDto)
                .toList();
    }

    @Override
    public PostDto find(String id) {
        Optional<Post> post = postRepository.findById(id);

        if (post.isPresent()) {
            return PostMapper.INSTANCE.postToPostDto(post.get());
        }

        throw new NoSuchElementException("Post with this id=" + id + " not found");
    }

    @Override
    public void create(PostCreateDto postCreateDto) {
        postRepository.save(PostMapper.INSTANCE.postCreateDtoToPost(postCreateDto));
    }

    @Override
    public void delete(String id) {
        postRepository.deleteById(id);
    }
}
