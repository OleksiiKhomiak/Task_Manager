package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskManagerApp extends Application {

    @Override
    public void start(Stage stage) {
        Label title = new Label("Task Manager");
        Button newTaskButton = new Button("New Task");

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, newTaskButton);

        Scene scene = new Scene(layout, 800, 500);

        stage.setTitle("Task Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}