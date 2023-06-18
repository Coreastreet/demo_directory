package com.example.demo.dto;

import com.example.demo.models.Task;
import com.example.demo.models.TaskFolder;
import com.example.demo.services.TaskService;
import com.example.demo.services.TaskFolderService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record DirectoryDTO(TaskFolder taskFolder, List<TaskFolder> childFolders, List<Task> childFiles) {
    // Check if file exists in the directory.
    public boolean IsFileInDirectory(Task currentFile) {
        boolean isFileInDirectory = false;
        // first folder
        TaskFolder taskFolder = currentFile.getTaskFolder();
        // while loop exits when root folder reached.
        // check if taskFolder is null or missing or non-existent.
        while (!(taskFolder == null)) {
            taskFolder = taskFolder.getTaskFolder();
            if (taskFolder.equals(taskFolder)) {
                isFileInDirectory = true;
                break;
            }
        }
        return isFileInDirectory;
    }
}