package com.example.demo.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnore
@Builder
@Table(name="tasks")
@Entity

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="task_sequence_generator")
    @SequenceGenerator(name = "task_sequence_generator", allocationSize = 1)
    @Getter
    @Setter
    private Long id;
    private String taskName;
    private boolean completed;
    @JsonIgnore
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_folder_id", referencedColumnName = "id")
    private TaskFolder taskFolder;

    public Task(String taskName, boolean completed, TaskFolder taskFolder) {
        this.taskName = taskName;
        this.completed = completed;
        this.taskFolder = taskFolder;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTask() {
        return taskName;
    }
    public void setTask(String task_name) {
        this.taskName = task_name;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task [id=" + getId() + ", taskName=" + getTask()
                + ", completed=" + isCompleted()
                + ", task_folder=" + getTaskFolder() + "]";
    }

}
