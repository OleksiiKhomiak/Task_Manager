package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Priority;
import model.Task;
import model.TaskStatus;
import service.TaskManager;
import view.MainView;

public class TaskManagerApp extends Application {

    @Override
    public void start(Stage stage) {

        TaskManager taskManager = new TaskManager();

        Task task1 = new Task(
                1,
                "Finish PDP",
                "Finish Personal Development Plan",
                Priority.HIGH
        );

        Task task2 = new Task(
                2,
                "Build JavaFX interface",
                "Create GUI for Task Manager",
                Priority.MEDIUM
        );

        task2.setStatus(TaskStatus.IN_PROGRESS);

        taskManager.createTask(task1);
        taskManager.createTask(task2);

        MainView mainView = new MainView(taskManager);

        Scene scene = new Scene(
                mainView.createView(),
                1000,
                650
        );

        stage.setTitle("Task Manager");
        stage.setMinWidth(850);
        stage.setMinHeight(550);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}