<%@ page import="com.bsuir.by.library.controller.data.BookDataController" %>
<%@ page import="com.bsuir.by.library.bean.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bsuir.by.library.controller.data.UserDataController" %>
<%@ page import="com.bsuir.by.library.bean.User" %>
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h3>${comment }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${text }"/>
<input type="text" readonly value="${publicate_date }"/>
<input type="text" readonly value="${book_id }"/>
<input type="text" readonly value="${sender_id }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" class="comment-container" action="tables?tableName=Comment&createAction=GetAll&language=${table_language}" method="POST" name="table">

</form>
        <input type="button"id="delete-comment" value="delete" name="action"/>
        <input type="button"id="update-comment" value="update" name="action"/>
<form action="tables?tableName=Comment&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="Comment" hidden readonly/>
    <input type="text" id="newCommentText" placeholder="Текст комментария"/>
    <input type="text" id="newCommentTextEn" placeholder="Текст комментария англ"/>
    <select  name="newCommentBookId" id="new-comment-book-id">
    </select>
    <select  name="newComentSenderId" id="new-comment-sender-id">
    </select>
    <input type="button"id="insert-comment" name="action" value="insert"/>
</form>
<a href="../../../resource/comment.pdf">comment.pdf</a>
<a href="../../../resource/comment.xls">comment.xls</a>
<a href="../../../resource/comment.csv">comment.csv</a>
<%--</c:if>--%>

</body>
<script src="../../../resource/js/commentTable.js"></script>
</html>
