package com.cbr.handler.external.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "application", ignoreInvalidFields = true)
@Component
@Getter
@Setter
public class ApplicationProperties {
    private String login;
    private String password;
    private String lotteryUrl;
    private String participantsUrl;
    private String numberingUrl;
    private String notifyUrl;
}
