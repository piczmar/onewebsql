<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>
<div class="container">
    <h1>Error encountered:</h1>

    <p>${error}</p>
</div>

<jsp:include page="common/footer.jsp"/>
	