package com.example.task_management_api.config;

import com.example.task_management_api.model.Task;
import com.example.task_management_api.model.TaskStatus;
import com.example.task_management_api.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final TaskRepository taskRepository;

    @Override
    public void run(String... args) {
        if (taskRepository.count() == 0) {
            taskRepository.save(Task.builder()
                    .title("Learn 360noscope")
                    .description("Go through tutorials")
                    .status(TaskStatus.IN_PROGRESS)
                    .build());

            taskRepository.save(Task.builder()
                    .title("Build eiffel tower")
                    .description("Just do it")
                    .status(TaskStatus.TODO)
                    .build());

            taskRepository.save(Task.builder()
                    .title("Test the application")
                    .description("Is it work?")
                    .status(TaskStatus.TODO)
                    .build());
        }
    }
}
