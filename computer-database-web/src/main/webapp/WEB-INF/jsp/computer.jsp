<jsp:include page="/include/header.jsp" />
<script type="text/javascript" src="js/validationAddComputer.js"></script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section id="main">

	<h1><spring:message code="front.computer.form.title" /></h1>
	<c:if test="${not empty message and error }">
		<p style="color: red"><spring:message code="${message}"/></p>
	</c:if>
	<c:if test="${not empty message and empty error }">
		<p style="color: green"><spring:message code="${message}"/></p> 
	</c:if>
	<form:form id="formAddComputer" commandName="computerDto"
		action="/computer-database-web/computer/add" method="POST">

		<c:if test="${not empty computerDto}">
			<div class="input">
				<input type="hidden" name="externalId" id="externalId"
					value="${computerDto.externalId}" />
			</div>
		</c:if>
		<fieldset>
			<div class="clearfix">
				<label for="computerName"><spring:message code="front.computer.form.field.computerName"/></label>
				<div class="input">
					<form:input path="computerName" />
					<form:errors path="computerName" cssClass="error" />
				</div>
			</div>
			<div class="clearfix">
				<label for="introducedDate"><spring:message code="front.computer.form.field.introducedDate"/></label>
				<div class="input">
					<form:input path="introducedDate" cssClass="hasDatepicker valid" />
					<form:errors path="introducedDate" cssClass="error" />
					<img class="ui-datepicker-trigger" src="./img/calendar-green.gif"
						alt="calendar" title="title_calendar" />
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinuedDate"><spring:message code="front.computer.form.field.discontinuedDate"/></label>
				<div class="input">
					<form:input path="discontinuedDate" cssClass="hasDatepicker valid" />
					<form:errors path="discontinuedDate" cssClass="error" />
					<img class="ui-datepicker-trigger" src="./img/calendar-green.gif"
						alt="calendar" title="title_calendar" />
				</div>
			</div>


			<div class="clearfix">
				<label for="company" id="company"><spring:message code="front.computer.form.field.companyName"/></label>
				<div class="input">
					<form:select path="companyDto" >
						<form:option value="null" label="Select..." />
						<form:options items="${companys}" itemValue="externalId" itemLabel="name" />
					</form:select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="<spring:message code="front.computer.form.add"/>" class="btn primary">  <a
				href="/computer-databas-web/computer/add" class="btn"><spring:message code="front.computer.form.button.cancel"/></a>
		</div>



	</form:form>



</section>

<jsp:include page="/include/footer.jsp" />