package com.example.demo.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Getter // will return the actual task_folder parent not just the foreign key id.
@Setter
@Entity
@Table(name="task_folders")
public class TaskFolder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="task_folder_sequence_generator")
    @SequenceGenerator(name = "task_folder_sequence_generator", allocationSize = 1)
    private Long id;

    private String name;

    @JsonIgnoreProperties({ "taskFolder", "name" })  // get immediate details not that of associations.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_folder_id", referencedColumnName = "id")
    private TaskFolder taskFolder;

    public TaskFolder(String name) {
        this.name = name;
    }
    public TaskFolder(String name, TaskFolder task_folder) {
        this.name = name;
        this.taskFolder = task_folder;
    }

    @Override
    public String toString() {
        return "TaskFolder [id=" + getId() + ", name=" + getName()
                + ", task_folder=" + getTaskFolder() + "]";
    }

    // getter and setter

    // getName() & setName(name)
}
