<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head><title>Error!</title></head>
  <link href="<%= request.getContextPath() + "/css/style.css"%>" rel="stylesheet"/>
  <body>
    <main>
      <%= request.getAttribute("errorMessage") %>
    </main>

  </body>
</html>
