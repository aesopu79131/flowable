package com.example.flowable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.flowable.engine.RuntimeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.flowable.dto.TaskDTO;

import org.apache.commons.beanutils.PropertyUtils;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // @Autowired
    // private RuntimeService runtimeService;

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @GetMapping("/list")
    public ResponseEntity<List<TaskDTO>> getTasks() {
        List<Task> tasks = taskService.createTaskQuery().list();
        List<TaskDTO> taskDTOs = tasks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskDTOs);
    }

    private TaskDTO convertToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setDueDate(task.getDueDate());
        dto.setAssignee(task.getAssignee());
        return dto;
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(taskId);
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setAssignee(task.getAssignee());
        return ResponseEntity.ok(taskDTO);
    }

    @PostMapping("/{taskId}/complete")
    public ResponseEntity<String> completeTask(@PathVariable String taskId) {
        try {
            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            if (task != null) {
                taskService.complete(taskId);
                return ResponseEntity.ok("Task " + taskId + " is completed");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found task id: " + taskId);
            }
        } catch (Exception e) {
            String errorMessage = "Error：" + e.getMessage();
            e.printStackTrace(); // 在控制台打印堆栈跟踪
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
