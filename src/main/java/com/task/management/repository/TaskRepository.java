package com.task.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.management.entites.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    
}