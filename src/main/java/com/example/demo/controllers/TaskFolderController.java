package com.example.demo.controllers;

import com.example.demo.dto.DirectoryDTO;
import com.example.demo.models.Task;
import com.example.demo.models.TaskFolder;
import com.example.demo.services.MappingService;
import com.example.demo.services.TaskFolderService;
import com.example.demo.services.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/task_folders")
public class TaskFolderController {
    @Autowired
    private TaskFolderService taskFolderService;

    @Autowired
    private TaskService taskService;
    @Autowired
    private MappingService mappingService;

    // get directory and path to current file
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskFolderController.class);

    // get the full directory with DTO objects.
    @GetMapping("/full_directory/{current_file_id}")
    public ResponseEntity<List<DirectoryDTO>> getAllFolders(@PathVariable Long current_file_id) {
        Task current_file = taskService.findTaskById(current_file_id);
        List<DirectoryDTO> directoryDTOs = mappingService.getFullDirectoryList(current_file);
        return ResponseEntity.ok(directoryDTOs);
    }
    
    //taskFolderService.getAllChildFolders(id) ResponseEntity<List<TaskFolder>>
    @GetMapping("/{id}/subfolders") // working
    public ResponseEntity<List<TaskFolder>> getChildFolders(@PathVariable Long id) {
        List<TaskFolder> listChildFolders = taskFolderService.getAllChildFolders(id);
        //LOGGER.info("What is up my G {}");
        return ResponseEntity.ok(listChildFolders);
    }

    @GetMapping("/{id}") // working
    public ResponseEntity<TaskFolder> getFolderById(@PathVariable Long id) {
        return ResponseEntity.ok(taskFolderService.getFolder(id));
    }

    @GetMapping("/{id}/tasks") // working
    public ResponseEntity<List<Task>> getChildTasksByFolderId(@PathVariable Long id) {
        return ResponseEntity.ok(taskFolderService.getAllFolderTasks(id));
    }

    @DeleteMapping("/{id}") // working
    public ResponseEntity<Boolean> deleteFolderById(@PathVariable Long id) {
        taskFolderService.deleteFolder(id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("") // working
    public ResponseEntity<Boolean> deleteAllFolders() {
        taskFolderService.deleteAllFolders();
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}") // working now
    public ResponseEntity<TaskFolder> updateFolderDetailsById(@PathVariable Long id,
                                                       @RequestBody TaskFolder new_task_folder) {
        //task_folder.setTaskFolder(task_folder);
        LOGGER.info("This is the new parent folder id - {}", new_task_folder.getTaskFolder().getId());
        TaskFolder new_parent_folder = taskFolderService.getFolder(new_task_folder.getTaskFolder().getId());
        TaskFolder updated_task_folder = taskFolderService.updateFolder(id, new_task_folder);

        return ResponseEntity.ok(updated_task_folder);
    }

    @PostMapping("")
    public ResponseEntity<TaskFolder> createNewFolder(@RequestBody TaskFolder task_folder) {
        return ResponseEntity.ok(taskFolderService.createFolder(task_folder));
    }
}
