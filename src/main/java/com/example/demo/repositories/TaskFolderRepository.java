package com.example.demo.repositories;

import com.example.demo.models.TaskFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskFolderRepository extends JpaRepository<TaskFolder, Long> {
    // public List<TaskFolder> findAll();

    //public TaskFolder getReferenceById(Long id);

    // returns a list of sub-folders belonging to the same parent folder,
    // where folder.id == id. (basically child folders of id folder)
    public List<TaskFolder> findByTaskFolder(TaskFolder taskFolder);
    public TaskFolder findByTaskFolderIsNull();

    /*
    @Modifying
    @Query(value = "ALTER SEQUENCE task_folder_sequence_generator RESTART WITH 1;",
            nativeQuery = true)
    void resetTaskFolderSequence();
    */
}
