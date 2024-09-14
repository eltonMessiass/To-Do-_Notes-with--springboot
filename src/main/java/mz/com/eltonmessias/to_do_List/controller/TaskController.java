package mz.com.eltonmessias.to_do_List.controller;

import mz.com.eltonmessias.to_do_List.dto.TaskDTO;
import mz.com.eltonmessias.to_do_List.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/tasks")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO createdTask = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks());
    }

    @GetMapping("tasks/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(id));
    }

    @PutMapping("tasks/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable(value = "id") UUID id, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(id, taskDTO));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable(value = "id") UUID id) {
         taskService.deleteTask(id);
         return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfuly");
    }


}
