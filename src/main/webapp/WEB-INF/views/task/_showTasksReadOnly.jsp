<%@ page import="com.generated.tasks.Task" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form action="index.htm" method="post" class="form-search">
    <input type="text" name="what" id="what" class="input-medium search-query" placeHolder="Enter Task Name" >
    <button type="submit" class="btn">Search</button>
    <c:if test='<%=session.getAttribute("userId")!=null%>'>
        <button type="button" onclick="javascript:go('task/saveTask.htm');" class="btn">New Task</button>
    </c:if>
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
                    <h4 class="media-heading"><c:out value="${task.login}"></c:out></h4>

                    <h4 class="media-heading"><c:out value="${task.what}"></c:out></h4>

                    <div class="media">
                        on <c:out value="${task.whenDate}"></c:out>
                        and it was <c:out value="${task.difficulty}"></c:out>.
                        <p>Earned <c:out value="${task.points}"></c:out> point(s).</p>

                        <p><c:out value="${task.count}"></c:out> people like this</p>

                        <p><i class="icon-thumbs-up"></i><a
                                href="javascript:go('task/like.htm?id=${task.id}');">Like</a></p>
                    </div>
                </div>
            </div>

        </c:forEach>


    </c:if>
</div>

