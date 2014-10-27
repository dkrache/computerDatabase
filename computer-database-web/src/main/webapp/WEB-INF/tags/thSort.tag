<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="page" required="true" type="core.Page"%>
<%@ attribute name="url" required="false" type="java.lang.String"%>
<%@ attribute name="fieldName" required="true" type="java.lang.String"%>
<%@ attribute name="var" required="true" type="java.lang.String"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<th><a href="${url}/dashboard?${page.currentLink}&order=${var}"><spring:message code="${fieldName}"/></a></th>
