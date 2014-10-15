<jsp:include page="../include/header.jsp" />
<section id="main">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<h1 id="homeTitle">456 Computers found</h1>
	<div id="actions">
		<form action="" method="GET">
			<input type="search" id="searchbox" name="search" value=""
				placeholder="Search name"> <input type="submit"
				id="searchsubmit" value="Filter by name" class="btn primary">
		</form>
		<a class="btn success" id="add" href="AddComputer">Add Computer</a>
	</div>

	<c:if test="${not empty message and error }">
		<p style="color: red">${message}</p>
	</c:if>
	<c:if test="${not empty message and empty error }">
		<p style="color: green">${message}</p>
	</c:if>
	${computers}
</section>

<jsp:include page="../include/footer.jsp" />
