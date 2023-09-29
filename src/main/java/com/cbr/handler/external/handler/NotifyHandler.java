package com.cbr.handler.external.handler;

import com.cbr.handler.external.constant.BpmnErrorConstant;
import com.cbr.handler.external.constant.ProcessVariableConstant;
import com.cbr.handler.external.service.NotifyService;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.context.annotation.Configuration;

@Configuration
@ExternalTaskSubscription("notify")
public class NotifyHandler implements ExternalTaskHandler {

    private final NotifyService notifyService;

    public NotifyHandler(NotifyService notifyService) {
        this.notifyService = notifyService;
    }

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        try {
            Integer chatId = externalTask.getVariable(ProcessVariableConstant.CHAT_ID);
            String text = externalTask.getVariable(ProcessVariableConstant.TEXT);
            notifyService.notify(chatId.longValue(), text);
            externalTaskService.complete(externalTask);
        } catch (Exception e) {
            e.printStackTrace();
            externalTaskService.handleBpmnError(externalTask, BpmnErrorConstant.EXTERNAL_TASK_ERROR);
        }
    }
}
