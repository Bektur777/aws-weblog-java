package kg.bektur.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import kg.bektur.exception.PostNotFoundException;
import kg.bektur.service.PostService;
import kg.bektur.service.impl.PostServiceImpl;
import kg.bektur.util.ResponseHeaders;

public class PostByIdHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

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
        logger.log("Path parameters: " + apiGatewayProxyRequestEvent.getPathParameters());

        String postId = apiGatewayProxyRequestEvent.getPathParameters().get("postId");

        if (postId == null || postId.isEmpty()) {
            logger.log("Post ID is empty: " + postId);
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500)
                    .withBody("Invalid path parameter: " + postId);
        }

        try {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withHeaders(ResponseHeaders.DEFAULT_GET_HEADERS)
                    .withBody(gson.toJson(postService.find(postId)));
        } catch (PostNotFoundException e) {
            logger.log("WARN: " + e.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(404)
                    .withBody(e.getMessage());
        } catch (Exception e) {
            logger.log("ERROR: " + e.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500)
                    .withBody(e.getMessage());
        }
    }
}
