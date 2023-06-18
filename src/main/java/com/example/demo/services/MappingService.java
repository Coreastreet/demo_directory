package com.example.demo.services;

import com.example.demo.dto.DirectoryDTO;
import com.example.demo.models.Task;
import com.example.demo.models.TaskFolder;
import com.example.demo.repositories.TaskFolderRepository;
import com.example.demo.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MappingService {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskFolderService taskFolderService;

    // return a list of directory objects (which are like folders but also keep a list
    // record of all the sub-folders and files under them.

    public List<DirectoryDTO> getFullDirectoryList(Task currentFile) {
        List<DirectoryDTO> directoryList = new ArrayList<DirectoryDTO>();

        TaskFolder taskFolder = currentFile.getTaskFolder();
        // while loop exits when root folder reached.
        // check if taskFolder is null or missing or non-existent.
        while (!(taskFolder == null)) {
            List<Task> childFiles = taskFolderService.getAllChildFiles(taskFolder);
            List<TaskFolder> childFolders = taskFolderService.getAllChildFolders(taskFolder.getId());

            // last in first out. Push the DTO from the lowest level to root folder.
            directoryList.add(new DirectoryDTO(taskFolder, childFolders, childFiles));
            taskFolder = taskFolder.getTaskFolder();
        }
        Collections.reverse(directoryList);
        return directoryList; // root at the end (reversed)
    }
    // purely for testing
    //public void resetDirectorySequences() {
    //    taskService.resetTaskSequenceId();
    //    taskFolderService.resetTaskFolderSequenceId();
    //}
}
