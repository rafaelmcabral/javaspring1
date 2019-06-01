<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastra task</title>
<script type="text/javascript">
function habilitaDataFinalizacao(radio) {
	if (radio.checked == true && radio.value == "true") {
		document.getElementById("dataFinalizacao").disabled = false;
	} else {
		document.getElementById("dataFinalizacao").disabled = true;
	}
}
</script>
</head>
<body>
	<h2>Formulário de alteração das <i>tasks</i></h2>
	<form action="editatask" method="post">
		<input type="hidden" name="id" value="${task.id}" />
		<h3>Descrição:</h3>
		<textarea rows="5" cols="100" name="descricao">${task.descricao}</textarea><br />
		<form:errors path="task.descricao" cssStyle="color: red; font-weight: bold"></form:errors><br />
		<h3>Finalizar Task:</h3>
		<input type="radio" name="finalizada" id="finalizadaSim" value="true" ${task.isFinalizada()?'checked':'' } onchange="habilitaDataFinalizacao(this)" />
		<label for="finalizadaSim">Sim</label>
		<input type="radio" name="finalizada" id="finalizadaNao" value="false" ${!task.isFinalizada()?'checked':'' } onchange="habilitaDataFinalizacao(this)" />
		<label for="finalizadaSim">Não</label>
		<h3>Data Finalização</h3>
		<input type="date" name="dataFinalizacao" id="dataFinalizacao" disabled="${!task.isFinalizada()?'false':'true' }" value="<fmt:formatDate value='${task.getDataFinalizacao().time}' pattern='dd/MM/yyyy' />" />
		<br /><br />
		<input type="submit" value="Alterar" />
	</form>
</body>
</html>