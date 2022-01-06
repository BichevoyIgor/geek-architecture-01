package ru.geekbrains.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpResponse {
    private String protocol;
    private String contentType;
    private String path;
    private String message;
    private StringBuilder body;

    private HttpResponse() {
    }

    @Override
    public String toString() {
        if (message == null) {
            return protocol + contentType + body;
        } else {
            return protocol + contentType + message + body;
        }
    }

    public static class HttpResponseBuilder {
        private HttpResponse httpResponse;
        private String path;
        private Method method = Method.GET;

        public HttpResponseBuilder() {
            this.httpResponse = new HttpResponse();
            httpResponse.body = new StringBuilder();
        }

        public HttpResponseBuilder withProtocol(String protocol) {
            httpResponse.protocol = protocol;
            return this;
        }

        public HttpResponseBuilder withContentType() {
            httpResponse.contentType = "Content-Type: text/html; charset=utf-8\n\n";
            return this;
        }

        public HttpResponseBuilder withMethod(Method method) {
            this.method = method;
            return this;
        }

        public HttpResponseBuilder withPath(String url, String www) {
            path = www;
            httpResponse.path = url;
            return this;
        }

        public HttpResponse build() {
            if (httpResponse.protocol.isEmpty()) {
                httpResponse.protocol = "HTTP/1.1";
            }

            Path pat = Paths.get(path, httpResponse.path);

            if (!Files.exists(pat)) {
                httpResponse.protocol = httpResponse.protocol + " 404 NOT_FOUND\n";
                httpResponse.message = "<h1>Файл не найден!</h1>";
            } else {
                if (method.equals(Method.GET)) {
                    httpResponse.protocol = httpResponse.protocol + " 200 OK\n";
                    try {
                        Files.readAllLines(pat).forEach(httpResponse.body::append);
                    } catch (IOException e) {
                        throw new IllegalStateException(e);
                    }
                }else {
                    httpResponse.protocol = httpResponse.protocol + " 405 METHOD_NOT_ALLOWED\n";
                    httpResponse.message = "<h1>Метод не поддерживается!</h1>";
                }
            }
            return this.httpResponse;
        }
    }
}
