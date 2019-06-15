<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<td>${task.getId()}</td>
<td>${task.getDescricao()}</td>
<td>Finalizada</td>
<td><fmt:formatDate value="${task.getDataFinalizacao().time}" pattern="dd/MM/yyyy" /></td>
<td><a href="javascript:void(0)" onclick="return excluir(${task.getId()})">Excluir</a></td>
<td><a href="javascript:void(0)" onclick="editar(${task.getId()})">Editar</a></td>