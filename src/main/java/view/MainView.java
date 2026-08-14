package view;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Task;
import service.TaskManager;

public class MainView {

    private final TaskManager taskManager;
    private final ObservableList<Task> tasks;

    public MainView(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.tasks = FXCollections.observableArrayList(
                taskManager.getAllTasks()
        );
    }

    public Parent createView() {

        BorderPane root = new BorderPane();

        root.setLeft(createSidebar());
        root.setTop(createTopBar());
        root.setCenter(createTaskTable());

        root.setStyle("""
                -fx-background-color: #f5f5f5;
                """);

        return root;
    }

    private VBox createSidebar() {

        Label projectsTitle = new Label("Projects");
        projectsTitle.setStyle("""
                -fx-font-size: 20px;
                -fx-font-weight: bold;
                """);

        Button allTasksButton = new Button("All Tasks");
        Button schoolButton = new Button("School");
        Button personalButton = new Button("Personal");
        Button newProjectButton = new Button("+ Project");

        allTasksButton.setMaxWidth(Double.MAX_VALUE);
        schoolButton.setMaxWidth(Double.MAX_VALUE);
        personalButton.setMaxWidth(Double.MAX_VALUE);
        newProjectButton.setMaxWidth(Double.MAX_VALUE);

        VBox sidebar = new VBox(
                12,
                projectsTitle,
                allTasksButton,
                schoolButton,
                personalButton,
                newProjectButton
        );

        sidebar.setPadding(new Insets(25));
        sidebar.setPrefWidth(200);

        sidebar.setStyle("""
                -fx-background-color: white;
                -fx-border-color: #dddddd;
                -fx-border-width: 0 1 0 0;
                """);

        return sidebar;
    }

    private HBox createTopBar() {

        Label title = new Label("My Tasks");

        title.setStyle("""
                -fx-font-size: 26px;
                -fx-font-weight: bold;
                """);

        Button newTaskButton = new Button("+ New Task");
        newTaskButton.setOnAction(event -> showNewTaskDialog());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox topBar = new HBox(
                20,
                title,
                spacer,
                newTaskButton
        );

        topBar.setPadding(new Insets(20, 25, 20, 25));

        return topBar;
    }

    private TableView<Task> createTaskTable() {

        TableView<Task> table = new TableView<>();

        TableColumn<Task, String> titleColumn =
                new TableColumn<>("Title");

        titleColumn.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(
                        data.getValue().getTitle()
                )
        );

        TableColumn<Task, String> priorityColumn =
                new TableColumn<>("Priority");

        priorityColumn.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(
                        data.getValue().getPriority().name()
                )
        );

        TableColumn<Task, String> statusColumn =
                new TableColumn<>("Status");

        statusColumn.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(
                        data.getValue().getStatus().name()
                )
        );

        titleColumn.setPrefWidth(350);
        priorityColumn.setPrefWidth(130);
        statusColumn.setPrefWidth(170);

        table.getColumns().addAll(
                titleColumn,
                priorityColumn,
                statusColumn
        );

        table.setItems(tasks);

        BorderPane.setMargin(
                table,
                new Insets(0, 25, 25, 25)
        );

        return table;
    }
    private void showNewTaskDialog() {

        Dialog<Task> dialog = new Dialog<>();

        dialog.setTitle("New Task");
        dialog.setHeaderText("Create a new task");

        ButtonType createButtonType = new ButtonType(
                "Create",
                ButtonBar.ButtonData.OK_DONE
        );

        dialog.getDialogPane().getButtonTypes().addAll(
                createButtonType,
                ButtonType.CANCEL
        );

        TextField titleField = new TextField();
        titleField.setPromptText("Task title");

        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Description");
        descriptionArea.setPrefRowCount(4);

        ComboBox<model.Priority> priorityBox = new ComboBox<>();
        priorityBox.getItems().addAll(model.Priority.values());
        priorityBox.setValue(model.Priority.MEDIUM);

        ComboBox<model.TaskStatus> statusBox = new ComboBox<>();
        statusBox.getItems().addAll(model.TaskStatus.values());
        statusBox.setValue(model.TaskStatus.TODO);

        VBox form = new VBox(
                10,
                new Label("Title"),
                titleField,
                new Label("Description"),
                descriptionArea,
                new Label("Priority"),
                priorityBox,
                new Label("Status"),
                statusBox
        );

        form.setPadding(new Insets(10));

        dialog.getDialogPane().setContent(form);

        Button createButton =
                (Button) dialog.getDialogPane()
                        .lookupButton(createButtonType);

        createButton.setDisable(true);

        titleField.textProperty().addListener(
                (observable, oldValue, newValue) ->
                        createButton.setDisable(
                                newValue == null || newValue.isBlank()
                        )
        );

        dialog.setResultConverter(button -> {

            if (button == createButtonType) {

                int newId = getNextTaskId();

                Task task = new Task(
                        newId,
                        titleField.getText(),
                        descriptionArea.getText(),
                        priorityBox.getValue()
                );

                task.setStatus(statusBox.getValue());

                return task;
            }

            return null;
        });

        dialog.showAndWait().ifPresent(task -> {
            taskManager.createTask(task);
            tasks.add(task);
        });
    }

    private int getNextTaskId() {

        int maxId = 0;

        for (Task task : taskManager.getAllTasks()) {
            if (task.getId() > maxId) {
                maxId = task.getId();
            }
        }

        return maxId + 1;
    }
}