<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css"/>
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
    <script src='<c:url value="/bootstrap/js/bootstrap.min.js" />'></script>
    <script src='<c:url value="/js/common.js" />'></script>
    <link rel="stylesheet" type="text/css" href='<c:url value="/bootstrap/css/bootstrap-responsive.min.css" />'/>
    <link rel="stylesheet" type="text/css" href='<c:url value="/bootstrap/css/bootstrap.min.css" />'/>
    <link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css" />'/>
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <title>OneWebSQL Demo: ${pageTitle}</title>
</head>

<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">OneWebSQL Demo</a>

            <div class="nav-collapse collapse">
                <ul class="nav">
                    <c:url value="/index.htm" var="homeUrl"/>
                    <li class="active"><a href="${homeUrl}">Home</a></li>

                    <c:url value="#" var="loginUrl"/>
                    <li class="active"><a href="${loginUrl}">Login</a></li>

                </ul>

            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>

