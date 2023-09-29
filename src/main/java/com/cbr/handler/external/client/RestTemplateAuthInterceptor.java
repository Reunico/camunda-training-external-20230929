package com.cbr.handler.external.client;

import com.cbr.handler.external.config.ApplicationProperties;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RestTemplateAuthInterceptor implements ClientHttpRequestInterceptor {

    private final ApplicationProperties applicationProperties;

    public RestTemplateAuthInterceptor(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        String auth = applicationProperties.getLogin() + ":" + applicationProperties.getPassword();
        byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String( encodeAuth );
        request.getHeaders().add(HttpHeaders.AUTHORIZATION, authHeader);
        return execution.execute(request, body);
    }
}
