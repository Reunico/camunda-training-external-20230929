package com.cbr.handler.external.service;

import com.cbr.handler.external.config.ApplicationProperties;
import com.cbr.handler.external.model.Notification;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotifyServiceImpl implements NotifyService {

    private final ApplicationProperties applicationProperties;
    private final RestTemplate restTemplate;

    public NotifyServiceImpl(ApplicationProperties applicationProperties,
                             @Qualifier("authenticatedRestTemplate") RestTemplate restTemplate) {
        this.applicationProperties = applicationProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public void notify(Long chatId, String text) {
        restTemplate.postForLocation(applicationProperties.getNotifyUrl(),
        new Notification(chatId, text));
    }
}
