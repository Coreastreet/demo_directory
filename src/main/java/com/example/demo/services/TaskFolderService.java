package com.example.demo.services;

import com.example.demo.controllers.TaskFolderController;
import com.example.demo.models.Task;
import com.example.demo.models.TaskFolder;
import com.example.demo.repositories.TaskFolderRepository;
import com.example.demo.repositories.TaskRepository;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskFolderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskFolderService.class);    // get directory and path to current file
    @Autowired
    private TaskFolderRepository task_folder_repository;
    @Autowired
    private TaskRepository task_repository;

    public List<TaskFolder> getAllFolders() {
        return task_folder_repository.findAll();
    }

    public TaskFolder getFolder(Long id) {
        if (task_folder_repository.existsById(id)) {
            return task_folder_repository.getReferenceById(id);
        };
        // TaskFolder (by id) does not exist.
        LOGGER.info("Task folder with id: {} does not exist", id);
        return null;
    }

    public List<Task> getAllFolderTasks(Long id) {
        TaskFolder task_folder = getFolder(id);
        if (task_folder == null) {
            // empty list returned
            return new ArrayList<Task>();
        } else {
            return task_repository.findByTaskFolder(task_folder);
        }
    }

    public TaskFolder createFolder(TaskFolder taskfolder) {
        return task_folder_repository.save(taskfolder);
    }

    public TaskFolder updateFolder(Long id, TaskFolder updated_task_folder) {
        TaskFolder task_folder = task_folder_repository.getReferenceById(id);
        task_folder.setName(updated_task_folder.getName());

        TaskFolder parent_folder = task_folder_repository
                .getReferenceById(updated_task_folder.getTaskFolder().getId());
        task_folder.setTaskFolder(parent_folder);
        return task_folder_repository.save(task_folder);
    }
    // must also delete any child folders that exist too.
    public void deleteFolder(Long id) {
        TaskFolder task_folder = task_folder_repository.getReferenceById(id);
        // delete all immediate child files
        deleteChildFiles(id);
        // all immediate child folder list - recursive delete
        List<TaskFolder> task_folder_list = getAllChildFolders(id);
        if (!task_folder_list.isEmpty()) {
            for (TaskFolder taskFolder : task_folder_list) {
                LOGGER.info("Get child task folder with ID {}", taskFolder.getId());
                deleteFolder(taskFolder.getId());
            }
        }

        // last operation - delete the file itself.
        task_folder_repository.delete(task_folder);
    }

    private void deleteChildFiles(Long id) {
        List<Task> task_list = getAllChildFiles(getFolder(id));
        task_repository.deleteAll(task_list);
    }


    // delete all records of folders and their associated files
    public void deleteAllFolders() {
        // find the root folder out of the folders linked to directory project and null.
        TaskFolder root_folder = task_folder_repository.findByTaskFolderIsNull();
        // delete that folder
        if (root_folder != null) {
            deleteFolder(root_folder.getId());
        }
        LOGGER.info("no folders in the database");
    }

    // returns a list of all the child folders under the current folder.
    public List<TaskFolder> getAllChildFolders(Long id) {
        TaskFolder taskFolder = getFolder(id);
        if (taskFolder == null) {
            // task folder does not exist.
            return new ArrayList<TaskFolder>();
        } else {
            return task_folder_repository.findByTaskFolder(taskFolder);
        }
    }

    // returns a list of files/tasks under a current folder.
    public List<Task> getAllChildFiles(TaskFolder task_folder) {
        List<Task> list_of_tasks = task_repository.findByTaskFolder(task_folder);
        for (int i = 0; i < list_of_tasks.size(); i++) {
            Task task = list_of_tasks.get(i);
            if (task instanceof HibernateProxy) {
                Task unproxiedTask = (Task) Hibernate.unproxy(task);
                list_of_tasks.set(i, unproxiedTask);
            }
        }
        return list_of_tasks;
    }

    //public void resetTaskFolderSequenceId() {
    //    task_folder_repository.resetTaskFolderSequence();
    //}
}
