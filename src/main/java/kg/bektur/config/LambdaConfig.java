package kg.bektur.config;

import kg.bektur.util.DynamoDBOperations;
import software.amazon.awscdk.Duration;
import software.amazon.awscdk.services.iam.Effect;
import software.amazon.awscdk.services.iam.PolicyStatement;
import software.amazon.awscdk.services.iam.PolicyStatementProps;
import software.amazon.awscdk.services.lambda.Architecture;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;

import java.util.List;

public class LambdaConfig {

    private static final String POST_TABLE_NAME = "arn:aws:dynamodb:us-east-1:975050038015:table/posts";

    public static Function deletePostLambdaFunction(Construct scope, String id) {
        return createLambdaFunctionToManipulatingPosts(scope, id,
                "kg.bektur.handler.PostDeleteHandler::handleRequest",
                List.of(DynamoDBOperations.GET_ITEM, DynamoDBOperations.DELETE_ITEM),
                List.of(POST_TABLE_NAME));
    }

    public static Function createPostLambdaFunction(Construct scope, String id) {
        return createLambdaFunctionToManipulatingPosts(scope, id,
                "kg.bektur.handler.PostCreateHandler::handleRequest",
                List.of(DynamoDBOperations.PUT_ITEM),
                List.of(POST_TABLE_NAME));
    }

    public static Function getPostLambdaFunction(Construct scope, String id) {
        return createLambdaFunctionToManipulatingPosts(scope, id,
                "kg.bektur.handler.PostByIdHandler::handleRequest",
                List.of(DynamoDBOperations.GET_ITEM),
                List.of(POST_TABLE_NAME));
    }

    public static Function getPostsLambdaFunction(Construct scope, String id) {
        return createLambdaFunctionToManipulatingPosts(scope, id,
                "kg.bektur.handler.PostListHandler::handleRequest",
                List.of(DynamoDBOperations.SCAN_ITEMS),
                List.of(POST_TABLE_NAME));
    }

    public static Function createLambdaFunctionToManipulatingPosts(Construct scope, String id,
                                                                   String handler,
                                                                   List<String> actions,
                                                                   List<String> resources) {
        Function function = Function.Builder.create(scope, id)
                .runtime(Runtime.JAVA_17)
                .architecture(Architecture.X86_64)
                .handler(handler)
                .code(Code.fromAsset("target/aws-weblog-java-0.1.jar"))
                .timeout(Duration.seconds(20))
                .build();

        function.addToRolePolicy(new PolicyStatement(PolicyStatementProps.builder()
                .effect(Effect.ALLOW)
                .actions(actions)
                .resources(resources)
                .build()));

        return function;
    }
}
