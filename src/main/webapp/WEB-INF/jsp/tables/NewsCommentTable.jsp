<%@ page import="com.bsuir.by.library.controller.data.UserDataController" %>
<%@ page import="com.bsuir.by.library.bean.User" %>
<%@ page import="com.bsuir.by.library.bean.News" %>
<%@ page import="com.bsuir.by.library.controller.data.NewsDataController" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h3>${news_comment }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${text }"/>
<input type="text" readonly value="${publicate_date }"/>
<input type="text" readonly value="${news_id }"/>
<input type="text" readonly value="${user_id }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" class="news-comment-container"
      action="tables?tableName=NewsComment&createAction=GetAll&language=${table_language}"
      method="POST" name="table">

</form>
<input type="button"id="delete-news-comment" value="delete" name="action"/>
<input type="button"id="update-news-comment" value="update" name="action"/>
<form action="tables?tableName=NewsComment&createAction=GetAll&language=${table_language}" method="post" name="table"
      accept-charset="UTF-8">
    <input type="text" name="table" value="NewsComment" hidden readonly/>
    <input type="text" id="newNewsCommentText" placeholder="Текст комментария"/>
    <input type="text" id="newNewsCommentTextEn" placeholder="Текст комментария англ"/>
    <select name="newNewsCommentNewsId" id="new-news-comment-news-id">
    </select>
    <select name="newNewsCommentUserId" id="new-news-comment-user-id">
        <input type="button"id="insert-news-comment" name="action" value="insert"/>
</form>
<%--</c:if>--%>

</body>
<script src="../../../resource/js/newsCommentTable.js"></script>
</html>
