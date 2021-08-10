package com.task.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.management.entites.Task;
import com.task.management.repository.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;

	public void createTask(Task task) {
		taskRepository.save(task);
	}

	public void updateTask(Long id, Task updatedTask) {
		Task task = taskRepository.findById(id).get();
		task.setName(updatedTask.getName());
		task.setDescription(updatedTask.getDescription());
		task.setStart_date(updatedTask.getStart_date());
		task.setTaskIncurred(updatedTask.getTaskIncurred());
		taskRepository.save(task);
	}

	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}

	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	public void setTaskCompleted(Long id) {
		Task task = taskRepository.findById(id).get();
		task.setCompleted(true);
		taskRepository.save(task);
	}

	public void setTaskNotCompleted(Long id) {
		Task task = taskRepository.findById(id).get();
		task.setCompleted(false);
		taskRepository.save(task);
	}

	public Task getTaskById(Long id) {
		return taskRepository.findById(id).orElse(null);
	}

}
