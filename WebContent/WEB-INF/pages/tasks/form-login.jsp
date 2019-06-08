<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" href="resources/css/tasks.css" rel="stylesheet" />
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Login Tasks</h2>
	<form action="getlogin" method="post">
		<h3>Login:</h3>
		<input type="text" name="login" />
		<br />
		<h3>Senha:</h3>
		<input type="password" name="senha" />
		<br />
		<input type="submit" value="Ok" />
	</form>
</body>
</html>