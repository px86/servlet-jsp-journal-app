package io.github.px86.journalapp.repository;

import io.github.px86.journalapp.model.JournalEntry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class JournalEntryRepository {
  private Connection dbConnection;

  public JournalEntryRepository() throws Exception {

    Class.forName("org.sqlite.JDBC");

    Properties properties = new Properties();
    properties.load(
        JournalEntryRepository.class
            .getClassLoader()
            .getResourceAsStream("application.properties"));
    String jdbcURL = properties.getProperty("jdbcURL");

    this.dbConnection = DriverManager.getConnection(jdbcURL);
    if (this.dbConnection == null) {
      throw new Exception("database not initilized!");
    }
  }

  public Optional<JournalEntry> findById(Long id) {
    try {
      PreparedStatement ps =
          this.dbConnection.prepareStatement("SELECT * FROM journal_entries WHERE id=?");
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setId(rs.getLong("id"));
        journalEntry.setTitle(rs.getString("title"));
        journalEntry.setBody(rs.getString("body"));
        journalEntry.setLastModified(rs.getString("last_modified"));
        return Optional.of(journalEntry);
      }

    } catch (SQLException sqlException) {
      System.err.println(sqlException);
    }
    return Optional.empty();
  }

  public List<JournalEntry> findAll() {
    List<JournalEntry> journalEntries = new ArrayList<>();
    try {
      PreparedStatement ps = this.dbConnection.prepareStatement("SELECT * FROM journal_entries");
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setId(rs.getLong("id"));
        journalEntry.setTitle(rs.getString("title"));
        journalEntry.setBody(rs.getString("body"));
        journalEntry.setLastModified(rs.getString("last_modified"));

        journalEntries.add(journalEntry);
      }

    } catch (SQLException sqlException) {
      System.err.println(sqlException);
    }
    return journalEntries;
  }

  public void save(JournalEntry journalEntry) throws SQLException {
    PreparedStatement insertStatement =
        this.dbConnection.prepareStatement(
            "INSERT into journal_entries(title, body, last_modified) VALUES (?, ?, ?);");
    insertStatement.setString(1, journalEntry.getTitle());
    insertStatement.setString(2, journalEntry.getBody());
    insertStatement.setString(3, journalEntry.getLastModified());
    insertStatement.executeUpdate();
  }

  public void deleteById(Long id) throws SQLException {
    PreparedStatement deleteStatement =
        this.dbConnection.prepareStatement("DELETE FROM journal_entries WHERE id=?");
    deleteStatement.setLong(1, id);
    deleteStatement.executeUpdate();
  }

  public void update(JournalEntry journalEntry) throws SQLException {
    PreparedStatement updateStatement =
        this.dbConnection.prepareStatement(
            "UPDATE journal_entries SET title=?, body=?, last_modified=? WHERE id=?");
    updateStatement.setString(1, journalEntry.getTitle());
    updateStatement.setString(2, journalEntry.getBody());
    updateStatement.setString(3, journalEntry.getLastModified());
    updateStatement.setLong(4, journalEntry.getId());
    updateStatement.executeUpdate();
  }
}
