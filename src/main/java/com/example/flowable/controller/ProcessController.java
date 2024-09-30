package com.example.flowable.controller;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProcessController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @GetMapping("/list-processes")
    public List<String> listProcesses() {
        return repositoryService.createProcessDefinitionQuery()
                .latestVersion()
                .list()
                .stream()
                .map(ProcessDefinition::getKey)
                .collect(Collectors.toList());
    }

    @GetMapping("/start-process/{processDefinitionKey}")
    public String startProcess(@PathVariable String processDefinitionKey) {
        if (repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).count() > 0) {
            runtimeService.startProcessInstanceByKey(processDefinitionKey);
            return "Process " + processDefinitionKey + " started successfully!";
        } else {
            return "Process definition " + processDefinitionKey + " not found!";
        }
    }
}