<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<title>EPF Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script
	src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
<!-- Bootstrap -->
<link href="/computer-database-web/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="/computer-database-web/css/main.css" rel="stylesheet" media="screen">
</head>
<body>

	<header class="topbar">
		<h1 class="fill">
			<a href="/computer-database-web/dashboard"><spring:message code="front.header.title" /></a>
		</h1>
		<span style="float: right"> <a href="/computer-database-web/dashboard?lang=en"><img alt="en" src="img/en.png"/></a> | <a
			href="/computer-database-web/dashboard?lang=fr"><img alt="fr" src="img/fr.png"/></a>
		</span>
	</header>
	
