package kg.bektur;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;
import software.amazon.awscdk.services.apigateway.RestApi;
import software.amazon.awscdk.services.lambda.Function;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

import static kg.bektur.config.ApiGatewayConfig.createApiRequest;
import static kg.bektur.config.LambdaConfig.*;
// import software.amazon.awscdk.Duration;
// import software.amazon.awscdk.services.sqs.Queue;

public class AwsWeblogJavaStack extends Stack {
    public AwsWeblogJavaStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public AwsWeblogJavaStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // The code that defines your stack goes here

        // example resource
        // final Queue queue = Queue.Builder.create(this, "AwsWeblogJavaQueue")
        //         .visibilityTimeout(Duration.seconds(300))
        //         .build();
//        AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
//
//        CreateTableRequest request = new CreateTableRequest()
//                .withTableName("posts")
//                .withAttributeDefinitions(
//                        new AttributeDefinition("id", ScalarAttributeType.S),
//                        new AttributeDefinition("title", ScalarAttributeType.S),
//                        new AttributeDefinition("content", ScalarAttributeType.S)
//                )
//                .withKeySchema(new KeySchemaElement("id", KeyType.HASH))
//                .withProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
//
//        String tableName = ddb.createTable(request).getTableDescription().getTableName();
//
//        System.out.println(tableName);

        Function getPostListLambda = getPostsLambdaFunction(this, "GetPostListLambda");
        Function getPostByIdLambda = getPostLambdaFunction(this, "GetPostByIdLambda");
        Function createPostLambda = createPostLambdaFunction(this, "CreatePostLambda");
        Function deletePostLambda = deletePostLambdaFunction(this, "DeletePostLambda");

        RestApi api = createApiRequest(this, "PostServiceApi", getPostListLambda, getPostByIdLambda,
                createPostLambda, deletePostLambda);
    }
}
