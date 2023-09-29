package com.cbr.handler.external.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participant implements Serializable {
    private String name;
    private String createdDate;
    private String chatId;
    private Long number;
}
