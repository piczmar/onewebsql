<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../common/header.jsp">
	<jsp:param name="pageTitle" value="Login"/>
</jsp:include>

<div class="container">
    <c:url value="/user/doLogin.htm" var="loginAction"/>
    <form class="form-signin" action="${loginAction}" method="POST" >
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="input-block-level" id="login" name="login" placeholder="Login">
        <input type="password" class="input-block-level" id="password" name="password"placeholder="Password">
        <label class="checkbox">
            <a href="register.htm">Do not have account?</a>
        </label>

        <button class="btn btn-large btn-primary" type="submit">Sign in</button>
    </form>

</div>
<jsp:include page="../common/footer.jsp" />
	