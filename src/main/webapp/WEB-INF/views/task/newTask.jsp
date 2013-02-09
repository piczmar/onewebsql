<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../common/header.jsp">
    <jsp:param name="pageTitle" value="List"/>
</jsp:include>

<div class="container">
    <h1>New Task</h1>
    <form:form action="saveTask.htm" method="post" commandName="newTask" class="form-horizontal">
        <jsp:include page="_formBody.jsp"/>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Save</button>
            <button onclick="javascript:go('viewUserTasks.htm');" type="button">Back</button>
        </div>
    </form:form>
</div>

<jsp:include page="../common/footer.jsp"/>
	