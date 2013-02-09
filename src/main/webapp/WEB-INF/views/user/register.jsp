<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp">
	<jsp:param name="pageTitle" value="Register"/>
</jsp:include>


<div class="container">
    <c:url value="/user/doRegister.htm" var="registerAction"/>
    <form class="form-signin" action="${registerAction}" method="POST" >
        <h2 class="form-signin-heading">Registration</h2>
        <input type="text" class="input-block-level" id="login" name="login" placeholder="Login">
        <input type="text" class="input-block-level" id="name" name="name" placeholder="Name">
        <input type="text" class="input-block-level" id="surname" name="surname" placeholder="Surname">
        <input type="password" class="input-block-level" id="password" name="password"placeholder="Password">
        <input type="password" class="input-block-level" id="confPassword" name="confPassword"placeholder="Confirm Password">
        <button class="btn btn-large btn-primary" type="submit">Register</button>
    </form>

</div>

<jsp:include page="../common/footer.jsp" />
	