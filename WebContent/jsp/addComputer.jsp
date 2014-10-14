<jsp:include page="../include/header.jsp" />
<script type="text/javascript" src="./js/validationAddComputer.js"></script>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<section id="main">

	<h1>Add Computer</h1>
	<c:if test="${not empty message and error }">
		<p style="color: red">${message}</p>
	</c:if>
	<c:if test="${not empty message and empty error }">
		<p style="color: green">${message}</p>
	</c:if>
	<form id="formAddComputer" action="AddComputer" method="POST">
		<fieldset><%@ taglib prefix="display"
				uri="http://displaytag.sf.net/el"%>
			<div class="clearfix">
				<label for="name">Computer name*:</label>
				<c:if test="${not empty computer}">
					<div class="input">
						<input type="hidden" name="externalId" id="externalId"
							value="${computer.externalId}" />
					</div>
				</c:if>
				<div class="input">
					<input type="text" name="name" id="name" />
				</div>
			</div>

			<div class="clearfix">

				<label for="introduced">Introduced date*:</label>
				<div class="input">
					<input id="introducedDate" class="hasDatepicker valid" type="date"
						name="introducedDate" size="10"> <img
						class="ui-datepicker-trigger" src="./img/calendar-green.gif"
						alt="calendar" title="title_calendar" />
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date*:</label>
				<div class="input">
					<input id="discontinuedDate" class="hasDatepicker valid"
						type="date" name="discontinuedDate" size="10"> <img
						class="ui-datepicker-trigger" src="./img/calendar-green.gif"
						alt="calendar" title="title_calendar" />
				</div>
			</div>

			<div class="clearfix">
				<label for="company" id="company">Company Name:</label>
				<div class="input">
					<select name="company">
						<c:forEach items="${companys}" var="company">
							<option value="${company.externalId}">${company.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Add" class="btn primary"> or <a
				href="Accueil" class="btn">Cancel</a>
		</div>
	</form>

</section>

<jsp:include page="../include/footer.jsp" />