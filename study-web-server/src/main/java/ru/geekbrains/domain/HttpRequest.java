package ru.geekbrains.domain;

import java.util.Map;

public class HttpRequest {

    private final String method;

    private final String url;

    private final Map<String, String> headers;

    private final String body;

    private final String protocol;
    public HttpRequest(String method, String url, Map<String, String> headers, String protocol, String body) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.body = body;
        this.protocol = protocol;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public String getProtocol() {
        return protocol;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}
