package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Priority;
import model.Task;
import model.TaskStatus;
import service.TaskManager;
import view.MainView;
import database.DatabaseManager;
import database.TaskRepository;

public class TaskManagerApp extends Application {

    @Override
    public void start(Stage stage) {
        DatabaseManager.initializeDatabase();

        TaskRepository taskRepository =
                new TaskRepository();

        TaskManager taskManager =
                new TaskManager(taskRepository);

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