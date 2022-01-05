package ru.geekbrains.domain;

import ru.geekbrains.SocketService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpResponse {
    private final String protocol;
    private final String contentType = "Content-Type: text/html; charset=utf-8";
    private StringBuilder response;
    private SocketService socketService;

    public HttpResponse(SocketService socketService, String protocol) {
        this.socketService = socketService;
        this.protocol = protocol;
        this.response = new StringBuilder();
    }

    public void HttpResponseGet(HttpRequest httpRequest, String www) {

        Path path = Paths.get(www, httpRequest.getUrl());

        if (!Files.exists(path)) {
            response.append(protocol + " 404 NOT_FOUND\n");
            response.append(contentType + "\n");
            response.append("\n");
            response.append("<h1>Файл не найден!</h1>");
            socketService.writeResponse(response.toString());
        }

        response.append(protocol + " 200 OK\n");
        response.append(contentType + "\n");
        response.append("\n");

        try {
            Files.readAllLines(path).forEach(response::append);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        socketService.writeResponse(response.toString());
    }

    public void HttpResponseAnotherMethod() {
        response.append(protocol + " 405 METHOD_NOT_ALLOWED\n");
        response.append(contentType + "\n");
        response.append("\n");
        response.append("<h1>Метод не поддерживается!</h1>");
        socketService.writeResponse(response.toString());
    }
}
