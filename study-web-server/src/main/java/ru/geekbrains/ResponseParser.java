package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;

public class ResponseParser {

    public void parseResponse(HttpRequest httpRequest, String www, SocketService socketService) {

        if (httpRequest.getMethod().equals("GET")) {
            new HttpResponse(socketService, httpRequest.getProtocol()).HttpResponseGet(httpRequest, www);
        } else {
            new HttpResponse(socketService, httpRequest.getProtocol()).HttpResponseAnotherMethod();
        }
    }
}
