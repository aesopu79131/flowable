package com.example.flowable.controller;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.flowable.dto.SubmitPRDTO;

@RestController
@RequestMapping("/processes")

public class ProcessController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @GetMapping("/list")
    public List<String> listProcesses() {
        return repositoryService.createProcessDefinitionQuery()
                .latestVersion()
                .list()
                .stream()
                .map(ProcessDefinition::getKey)
                .collect(Collectors.toList());
    }

    // @PostMapping("/start/{processDefinitionKey}")
    @PostMapping(value = "/start", consumes = "application/json")
    public ResponseEntity<String> startProcess(@RequestBody SubmitPRDTO requestBody) {
        // throws Exception {
        // System.out.println(payload);
        // }

        System.out.println(requestBody.getProcessDefinitionKey());

        return ResponseEntity.ok("1");

        // try {
        // ProcessDefinition processDefinition =
        // repositoryService.createProcessDefinitionQuery()
        // .processDefinitionKey(processDefinitionKey)
        // .latestVersion()
        // .singleResult();

        // if (processDefinition != null) {
        // ProcessInstance processInstance =
        // runtimeService.startProcessInstanceByKey(processDefinitionKey);
        // return "Process " + processDefinitionKey + " started! Instance ID: " +
        // processInstance.getId();
        // } else {
        // return "Cannt found the " + processDefinitionKey + "！";
        // }
        // } catch (Exception e) {
        // return "Error: " + e.getMessage();
        // }
    }

    @GetMapping("/info/{processDefinitionKey}")
    public String getProcessInfo(@PathVariable String processDefinitionKey) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey)
                .latestVersion()
                .singleResult();

        if (processDefinition != null) {
            return "Process Definition: " + processDefinition.getName() +
                    ", Key: " + processDefinition.getKey() +
                    ", Ver.: " + processDefinition.getVersion() +
                    ", Deployment ID: " + processDefinition.getDeploymentId();
        } else {
            return "Cannot found the deployment " + processDefinitionKey + "！";
        }
    }
}
