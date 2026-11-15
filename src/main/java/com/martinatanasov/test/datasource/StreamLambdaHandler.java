package com.martinatanasov.test.datasource;


import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class StreamLambdaHandler implements RequestStreamHandler {
    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    static {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(Application.class);
        } catch (ContainerInitializationException e) {
            // if we fail here. We re-throw the exception to force another cold start
            e.printStackTrace();
            throw new RuntimeException("Could not initialize Spring Boot application", e);
        }
    }

    @Override
    public void handleRequest(InputStream inputStream,
                              OutputStream outputStream,
                              Context context) throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
    }

    /**
     * Example that copy and logs the inputStream
     */

//    @Override
//    public void handleRequest(InputStream inputStream,
//                              OutputStream outputStream,
//                              Context context) throws IOException {
//        LambdaLogger logger = context.getLogger();
//
//        // Read and copy the incoming request
//        ByteArrayOutputStream requestBuffer = new ByteArrayOutputStream();
//        inputStream.transferTo(requestBuffer);
//
//        byte[] requestBytes = requestBuffer.toByteArray();
//
//        logger.log("INCOMING REQUEST: " + new String(requestBytes, StandardCharsets.UTF_8));
//
//        // Process the request
//        ByteArrayOutputStream responseBuffer = new ByteArrayOutputStream();
//
//        try {
//            handler.proxyStream(
//                    new ByteArrayInputStream(requestBytes),
//                    responseBuffer,
//                    context
//            );
//
//            // Copy response to the original output stream
//            byte[] responseBytes = responseBuffer.toByteArray();
//
//            logger.log("OUTGOING RESPONSE: " + new String(responseBytes, StandardCharsets.UTF_8));
//
//            outputStream.write(responseBytes);
//            outputStream.flush();
//
//        } catch (Exception e) {
//            logger.log("ERROR PROCESSING REQUEST: " + e.getMessage());
//            throw e;
//        }
//    }

}