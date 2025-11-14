package com.example.task_management_api.controller;

import com.example.task_management_api.model.Task;
import com.example.task_management_api.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskWebController {

    private final TaskService taskService;

    public TaskWebController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("newTask", new Task());
        return "tasks";
    }

    @PostMapping("/tasks/add")
    public String addTask(@ModelAttribute("newTask") Task task) {
        task.setStatus(com.example.task_management_api.model.TaskStatus.TODO);

        taskService.createTask(task);

        return "redirect:/";
    }
}