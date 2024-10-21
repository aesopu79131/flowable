package com.example.flowable.controller;

import com.example.flowable.dto.SubmitPRDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/processes")
public class ProcessController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    private final Gson gson = new Gson(); // 创建 Gson 实例

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
        String processKey = requestBody.getProcessDefinitionKey();
        String businessKey = requestBody.getBusinessKey();
        Object variables = requestBody.getVariables();

        // 将 Object variables 转换为 Map
        Type mapType = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> variablesMap = gson.fromJson(gson.toJson(variables), mapType);

        // System.out.println(processKey);
        // System.out.println(businessKey);
        // System.out.println(variablesMap); // 打印转换后的 Map

        try {
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionKey(processKey)
                    .latestVersion()
                    .singleResult();

            if (processDefinition != null) {
                ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, businessKey,
                        variablesMap);
                return ResponseEntity.ok("Process " + processKey + " started! Instance ID: "
                        +
                        processInstance.getId());
            } else {
                return ResponseEntity.ok("Cannot found the " + processKey + "！");
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.ok("Error: " + e.getMessage());
        }
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
