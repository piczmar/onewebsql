<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp">
    <jsp:param name="pageTitle" value="List"/>
</jsp:include>

<div class="container">
    <h1>My Tasks</h1>

    <form action="searchTask.htm" method="post" class="form-search">
        <input type="text" name="what" id="what" class="input-medium search-query"  placeHolder="Enter Task Name">
        <button type="submit" class="btn">Search</button>
        <button type="button" onclick="javascript:go('saveTask.htm');" class="btn">New Task</button>
    </form>

    <div class="media">

        <c:if test="${empty tasks}">

            No Results found

        </c:if>
        <c:if test="${! empty tasks}">
            <c:forEach var="task" items="${tasks}">
                <div class="task-panel">
                    <div class="pull-left">
                        <img class="media-object" src='<c:url value="/images/task.jpg" />'>
                    </div>

                    <div class="media-body">
                        <h4 class="media-heading"><c:out value="${task.what}"></c:out></h4>

                        <div class="media">
                            on <c:out value="${task.whenDate}"></c:out>
                            and it was <c:out value="${task.difficulty}"></c:out>.
                            <p>Earned <c:out value="${task.points}"></c:out> point(s).</p>
                        </div>
                        <button type="button" class="btn btn-primary"
                                onclick="javascript:go('updateTask.htm?id=${task.id}');">Edit
                        </button>
                        <button type="button" class="btn btn-primary"
                                onclick="javascript:deleteItem('deleteTask.htm?id=${task.id}');">Delete
                        </button>
                    </div>
                </div>
            </c:forEach>


        </c:if>
    </div>

</div>


<jsp:include page="../common/footer.jsp"/>
	