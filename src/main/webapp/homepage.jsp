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
  <head>
    <title>All Journal Entries</title>
  </head>
  <link href="<%= request.getContextPath() + "/style.css"%>" rel="stylesheet"/>
  <body>
    <section class="content">

      <div class="header">
	<h1>All Journal Entries</h1>
	<span><a id="new-entry-link" href="<%= newEntryPath %>">New Entry</a></span>
      </div>

      <section id="entries">

	<%for (JournalEntry entry: entries) {
	  Long id = entry.getId();
	  String resourceURL = root + id;
	  String deleteURL = resourceURL + "/delete";
	  String editURL = resourceURL + "/edit";
	%>

	<div class="entry">
	  <span class="last-modified"><%= entry.getLastModified() %></span>
	  <span class="title"><a href="<%= resourceURL %>"><%= entry.getTitle() %></a></span>
	  <span class="btn delete-btn"><a href="<%= deleteURL %>">Delete</a></span>
	  <span class="btn edit-btn"><a href="<%= editURL %>"    >Edit</a></span>
	</div>

      <%}%>
      </section>
    </section>

  </body>
</html>
