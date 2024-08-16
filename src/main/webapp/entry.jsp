<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="io.github.px86.journalapp.model.JournalEntry"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
JournalEntry entry = (JournalEntry)request.getAttribute("journalEntry");
String root = request.getContextPath() + "/journal/";
String pathToResource = root + entry.getId();
String deleteURL = pathToResource + "/delete";
String editURL = pathToResource + "/edit";
%>

<html>
  <head>
    <title><%= entry.getTitle() %></title>
    <link href="<%= request.getContextPath() + "/style.css"%>" rel="stylesheet"/>
  </head>
  <body>

    <section class="content">
      <main class="entry">
	<h1 class="title"><%= entry.getTitle() %></h1>
 	<span class="last-modified"><%= entry.getLastModified() %></span>

	<div class="controls">
	  <span class="btn edit-btn"><a href="<%= editURL %>">Edit</a></span>
	  <span class="btn delete-btn"><a href="<%= deleteURL %>">Delete</a></span>
	  <span class="btn home-btn"><a href="<%= root %>">Home</a></span>
	</div>

	<p class="body"><%= entry.getBody() %></p>
      </main>
    </section>

  </body>
</html>
