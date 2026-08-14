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
        this.priority = priority;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void assignUser(User user) {
        this.assignedUser = user;
    }
}