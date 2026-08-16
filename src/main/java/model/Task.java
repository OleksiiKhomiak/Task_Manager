package model;

public class Task {

    private int id;
    private String title;
    private String description;
    private Priority priority;
    private TaskStatus status;
    private User assignedUser;

    public Task(int id, String title, String description, Priority priority) {

        if (id <= 0) {
            throw new IllegalArgumentException("Task ID must be greater than 0.");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Task title cannot be empty.");
        }

        if (priority == null) {
            throw new IllegalArgumentException("Task priority cannot be null.");
        }

        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = TaskStatus.TODO;
        this.assignedUser = null;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setPriority(Priority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("Task priority cannot be null.");
        }

        this.priority = priority;
    }

    public void setStatus(TaskStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Task status cannot be null.");
        }

        this.status = status;
    }

    public void assignUser(User user) {
        this.assignedUser = user;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Task title cannot be empty.");
        }

        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}