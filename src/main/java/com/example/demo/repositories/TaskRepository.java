package com.example.demo.repositories;

import com.example.demo.models.Task;
import com.example.demo.models.TaskFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public Task findByTaskName(String taskName);

    public List<Task> findByCompletedTrue();

    public List<Task> findByCompletedFalse();

    public List<Task> findAll();

    public Task getById(Long id);

    // returns a list of tasks/files belonging to the same parent folder,
    // where folder.id == id. (Basically get child files of id folder)
    public List<Task> findByTaskFolder(TaskFolder task_folder);

    /*
    @Modifying
    @Query(value = "ALTER SEQUENCE task_sequence_generator RESTART WITH 1;",
            nativeQuery = true)
    void resetTaskSequence();
    */
}
