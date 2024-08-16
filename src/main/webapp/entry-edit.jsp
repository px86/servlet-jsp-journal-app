<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="io.github.px86.journalapp.model.JournalEntry"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
JournalEntry journalEntry = (JournalEntry) request.getAttribute("journalEntry");
String root = request.getContextPath() + "/journal/";
String pathToResource = root + journalEntry.getId();
%>

<html>
  <head>
    <title>Edit</title>
    <link href="<%= request.getContextPath() + "/style.css"%>" rel="stylesheet"/>
  </head>
  <body>
    <section class="content">
      <form action="<%= pathToResource %>" method="POST">
	<input name="id" type="hidden" type="number" value="<%= journalEntry.getId() %>"/>
	<input name="title" type="text" value="<%= journalEntry.getTitle() %>"<br/>
	<textarea name="body" ><%= journalEntry.getBody()%></textarea> <br/>
	<button type="submit">Save</button>
	<a href="<%= root %>">Cancel</a>
      </form>
    </section>
  </body>
</html>
