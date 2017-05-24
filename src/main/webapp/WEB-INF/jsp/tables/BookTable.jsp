<%@ page import="com.bsuir.by.library.controller.data.AuthorDataController" %>
<%@ page import="com.bsuir.by.library.bean.Author" %>
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

<h3>${book }</h3>


<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${book_name }"/>
<input type="text" readonly value="${book_date }"/>
<input type="text" readonly value="${description }"/>
<input type="text" readonly value="${book_text_link }"/>
<input type="text" readonly value="${author_id }"/>
    <%--<c:if test="${!empty bookList}">--%>
<form class="book-container" accept-charset="UTF-8" action="tables?tableName=Book&createAction=GetAll&language=${table_language}" method="POST" name="table">

</form>
<input type="button"id="delete-book" value="delete" name="action"/>
<input type="button"id="update-book" value="update" name="action"/>
<form action="tables?tableName=Book&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" id="table" value="Book" hidden readonly/>
    <input type="text" id="newBookName" placeholder="Название книги"/>
    <input type="text" id="newBookDescription" placeholder="Описание книги"/>
    <input type="text" id="newBookTextLink" placeholder="Ссылка на книгу"/>
    <input type="text" id="newBookNameEn" placeholder="Название книги англ"/>
    <input type="text" id="newBookDescriptionEn" placeholder="Описание книги англ"/>
    <input type="text" id="newBookTextLinkEn" placeholder="Ссылка на книгу англ"/>
    <select  name="newBookAuthorId" id="new-book-author-id">

    </select>
    <input type="button"id="insert-book" name="action" value="insert"/>
</form>

    <%--</c:if>--%>
</body>
<script src="../../../resource/js/bookTable.js"></script>
</html>
