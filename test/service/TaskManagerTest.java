package service;

import model.Priority;
import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    void getTaskByIdReturnsCorrectTask() {
        Task task = new Task(
                1,
                "Create UML",
                "Create UML diagram",
                Priority.HIGH
        );

        taskManager.createTask(task);

        Task result = taskManager.getTaskById(1);

        assertEquals(task, result);
    }

    @Test
    void getTaskByIdReturnsNullWhenTaskDoesNotExist() {
        Task result = taskManager.getTaskById(100);

        assertNull(result);
    }

    @Test
    void changeTaskStatusChangesStatus() {
        Task task = new Task(
                1,
                "Write code",
                "Create Task Manager classes",
                Priority.MEDIUM
        );

        taskManager.createTask(task);

        taskManager.changeTaskStatus(1, TaskStatus.DONE);

        assertEquals(TaskStatus.DONE, task.getStatus());
    }

    @Test
    void setTaskPriorityChangesPriority() {
        Task task = new Task(
                1,
                "Write tests",
                "Create unit tests",
                Priority.LOW
        );

        taskManager.createTask(task);

        taskManager.setTaskPriority(1, Priority.HIGH);

        assertEquals(Priority.HIGH, task.getPriority());
    }

    @Test
    void getUnfinishedTasksReturnsOnlyUnfinishedTasks() {
        Task task1 = new Task(
                1,
                "Create UML",
                "Create UML diagram",
                Priority.HIGH
        );

        Task task2 = new Task(
                2,
                "Write code",
                "Create Java classes",
                Priority.MEDIUM
        );

        Task task3 = new Task(
                3,
                "Write tests",
                "Create unit tests",
                Priority.LOW
        );

        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createTask(task3);

        taskManager.changeTaskStatus(1, TaskStatus.DONE);

        List<Task> unfinishedTasks = taskManager.getUnfinishedTasks();

        assertEquals(2, unfinishedTasks.size());
        assertFalse(unfinishedTasks.contains(task1));
        assertTrue(unfinishedTasks.contains(task2));
        assertTrue(unfinishedTasks.contains(task3));
    }
}