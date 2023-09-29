package com.cbr.handler.external.service;

import com.cbr.handler.external.config.ApplicationProperties;
import com.cbr.handler.external.model.Participant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ParticipantServiceImpl implements ParticipantService {

    private final RestTemplate restTemplate;
    private final ApplicationProperties applicationProperties;

    public ParticipantServiceImpl(@Qualifier("authenticatedRestTemplate") RestTemplate restTemplate,
                                  ApplicationProperties applicationProperties) {
        this.restTemplate = restTemplate;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public List<Participant> get() {
        return (List<Participant>) restTemplate.getForObject(applicationProperties.getParticipantsUrl(), List.class);
    }

    @Override
    public List<Participant> numerate(List<Participant> participants) {

        return (List<Participant>) restTemplate.postForObject(
                applicationProperties.getNumberingUrl(),
                participants, List.class);
    }
}
