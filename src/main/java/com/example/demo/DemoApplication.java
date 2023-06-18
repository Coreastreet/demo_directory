package com.example.demo;

import com.example.demo.models.Task;
import com.example.demo.models.TaskFolder;
import com.example.demo.repositories.TaskFolderRepository;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.services.MappingService;
import com.example.demo.services.TaskFolderService;
import com.example.demo.services.TaskService;
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
	private TaskService taskService;
	@Autowired
	private TaskFolderService taskFolderService;

	@Autowired
	private MappingService mappingService;
	@Override
	public void run(String... args) throws Exception {
		// taskFolderService.deleteAllFolders();
/*
		TaskFolder folder_1 = taskFolderService.createFolder(new TaskFolder("To-do list"));
		TaskFolder folder_2 = taskFolderService.createFolder(new TaskFolder("To-do list 2", folder_1));
		TaskFolder folder_3 = taskFolderService.createFolder(new TaskFolder("To-do list 3", folder_2));
		TaskFolder folder_4 = taskFolderService.createFolder(new TaskFolder("To-do list 4", folder_3));
		TaskFolder folder_5 = taskFolderService.createFolder(new TaskFolder("To-do list 5", folder_1));
		TaskFolder folder_6 = taskFolderService.createFolder(new TaskFolder("To-do list 6", folder_2));

		taskService.createTask(new Task("Task 1 - Folder 1", false, folder_1));
		taskService.createTask(new Task("Task 2 - Folder 1", true, folder_1));
		taskService.createTask(new Task("Task 1 - Folder 2", false, folder_2));
		taskService.createTask(new Task("Task 1 - Folder 3", false, folder_3));
		taskService.createTask(new Task("Task 2 - Folder 3", false, folder_3));
		taskService.createTask(new Task("Task 3 - Folder 3", false, folder_3));
		taskService.createTask(new Task("Task 1 - Folder 4", false, folder_4));
		taskService.createTask(new Task("Task 1 - Folder 5", false, folder_5));
		taskService.createTask(new Task("Task 1 - Folder 6", false, folder_6));
		taskService.createTask(new Task("Task 2 - Folder 6", false, folder_6));

 */
	}
}
