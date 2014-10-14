<jsp:include page="../include/header.jsp" />
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<section id="main">
	<h1 id="homeTitle">456 Computers found</h1>
	<div id="actions">
		<form action="" method="GET">
			<input type="search" id="searchbox" name="search" value=""
				placeholder="Search name"> <input type="submit"
				id="searchsubmit" value="Filter by name" class="btn primary">
		</form>
		<a class="btn success" id="add" href="AddComputer">Add Computer</a>
	</div>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:if test="${not empty message and error }">
		<p style="color: red">${message}</p>
	</c:if>
	<c:if test="${not empty message and empty error }">
		<p style="color: green">${message}</p>
	</c:if>

	<display:table name="computers" pagesize="20" size="resultSize"
		requestURI="Accueil">
		<display:column property="computerName" title="Nom" sortable="true" />
		<display:column property="introducedDate" title="Introduction"
			sortable="true" />
		<display:column property="discontinuedDate" title="Arrêt"
			sortable="true" />
		<display:column property="companyDto.name" title="Compagnie"
			sortable="true" />
		<display:column property="supprimer" title="Supprimer"
			url="Suppression ${externalId}" />
		<display:column property="modifier" title="Modifier"
			url="Update ${externalId}" />
	</display:table>

</section>

<jsp:include page="../include/footer.jsp" />
