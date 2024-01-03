/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.client.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/**
 *
 * @author sushant.kumar
 */
public class ServiceClient {

    private final String baseUrl;
    private final int timeoutMillis;

    private WebClient webClientV2() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn
                        -> conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(timeoutMillis, TimeUnit.MILLISECONDS)));

        WebClient client = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        return client;
    }

    public ServiceClient(String baseUrl, int timeout) {
        this.baseUrl = baseUrl;
        this.timeoutMillis = timeout;
    }

    private WebClient getClient(String path) {
        WebClient client = WebClient.builder()
                .baseUrl(baseUrl + path)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                //.defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();
        return client;
    }
    
    
    private WebClient getClientV2(String path) {
        return webClientV2();
    }

    private <T> T decodeResponse(WebClient.RequestHeadersUriSpec<?> spec, TypeReference type) {
        WebResponse wr = new WebResponse(HttpStatusCode.valueOf(200), "OK");
        Mono<String> resp;
        resp = spec.exchangeToMono(response -> {
            wr.setStatusCode(response.statusCode());
            if (response.statusCode().equals(HttpStatus.OK)) {
                return response.bodyToMono(String.class);
            } else if (response.statusCode().is4xxClientError()) {
                return Mono.just("Error response " + response.statusCode());
            } else {
                return response.createException()
                        .flatMap(Mono::error);
            }
        });
        wr.setResponse(resp.block());
        return (T) parseResponse(wr, type);

    }
    
    
    private <T> T decodeResponse(WebClient.RequestHeadersSpec<?> spec, TypeReference type) {
        WebResponse wr = new WebResponse(HttpStatusCode.valueOf(200), "OK");
        Mono<String> resp;
        resp = spec.exchangeToMono(response -> {
            wr.setStatusCode(response.statusCode());
            if (response.statusCode().equals(HttpStatus.OK)) {
                return response.bodyToMono(String.class);
            } else if (response.statusCode().is4xxClientError()) {
                return Mono.just("Error response " + response.statusCode());
            } else {
                return response.createException()
                        .flatMap(Mono::error);
            }
        });
        wr.setResponse(resp.block());
        return (T) parseResponse(wr, type);

    }

    private <T> T parseResponse(WebResponse wr, TypeReference<T> type) {
        if(wr.getResponse()==null || wr.getResponse().isEmpty()) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            T jsonResponse = mapper.readValue(wr.getResponse(), type);
            return jsonResponse;
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error from web calls " + wr.getStatusCode().value());
        }
    }

    public <T> T executeGet(WebRequest<T> req) {
        return decodeResponse(getClient(req.getPath()).get(), req.getType());
    }

    public <T> T executePost(WebRequest<T> req) {
        WebClient.RequestHeadersSpec<?> post = getClient(req.getPath()).post().bodyValue(req.getPayload());
        return decodeResponse(post, req.getType());
    }

}
