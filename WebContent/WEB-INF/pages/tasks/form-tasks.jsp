<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastra task</title>
<link type="text/css" href="resources/css/tasks.css" rel="stylesheet" />
</head>
<body>
	<h2>Formulário de cadastro das <i>tasks</i></h2>
	<form action="cadastratask" method="post">
		<h3>Descrição:</h3>
		<textarea rows="5" cols="100" name="descricao"></textarea><br />
		<form:errors path="task.descricao" cssStyle="color: red; font-weight: bold"></form:errors><br />
		<input type="submit" value="Cadastrar" />
	</form>
</body>
</html>