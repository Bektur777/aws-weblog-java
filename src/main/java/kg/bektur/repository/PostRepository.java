package kg.bektur.repository;

import kg.bektur.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    List<Post> findAll();

    Optional<Post> findById(String id);

    void save(Post post);

    void deleteById(String id);
}
