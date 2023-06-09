package com.example.demo;

import com.example.demo.models.Task;
import com.example.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public void run(String... args) throws Exception {

		Task task1 = Task.builder()
				.taskName("Eat Weetbix")
				.completed(true)
				.build();

		Task task2 = Task.builder()
				.taskName("Eat Lunch")
				.completed(true)
				.build();

		Task task3 = Task.builder()
				.taskName("Eat Dinner")
				.completed(false)
				.build();

		taskRepository.save(task1);
		taskRepository.save(task2);
		taskRepository.save(task3);
	}

}
