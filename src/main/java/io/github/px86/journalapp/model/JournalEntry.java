package io.github.px86.journalapp.model;

import java.util.Date;

public class JournalEntry {
  private Long id;
  private String title;
  private String body;
  private Date lastModified;

  public JournalEntry() {}

  public Date getLastModified() {
    return this.lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    if (title != null && title != "") {
      this.title = title;
    } else {
      this.title = "Untitled";
    }
  }

  public String getBody() {
    return this.body;
  }

  public void setBody(String body) {
    if (body != null) {
      this.body = body;
    }
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String toString() {
    return this.title + "(" + this.getId() + ")";
  }
}
