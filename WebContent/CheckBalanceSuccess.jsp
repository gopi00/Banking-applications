<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Check Balance page</title>
</head>
<body>
Dear Customer,
Your balance is<%=session.getAttribute("balance")%>
</body>
</html>