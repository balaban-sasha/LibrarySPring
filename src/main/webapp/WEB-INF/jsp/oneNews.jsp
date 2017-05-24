<%--
  Created by IntelliJ IDEA.
  User: Саша
  Date: 08.05.2017
  Time: 0:44
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

<div class="container one-news-container">
    <h1 class="main_content one-news-header" style="text-align:center;"></h1>
    <section>
        <article>
            <div class="one-news-text">

            </div>
            <div class="one-news-publicate-date">

            </div>
            <div class="input-group"  name="commentForm">
                <span class="input-group-addon">Текст комментария</span>
                <input id="message" type="text" class="form-control" placeholder="Сообщение" > <span
                    class="input-group-btn">
                  <button class="btn btn-default" type="button"  id="add_new_comment">Отправить</button>
                 </span>
            </div>
        </article>
    </section>
    <hr color="black" style=" height: 3px;">
    <div class="news-comment-container"></div>
    <jsp:include page="template/footer.jsp"/>
</div><!--/.container-->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<jsp:include page="template/jsfiles.jsp"/>
<script src="../../resource/js/news.js"></script>
</body>
</html>
