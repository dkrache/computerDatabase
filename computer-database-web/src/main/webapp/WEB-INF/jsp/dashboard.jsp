<jsp:include page="/include/header.jsp" />
<section id="main">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib tagdir="/WEB-INF/tags" prefix="pagination"%>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	<c:choose>
		<c:when test="${page.totalCount < 2}">
			<h1 id="homeTitle">${page.totalCount}
				<spring:message code="front.dashboard.computer.title" />
			</h1>
		</c:when>
		<c:otherwise>
			<h1 id="homeTitle">${page.totalCount}
				<spring:message code="front.dashboard.computers.title" />
			</h1>
		</c:otherwise>
	</c:choose>
	<c:if test="${not empty message and error }">
		<p style="color: red"><spring:message code="${message}"/></p>
	</c:if>
	<c:if test="${not empty message and empty error }">
		<p style="color: green"><spring:message code="${message}"/></p> 
	</c:if>
	<div id="actions">
		<form action="" method="GET">
			<input type="search" id="searchbox" name="searchString" value=""
				placeholder="Search name"> <select id="limit" name="limit">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
			</select> <input type="submit" id="searchsubmit" value="<spring:message code="front.dashboard.computer.button.filter"/>"
				class="btn primary">
		</form>
		<script>
			$('#searchbox').attr('value', '${page.searchString}')
			$('#limit').children('option[value=${page.limit}]').attr(
					'selected', 'selected')
		</script>
		<a class="btn success" id="add" href="/computer-database-web/computer/add"><spring:message code="front.dashboard.computer.button.addComputer"/></a>
	</div>

	<table>
		<thead>
			<tr>
				<pagination:thSort page="${page}" fieldName="front.dashboard.computer.computerName" var="name"
					url="${pageContext.request.contextPath}" />
				<pagination:thSort page="${page}" fieldName="front.dashboard.computer.introducedDate"
					var="idate" url="${pageContext.request.contextPath}" />
				<pagination:thSort page="${page}" fieldName="front.dashboard.computer.discontinuedDate"
					var="ddate" url="${pageContext.request.contextPath}" />
				<pagination:thSort page="${page}" fieldName="front.dashboard.computer.companyName" var="comp"
					url="${pageContext.request.contextPath}" />
				<th><spring:message code="front.dashboard.computer.update"/></th>
				<th><spring:message code="front.dashboard.computer.delete"/></th>
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

	<div id="pagination" class="pagination">
		<pagination:pagination page="${page}" />
	</div>
</section>

<jsp:include page="/include/footer.jsp" />
