package ru.geekbrains;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public static void main(String[] args) {
        Config config = new ConfigFile("study-web-server/src/main/resources/config.properties");
        try (ServerSocket serverSocket = new ServerSocket(config.getPORT())) {
            System.out.println("Server started!");
            RequestParser requestParser = new RequestParser();

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

                new Thread(new RequestHandler(new SocketService(socket), requestParser, config.getWWW())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
