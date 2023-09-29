package com.cbr.handler.external.handler;

import com.cbr.handler.external.constant.BpmnErrorConstant;
import com.cbr.handler.external.constant.ProcessVariableConstant;
import com.cbr.handler.external.model.Participant;
import com.cbr.handler.external.service.ParticipantService;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;

@Configuration
@ExternalTaskSubscription("numerate-participants")
public class NumerateParticipantHandler implements ExternalTaskHandler {

    private final ParticipantService participantService;

    public NumerateParticipantHandler(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        try {
            List<Participant> participants = externalTask.getVariable(ProcessVariableConstant.PARTICIPANTS);
            participants = participantService.numerate(participants);
            HashMap<String, Object> variableMap = new HashMap<>();
            variableMap.put(ProcessVariableConstant.PARTICIPANTS, participants);
            externalTaskService.complete(externalTask, variableMap);
        } catch (Exception e) {
            e.printStackTrace();
            externalTaskService.handleBpmnError(externalTask, BpmnErrorConstant.EXTERNAL_TASK_ERROR);
        }
    }
}
