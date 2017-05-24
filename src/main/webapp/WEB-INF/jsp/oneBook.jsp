<%--
  Created by IntelliJ IDEA.
  User: Саша
  Date: 09.05.2017
  Time: 9:48
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

<div class="container one-book-container">
    <h1 class="main_content one-book-header" style="text-align:center;"></h1>
    <section>
        <article>
            <p class="one-book-description">
            <p>
            <font style="display: block;">Дата публикации: <font class="one-book-publicate-date"></font> </font>
            <div class="one-book-link"></div>

            <div class="one-book-author-link"></div>
            </p>
            <div class="input-group">
                <span class="input-group-addon">Текст комментария</span>
                <input id="books-id" hidden readonly value="${section[0].id}"/>
                <input id="message" type="text" class="form-control" placeholder="Сообщение"> <span
                    class="input-group-btn">
                  <button class="btn btn-default" type="button" id="add_new_comment">Отправить</button>
                 </span>
            </div>
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
<script src="../../resource/js/book.js"></script>
</body>
</html>
