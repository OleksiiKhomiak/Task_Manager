package database;

import model.Priority;
import model.Task;
import model.TaskStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    public void save(Task task) {

        String sql = """
                INSERT INTO tasks (
                    id,
                    title,
                    description,
                    priority,
                    status
                )
                VALUES (?, ?, ?, ?, ?);
                """;

        try (
                Connection connection = DatabaseManager.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, task.getId());
            statement.setString(2, task.getTitle());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getPriority().name());
            statement.setString(5, task.getStatus().name());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Could not save task.",
                    e
            );
        }
    }

    public void update(Task task) {

        String sql = """
                UPDATE tasks
                SET title = ?,
                    description = ?,
                    priority = ?,
                    status = ?
                WHERE id = ?;
                """;

        try (
                Connection connection = DatabaseManager.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getPriority().name());
            statement.setString(4, task.getStatus().name());
            statement.setInt(5, task.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Could not update task.",
                    e
            );
        }
    }

    public void delete(int id) {

        String sql = """
                DELETE FROM tasks
                WHERE id = ?;
                """;

        try (
                Connection connection = DatabaseManager.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Could not delete task.",
                    e
            );
        }
    }

    public List<Task> findAll() {

        List<Task> tasks = new ArrayList<>();

        String sql = """
                SELECT id,
                       title,
                       description,
                       priority,
                       status
                FROM tasks
                ORDER BY id;
                """;

        try (
                Connection connection = DatabaseManager.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        Priority.valueOf(
                                resultSet.getString("priority")
                        )
                );

                task.setStatus(
                        TaskStatus.valueOf(
                                resultSet.getString("status")
                        )
                );

                tasks.add(task);
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Could not load tasks.",
                    e
            );
        }

        return tasks;
    }
}