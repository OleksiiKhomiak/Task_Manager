import model.Priority;
import model.Project;
import model.Task;
import model.TaskStatus;
import model.User;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        User user1 = new User(
                1,
                "Oleksii",
                "oleksii@example.com"
        );

        taskManager.addUser(user1);

        Project project = new Project(
                1,
                "PDP Task Manager"
        );

        taskManager.addProject(project);

        Task task1 = new Task(
                1,
                "Create UML diagram",
                "Create UML diagram for the Task Manager project",
                Priority.HIGH
        );

        Task task2 = new Task(
                2,
                "Create Java classes",
                "Create the main OOP classes",
                Priority.MEDIUM
        );

        Task task3 = new Task(
                3,
                "Write unit tests",
                "Test the main Task Manager functionality",
                Priority.LOW
        );

        task1.assignUser(user1);
        task2.assignUser(user1);
        task3.assignUser(user1);

        project.addTask(task1);
        project.addTask(task2);
        project.addTask(task3);

        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createTask(task3);

        taskManager.changeTaskStatus(
                1,
                TaskStatus.DONE
        );

        taskManager.changeTaskStatus(
                2,
                TaskStatus.IN_PROGRESS
        );

        taskManager.setTaskPriority(
                3,
                Priority.HIGH
        );

        System.out.println("All tasks:");
        System.out.println("--------------------");

        for (Task task : project.getTasks()) {
            System.out.println(
                    task.getId()
                            + " | "
                            + task.getTitle()
                            + " | "
                            + task.getPriority()
                            + " | "
                            + task.getStatus()
            );
        }

        System.out.println();
        System.out.println("Unfinished tasks:");
        System.out.println("--------------------");

        for (Task task : taskManager.getUnfinishedTasks()) {
            System.out.println(
                    task.getTitle()
                            + " | "
                            + task.getStatus()
            );
        }
    }
}