<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
String root = request.getContextPath() + "/journal/";
%>

<html>
  <head>
    <title>New Entry</title>
    <link href="<%= request.getContextPath() + "/css/style.css"%>" rel="stylesheet"/>
  </head>
  <body>
    <section class="content">
      <form action="<%= root %>" method="POST">
	<input name="title" type="text" value="" placeholder="Enter title here" /><br/>
	<textarea name="body">What's on your mind?</textarea> <br/>
	<button type="submit">Save</button>
	<a href="<%= root %>">Cancel</a>
      </form>
    </section>
  </body>
</html>
