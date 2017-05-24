<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.bsuir.by.library.bean.Book" %><%--
  Created by IntelliJ IDEA.
  User: Саша
  Date: 09.05.2017
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Электронная библиотека</title>
</head>
<body>
<jsp:include page="template/header.jsp"/>
<jsp:include page="template/navigation.jsp"/>

<div class="container one-author-container">
    <h1 class="main_content one-author-snp" style="text-align:center;"></h1>
    <section>
        <article>
            <p class="one-author-biography">
            <p>
            <div class="one-author-books"></div>
            <c:forEach items="${section[0].bookList}" var="book">

                <a href="books?bookId=${book.id}">${book.bookName} </a></p>

            </c:forEach>
        </article>
    </section>
    <hr color="black" style=" height: 3px;">
    <div class="books-comment-container"></div>
    <jsp:include page="template/footer.jsp"/>
</div><!--/.container-->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<jsp:include page="template/jsfiles.jsp"/>
<script src="../../resource/js/author.js"></script>
</body>
</html>
