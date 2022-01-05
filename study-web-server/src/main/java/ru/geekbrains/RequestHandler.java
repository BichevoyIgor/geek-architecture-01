package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;

import java.io.IOException;
import java.util.Deque;

public class RequestHandler implements Runnable {

    private final SocketService socketService;
    private final RequestParser requestParser;
    private final String www;

    public RequestHandler(SocketService socketService, RequestParser requestParser, String www) {
        this.socketService = socketService;
        this.requestParser = requestParser;
        this.www = www;
    }

    @Override
    public void run() {
        Deque<String> rawRequest = socketService.readRequest();
        HttpRequest httpRequest = requestParser.parseRequest(rawRequest);
        ResponseParser responseParser = new ResponseParser();
       responseParser.parseResponse(httpRequest, www, socketService);

        try {
            socketService.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        System.out.println("Client disconnected!");
    }
}
