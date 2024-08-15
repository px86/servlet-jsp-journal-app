package io.github.px86.journalapp.service;

import io.github.px86.journalapp.model.JournalEntry;
import io.github.px86.journalapp.repository.JournalEntryRepository;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JournalEntryService {
  private JournalEntryRepository repo;

  public JournalEntryService() throws Exception {
    this.repo = new JournalEntryRepository();
  }

  public Optional<JournalEntry> findById(Long id) {
    return repo.findById(id);
  }

  public List<JournalEntry> findAll() {
    return repo.findAll();
  }

  public void save(JournalEntry journalEntry) {
    try {
      this.repo.save(journalEntry);
    } catch (SQLException e) {
      System.err.println(e);
    }
  }

  public void deleteById(Long id) {
    try {
      this.repo.deleteById(id);
    } catch (SQLException e) {
      System.err.println(e);
    }
  }

  public void update(JournalEntry journalEntry) {
    try {
      this.repo.update(journalEntry);
    } catch (SQLException e) {
      System.err.println(e);
    }
  }
}
