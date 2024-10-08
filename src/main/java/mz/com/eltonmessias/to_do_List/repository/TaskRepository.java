package mz.com.eltonmessias.to_do_List.repository;


import mz.com.eltonmessias.to_do_List.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
}
