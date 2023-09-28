package com.task.taskBackend.repository;

import com.task.taskBackend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // This will inherit all the crud operations from JpaRepo
}
