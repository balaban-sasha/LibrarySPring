<%--
  Created by IntelliJ IDEA.
  User: Саша
  Date: 29.03.2017
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <title>Электронная библиотека</title>
</head>
<body>
<jsp:include page="template/header.jsp"/>
<jsp:include page="template/navigation.jsp"/>

<div class="container" ng-controller="NewsController">
    <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
            </p>
            <div class="jumbotron">
                <h1 id="main_section_header"></h1>
                <p id="main_section_description"></p>
            </div>
        </div><!--/span-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
            <div><!--class="list-group"!-->
                <a ng-repeat="news in newsList" href="http://localhost:8080/news?id={{news.id}}"
                   class="list-group-item">{{news.header}}</a>
            </div>
        </div><!--/span-->
    </div><!--/row-->

    <jsp:include page="template/footer.jsp"/>
</div><!--/.container-->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<jsp:include page="template/jsfiles.jsp"/>
</body>
</html>
