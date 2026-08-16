package service;

import model.Priority;
import model.Project;
import model.Task;
import model.TaskStatus;
import model.User;

import java.util.ArrayList;
import java.util.List;

import database.TaskRepository;

public class TaskManager {

    private TaskRepository taskRepository;
    private List<User> users;
    private List<Project> projects;
    private List<Task> tasks;

    public TaskManager() {
        this(null);
    }

    public TaskManager(TaskRepository taskRepository) {
        this.users = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.tasks = new ArrayList<>();

        this.taskRepository = taskRepository;

        if (taskRepository != null) {
            this.tasks.addAll(
                    taskRepository.findAll()
            );
        }
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void createTask(Task task) {

        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }

        if (getTaskById(task.getId()) != null) {
            throw new IllegalArgumentException(
                    "Task with ID " + task.getId() + " already exists."
            );
        }

        tasks.add(task);

        if (taskRepository != null) {
            taskRepository.save(task);
        }
    }

    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }

        return null;
    }

    public void changeTaskStatus(int id, TaskStatus status) {
        Task task = getTaskById(id);

        if (task != null) {
            task.setStatus(status);
        }
    }

    public void setTaskPriority(int id, Priority priority) {
        Task task = getTaskById(id);

        if (task != null) {
            task.setPriority(priority);
        }
    }

    public List<Task> getUnfinishedTasks() {
        List<Task> unfinishedTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getStatus() != TaskStatus.DONE) {
                unfinishedTasks.add(task);
            }
        }

        return unfinishedTasks;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public boolean updateTask(
            int id,
            String title,
            String description,
            Priority priority,
            TaskStatus status
    ) {

        Task task = getTaskById(id);

        if (task == null) {
            return false;
        }

        task.setTitle(title);
        task.setDescription(description);
        task.setPriority(priority);
        task.setStatus(status);

        if (taskRepository != null) {
            taskRepository.update(task);
        }

        return true;
    }

    public boolean removeTask(int id) {

        Task task = getTaskById(id);

        if (task == null) {
            return false;
        }

        boolean removed = tasks.remove(task);

        if (removed && taskRepository != null) {
            taskRepository.delete(id);
        }

        return removed;
    }
}