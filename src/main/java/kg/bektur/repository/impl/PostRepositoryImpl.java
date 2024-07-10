package kg.bektur.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import kg.bektur.entity.Post;
import kg.bektur.repository.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PostRepositoryImpl implements PostRepository {

    private final AmazonDynamoDB client;

    private final DynamoDBMapper dynamoDB;

    {
        client = AmazonDynamoDBClientBuilder.defaultClient();
        dynamoDB = new DynamoDBMapper(client);
    }

    @Override
    public List<Post> findAll() {
        return dynamoDB.scan(Post.class, new DynamoDBScanExpression());
    }

    @Override
    public Optional<Post> findById(String id) {
        return Optional.of(dynamoDB.load(Post.class, id));
    }

    @Override
    public void save(Post post) {
        post.setId(UUID.randomUUID().toString());
        dynamoDB.save(post);
    }

    @Override
    public void deleteById(String id) {
        try {
            dynamoDB.delete(findById(id));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Post with id " + id + " not found and cannot be deleted");
        }
    }
}
