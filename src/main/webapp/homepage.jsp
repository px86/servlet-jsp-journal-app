<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="io.github.px86.journalapp.model.JournalEntry"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
List<JournalEntry> entries = (List<JournalEntry>) request.getAttribute("journalEntries");
String root = request.getContextPath() + "/journal/";
String newEntryPath = root + "new";
%>

<html>
  <head><title>All Journal Entries</title></head>
  <link href="<%= request.getContextPath() + "/style.css"%>" rel="stylesheet"/>
  <body>
    <h1>All Journal Entries</h1>

    <a href="<%= newEntryPath %>">New Entry</a>

    <section id="entries">
      <%for (JournalEntry entry: entries) {%>
	<section class="entry">

	  <a href="<%= root + entry.getId() %>"><h2 class="title"><%= entry.getTitle() %></h2></a>
	  <p class="body"><%= entry.getBody() %></p>

	  <div class="metadata">
	    <span class="id"><%= entry.getId() %></span>
	    <span class="last-modified"><%= entry.getLastModified() %></span>

	    <a href="<%= root + entry.getId() + "/delete"%>">Delete</a>
	    <a href="<%= root + entry.getId() + "/edit"%>">Edit</a>

	  </div>
	</section>
      <%}%>
    </section>

  </body>
</html>
