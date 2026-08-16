package database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DATABASE_FOLDER = "data";
    private static final String DATABASE_URL =
            "jdbc:sqlite:data/taskmanager.db";

    public static Connection getConnection() throws SQLException {
        createDatabaseFolder();

        return DriverManager.getConnection(DATABASE_URL);
    }

    private static void createDatabaseFolder() {
        try {
            Files.createDirectories(
                    Path.of(DATABASE_FOLDER)
            );
        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not create database folder.",
                    e
            );
        }
    }

    public static void initializeDatabase() {

        String sql = """
                CREATE TABLE IF NOT EXISTS tasks (
                    id INTEGER PRIMARY KEY,
                    title TEXT NOT NULL,
                    description TEXT,
                    priority TEXT NOT NULL,
                    status TEXT NOT NULL
                );
                """;

        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.execute(sql);

            System.out.println(
                    "Database initialized successfully."
            );

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Could not initialize database.",
                    e
            );
        }
    }
}