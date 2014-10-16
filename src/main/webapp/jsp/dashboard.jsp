<jsp:include page="../include/header.jsp" />
<section id="main">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<h1 id="homeTitle">456 Computers found</h1>
	<div id="actions">
		<form action="" method="GET">
			<input type="search" id="searchbox" name="searchString" value=""
				placeholder="Search name"> <select id="limit" name="limit">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
			</select> <input type="submit" id="searchsubmit" value="Filter by name"
				class="btn primary">
		</form>
		<script>
			$('#searchbox').attr('value', '${page.searchString}')
			$('#limit').children('option[value=${page.limit}]').attr(
					'selected', 'selected')
		</script>
		<a class="btn success" id="add" href="AddComputer">Add Computer</a>
	</div>

	<c:if test="${not empty message and error }">
		<p style="color: red">${message}</p>
	</c:if>
	<c:if test="${not empty message and empty error }">
		<p style="color: green">${message}</p>
	</c:if>
	<table>
		<thead>
			<tr>
				<th>Nom</th>
				<th>IntroducedDate</th>
				<th>DiscontinuedDate</th>
				<th>Company</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${computers}" var="computer">
				<tr>
					<td>${computer.computerName}</td>
					<td>${computer.introducedDate}</td>
					<td>${computer.discontinuedDate}</td>
					<td>${computer.companyDto.name}</td>
					<td>${computer.modifier}</td>
					<td>${computer.supprimer}</td>


				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- <ex:Hello /> -->
</section>

<jsp:include page="../include/footer.jsp" />
