package io.github.px86.journalapp.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class DatabaseHelper {

  public static void main(String[] args) throws Exception {

    Properties properties = new Properties();
    properties.load(
        DatabaseHelper.class
            .getClassLoader()
            .getResourceAsStream("application.properties"));
    String jdbcURL = properties.getProperty("jdbcURL");

    Class.forName("org.sqlite.JDBC");
    Connection dbConnection = DriverManager.getConnection(jdbcURL);
    if (dbConnection == null) {
      throw new Exception("failed to connect to sqlite database!");
    }
    PreparedStatement createTableQuery =
        dbConnection.prepareStatement(
            """
            CREATE TABLE IF NOT EXISTS journal_entries(
              id INTEGER PRIMARY KEY,
              title VARCHAR(256),
              body TEXT,
              last_modified CHAR(22)
            );""");
    createTableQuery.executeUpdate();

    PreparedStatement insertRowsQuery =
        dbConnection.prepareStatement(
            """
            INSERT INTO journal_entries(title, body, last_modified) VALUES
              ("Test journal 1", "1 - This journal is for testing purposes only.", "1900-01-01 00:00:00 AM"),
              ("Test journal 2", "2 - This journal is for testing purposes only.", "1900-01-01 00:00:00 AM");""");

    int rowsAffected = insertRowsQuery.executeUpdate();
    System.out.println("Rows affected by insertRowsQuery = " + rowsAffected);

    dbConnection.close();
  }
}
