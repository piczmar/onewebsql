<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>


<div class="container">
    <h1>Welcome to OneWebSQL demo application</h1>

    <p>The application demonstrates OneWebSQL integration with Spring MVC.</p>

    <p>This is an example of social network system in which each user can post news on tasks he accomplished and other
        users
        can mark his tasks with 'Likes' similarly as on FaceBook.</p>

    <jsp:include page="task/_showTasksReadOnly.jsp"/>

</div>
<jsp:include page="common/footer.jsp"/>
	