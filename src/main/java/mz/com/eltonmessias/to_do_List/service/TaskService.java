package mz.com.eltonmessias.to_do_List.service;


import mz.com.eltonmessias.to_do_List.dto.TaskDTO;
import mz.com.eltonmessias.to_do_List.model.TaskModel;
import mz.com.eltonmessias.to_do_List.repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
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
        TaskModel taskModel = new TaskModel();
        BeanUtils.copyProperties(taskDTO, taskModel);
        TaskModel savedTask = taskRepository.save(taskModel);
        BeanUtils.copyProperties(savedTask, taskDTO);
        return taskDTO;
    }
    
    public List<TaskDTO> getAllTasks(){
        return taskRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
