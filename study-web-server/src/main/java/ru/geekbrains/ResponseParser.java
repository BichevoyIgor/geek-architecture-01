package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.domain.Method;

public class ResponseParser {

    public void parseResponse(HttpRequest httpRequest, String www, SocketService socketService) {
            HttpResponse httpResponse = new HttpResponse.HttpResponseBuilder()
                    .withProtocol(httpRequest.getProtocol())
                    .withPath(httpRequest.getUrl(), www)
                    .withMethod(Method.GET)
                    .withContentType()
                    .build();
            socketService.writeResponse(httpResponse.toString());
    }
}
