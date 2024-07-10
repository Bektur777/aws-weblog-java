package kg.bektur.config;

import software.amazon.awscdk.services.apigateway.*;
import software.amazon.awscdk.services.lambda.Function;
import software.constructs.Construct;

import java.util.Collections;

public class ApiGatewayConfig {

    public static RestApi createApiRequest(Construct scope, String id,
                                           Function getPostListLambda,
                                           Function getPostByIdLambda,
                                           Function createPostLambda,
                                           Function deletePostLambda) {
        RestApi api = RestApi.Builder.create(scope, id)
                .defaultCorsPreflightOptions(CorsOptions.builder()
                        .allowOrigins(Cors.ALL_ORIGINS)
                        .allowMethods(Cors.ALL_METHODS)
                        .allowHeaders(Collections.singletonList("*"))
                        .build())
                .description("API запросов для работы с постами")
                .build();

        Resource posts = api.getRoot().addResource("posts");
        LambdaIntegration getPostListLambdaIntegration = LambdaIntegration.Builder.create(getPostListLambda).build();
        posts.addMethod("GET", getPostListLambdaIntegration);

        Resource post = posts.addResource("{postId}");
        LambdaIntegration getPostLambdaIntegration = LambdaIntegration.Builder.create(getPostByIdLambda).build();
        post.addMethod("GET", getPostLambdaIntegration);

        LambdaIntegration createPostLambdaIntegration = LambdaIntegration.Builder.create(createPostLambda).build();
        posts.addMethod("POST", createPostLambdaIntegration);

        LambdaIntegration deletePostLambdaIntegration = LambdaIntegration.Builder.create(deletePostLambda).build();
        post.addMethod("DELETE", deletePostLambdaIntegration);

        return api;
    }
}
