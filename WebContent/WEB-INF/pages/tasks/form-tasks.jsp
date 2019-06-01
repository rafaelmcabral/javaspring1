<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastra task</title>
</head>
<body>
	<h2>Formulário de cadastro das <i>tasks</i></h2>
	<form action="cadastratask" method="post">
		<h3>Descrição:</h3>
		<textarea rows="5" cols="100" name="descricao"></textarea><br />
		<input type="submit" value="Cadastrar" />
	</form>
</body>
</html>