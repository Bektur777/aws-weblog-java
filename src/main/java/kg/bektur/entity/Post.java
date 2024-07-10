package kg.bektur.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "posts")
public class Post {

    @DynamoDBHashKey(attributeName = "id")
    private String id;

    @DynamoDBAttribute(attributeName = "title")
    private String title;

    @DynamoDBAttribute(attributeName = "content")
    private String content;
}
