package mz.com.eltonmessias.to_do_List.service;


import mz.com.eltonmessias.to_do_List.dto.TaskDTO;
import mz.com.eltonmessias.to_do_List.model.TaskModel;
import mz.com.eltonmessias.to_do_List.repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    private TaskDTO convertToDTO(TaskModel taskModel) {
        TaskDTO taskDTO = new TaskDTO(taskModel.getId(), taskModel.getName(), taskModel.getDescription(), taskModel.isCompleted());
//        BeanUtils.copyProperties(taskModel, taskDTO);
        return taskDTO;
    }

    private TaskModel convertToModel(TaskDTO taskDTO) {
        TaskModel taskModel = new TaskModel();
        BeanUtils.copyProperties(taskDTO, taskModel);
        return taskModel;
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        TaskModel taskModel = convertToModel(taskDTO);
        TaskModel savedTaskModel = taskRepository.save(taskModel);
        return convertToDTO(savedTaskModel);
    }
    
    public List<TaskDTO> getAllTasks(){
        return taskRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public TaskDTO getTaskById(UUID id) {
        return taskRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public TaskDTO updateTask(UUID id, TaskDTO taskDTO) {
        TaskModel taskModel = taskRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(taskDTO, taskModel);
        TaskModel savedTaskModel = taskRepository.save(taskModel);
        return convertToDTO(savedTaskModel);
    }

    public void deleteTask(UUID id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
    }
}
