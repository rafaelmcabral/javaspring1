<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listagem de tasks</title>
<script type="text/javascript">
function excluir(id) {
	if (confirm("Deseja excluir a task " + id + "?")) {
		window.location.href = "excluitask?id=" + id;
		return true;
	}
	return false;
}

function editar(id) {
	window.location.href = "buscartask?id=" + id;
}
</script>
</head>
<body>
<a href="novatask">Inserir nova task</a>
<br /><br />
<table border="1" cellspacing="0" cellpadding="0">
	<tr>
		<th>Id</th>
		<th>Descri��o</th>
		<th>Finalizada</th>
		<th>Data de finaliza��o</th>
		<th>A��o 1</th>
		<th>A��o 2</th>
	</tr>
	<c:forEach items="${tasks}" var="task">
	<tr>
		<td>${task.getId()}</td>
		<td>${task.getDescricao()}</td>
		<c:if test="${task.isFinalizada() eq false }">
			<td>N�o finalizada</td>
		</c:if>
		<c:if test="${task.isFinalizada() eq true }">
			<td>Finalizada</td>
		</c:if>
		<td><fmt:formatDate value="${task.getDataFinalizacao().time}" pattern="dd/MM/yyyy" /></td>
		<td><a href="javascript:void(0)" onclick="return excluir(${task.getId()})">Excluir</a></td>
		<td><a href="javascript:void(0)" onclick="editar(${task.getId()})">Editar</a></td>
	</tr>
	</c:forEach>
</table>
</body>
</html>