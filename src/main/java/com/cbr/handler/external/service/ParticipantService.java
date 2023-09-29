package com.cbr.handler.external.service;

import com.cbr.handler.external.model.Participant;

import java.util.List;

public interface ParticipantService {
    List<Participant> get();
    List<Participant> numerate(List<Participant> participants);

}
