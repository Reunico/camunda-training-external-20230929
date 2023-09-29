package com.cbr.handler.external.service;

import com.cbr.handler.external.config.ApplicationProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LotteryServiceImpl implements LotteryService {

    private final ApplicationProperties applicationProperties;
    private final RestTemplate restTemplate;

    public LotteryServiceImpl(ApplicationProperties applicationProperties,
                              @Qualifier("authenticatedRestTemplate") RestTemplate restTemplate) {
        this.applicationProperties = applicationProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public void start() {
        restTemplate.postForLocation(applicationProperties.getLotteryUrl() + "/start", null);
    }

    @Override
    public void stop() {
        restTemplate.postForLocation(applicationProperties.getLotteryUrl() + "/offline", null);
    }
}
