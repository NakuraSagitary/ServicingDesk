<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/app/producao/salvar" var="urlSalvarProducao" />


<!DOCTYPE html>
<html>
<head>
<c:import url="../templates/head.jsp" />
</head>
<body>
	<c:import url="../templates/header.jsp" />
	<main class="container read-container">
	<h1>Salvar Item de Produção</h1>
	<form:form action="${urlSalvarProducao}" method="post"
		class="grid-flex" modelAttribute="itemProducao" id="meuForm"
		onchange="calcula();">
		<form:hidden path="id" />
		<div class="row">
			<div class="col flex-1">
				<form:label path="data">data</form:label>
				<form:input path="data" id="data" type="date" />
				<form:errors path="data" cssClass="erro" element="div" />
			</div>
		</div>

		<div class="row">
			<div class="col flex-1">
				<form:label path="item">ItemSelecionado</form:label>
				
				<form:select path="item.id" id="item" >
					<c:forEach items="${itens}" var="obj">
						<form:option value="${obj.id}" data-pre="${obj.prejuizoEstimadoPorPeca}"> ${obj.nomePeca} </form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>

		<div class="row">
			<div class="col flex-1">
				<form:label path="itensAprovados">itensAprovados</form:label>
				<form:input path="itensAprovados" id="aprovados" />
				<form:errors path="itensAprovados" cssClass="erro" element="div" />
			</div>
		</div>

		<div class="row">
			<div class="col flex-1">
				<form:label path="itensReprovados">itensReprovados</form:label>
				<form:input path="itensReprovados" id="reprovados" />
				<form:errors path="itensReprovados" cssClass="erro" element="div" />
			</div>
		</div>

		<div class="row">
			<div class="col flex-1">
				<form:label path="itensProduzidos">itensProduzidos</form:label>
				<form:input path="itensProduzidos" disabled="true" />
				<form:errors path="itensProduzidos" cssClass="erro" element="div" />
			</div>
		</div>

		<div class="row">
			<div class="col flex-1">
				<form:label path="prejuizoEstimado">prejuizoEstimado</form:label>
				<form:input path="prejuizoEstimado" disabled="true" />
				<form:errors path="prejuizoEstimado" cssClass="erro" element="div" />
			</div>
		</div>

		<div class="row">
			<div class="col flex-1">
				<form:label path="emailGerente">E-mail</form:label>
				<form:input path="emailGerente" disabled="true" />
				<form:errors path="emailGerente" cssClass="erro" element="div" />
			</div>
		</div>

		<p id="mensagemDeErro"></p>

		<div class="row btn-group">
			<button type="submit" class="btn btn-blue col flex-1"
				onclick="return validate()">SALVAR</button>
		</div>
	</form:form> </main>

	<script type="text/javascript">
		function calcula() {
			
			var e = document.getElementById("item");
			var preju = e.options[e.selectedIndex].getAttribute('data-pre');
			
			var itensAprovados = meuForm.itensAprovados.value;
			var itensReprovados = meuForm.itensReprovados.value;

			var itensProduzidos = itensAprovados - itensReprovados;
			
			var prejuTotal = preju * itensReprovados;

			if (meuForm.itensAprovados.value != 0
					&& meuForm.itensReprovados.value != 0) {
				meuForm.itensProduzidos.value = itensProduzidos;
				meuForm.prejuizoEstimado.value = prejuTotal;
			}

			if (meuForm.itensReprovados.value > 10) {
				meuForm.emailGerente.disabled = false;
			}

		}

		function validate() {

			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth() + 1;
			var yyyy = today.getFullYear();

			if (dd < 10) {
				dd = '0' + dd
			}

			if (mm < 10) {
				mm = '0' + mm
			}

			today = yyyy + '-' + mm + '-' + dd;

			if (document.getElementById("data").value > today) {
				document.getElementById("mensagemDeErro").textContent = "A data de produção não pode ser superior a data de hoje";
				return false;
			} else if (parseInt(document.getElementById("aprovados").value) <= parseInt(document
					.getElementById("reprovados").value)) {
				document.getElementById("mensagemDeErro").textContent = "A quantidade de peças reprovadas não pode ser maior do que a quantidade de peças aprovadas."
				return false;
			}

			alert("Produto cadastrado com sucesso.");

		}
	</script>

</body>
</html>