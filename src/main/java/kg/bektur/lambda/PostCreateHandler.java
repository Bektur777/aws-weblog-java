package kg.bektur.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import kg.bektur.dto.PostCreateDto;
import kg.bektur.service.PostService;
import kg.bektur.service.impl.PostServiceImpl;
import kg.bektur.util.ResponseHeaders;

public class PostCreateHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final PostService postService;

    private final Gson gson;

     {
        postService = new PostServiceImpl();
        gson = new Gson();
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {
        LambdaLogger logger = context.getLogger();

        logger.log("Request id: " + context.getAwsRequestId());
        logger.log("Function name: " + context.getFunctionName());
        logger.log("Request body: " + apiGatewayProxyRequestEvent.getBody());

        try {
            postService.create(gson.fromJson(apiGatewayProxyRequestEvent.getBody(), PostCreateDto.class));
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(201)
                    .withHeaders(ResponseHeaders.DEFAULT_POST_HEADERS)
                    .withBody("Successfully created");
        } catch (Exception e) {
            logger.log("ERROR: " + e.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500)
                    .withBody("Error creating post: " + e.getMessage());
        }
     }
}
