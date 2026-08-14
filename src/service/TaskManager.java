package service;

import model.Priority;
import model.Project;
import model.Task;
import model.TaskStatus;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private List<User> users;
    private List<Project> projects;
    private List<Task> tasks;

    public TaskManager() {
        this.users = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void createTask(Task task) {
        tasks.add(task);
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
}