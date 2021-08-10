package com.task.management.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.task.management.entites.Task;
import com.task.management.service.TaskService;

@Controller
public class TaskController {
	@Autowired
	private TaskService taskService;

	@GetMapping("/tasks")
	public String listTasks(Model model) {

		model.addAttribute("tasks", taskService.findAll());
		model.addAttribute("onlyInProgress", false);
		return "views/tasks";
	}

	@GetMapping("/tasks/in-progress")
	public String listTasksInProgress(Model model) {
		model.addAttribute("tasks", taskService.findAll());
		model.addAttribute("onlyInProgress", true);
		return "views/tasks";
	}

	@GetMapping("/task/create")
	public String showEmptyTaskForm(Model model) {

		model.addAttribute("task", new Task());
		return "forms/task-new";
	}

	@PostMapping("/task/create")
	public String createTask(@Valid Task task, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "forms/task-new";
		}
		taskService.createTask(task);
        
		return "redirect:/tasks";
	}

	@GetMapping("/task/edit/{id}")
	public String showFilledTaskForm(@PathVariable Long id, Model model) {
		model.addAttribute("task", taskService.getTaskById(id));
		return "forms/task-edit";
	}

	@PostMapping("/task/edit/{id}")
	public String updateTask(@Valid Task task, BindingResult bindingResult, @PathVariable Long id, Model model) {
		if (bindingResult.hasErrors()) {
			return "forms/task-edit";
		}
		taskService.updateTask(id, task);
		model.addAttribute("tasks", taskService.findAll());
		return "redirect:/tasks";
	}

	@GetMapping("/task/delete/{id}")
	public String deleteTask(@PathVariable Long id) {
		taskService.deleteTask(id);
		
		return "redirect:/tasks";
	}

	@GetMapping("/task/mark-done/{id}")
	public String setTaskCompleted(@PathVariable Long id) {
		taskService.setTaskCompleted(id);
		
		return "redirect:/tasks";
	}

	@GetMapping("/task/unmark-done/{id}")
	public String setTaskNotCompleted(@PathVariable Long id) {
		taskService.setTaskNotCompleted(id);
		return "redirect:/tasks";
	}

}
