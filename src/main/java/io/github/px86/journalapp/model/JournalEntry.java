package io.github.px86.journalapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class JournalEntry {
  private Long id;
  private String title;
  private String body;
  private String lastModified;

  public JournalEntry() {}

  public static String timeStringOf(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
    sdf.setTimeZone(TimeZone.getTimeZone("IST"));
    if (date == null) {
      date = new Date();
    }
    return sdf.format(date);
  }

  public String getLastModified() {
    return this.lastModified;
  }

  public void setLastModified(String lastModified) {
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
    return this.title + "|" + this.body + "|" + this.lastModified;
  }
}
