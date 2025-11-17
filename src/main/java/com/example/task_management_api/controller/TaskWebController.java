package com.example.task_management_api.controller;

import com.example.task_management_api.model.Task;
import com.example.task_management_api.model.TaskStatus;
import com.example.task_management_api.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @PostMapping("/tasks/status/{id}")
    public String updateStatus(@PathVariable Long id, @RequestParam TaskStatus nextStatus) {
        taskService.getTaskById(id)
                .map(task -> {
                    task.setStatus(nextStatus);
                    return taskService.updateTask(id, task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found with id" + id));
        return "redirect:/";
    }
}