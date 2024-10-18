package com.example.flowable.dto;

public class SubmitPRDTO {
    private String processDefinitionKey;
    private String businessKey;
    private Object variables;

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    // public void setProcessDefinitionKey(String processDefinitionKey) {
    // this.processDefinitionKey = processDefinitionKey;
    // }
}
