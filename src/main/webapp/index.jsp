<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head><title>Hello World!</title></head>
  <body>
    <h1>Hello <%
	      String name = request.getParameter("name");
	      if (name!=null && name!="") {
		out.print(request.getParameter("name"));
	      } else {
		out.print("world");
	      }
	      %>!</h1>
  </body>
</html>
