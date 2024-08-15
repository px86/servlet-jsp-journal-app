<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="io.github.px86.journalapp.model.JournalEntry"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
JournalEntry entry = (JournalEntry)request.getAttribute("journalEntry");
String root = request.getContextPath() + "/journal/";
String pathToResource = root + entry.getId();
%>

<html>
  <head>
    <title><%= entry.getTitle() %></title>
    <link href="<%= request.getContextPath() + "/style.css"%>" rel="stylesheet"/>
  </head>
  <body>
    <main class="entry">
	<div>
	  <h2 class="title"><%= entry.getTitle() %></h2>
	  <p><%= entry.getLastModified() %></p>

	  <a href="<%= pathToResource + "/edit" %>">Edit</a>
	  <a href="<%= pathToResource + "/delete" %>">Delete</a>
	  <a href="<%= root %>">Home</a>

	  <p class="body"><%= entry.getBody() %></p>
	</div>
    </main>
  </body>
</html>
