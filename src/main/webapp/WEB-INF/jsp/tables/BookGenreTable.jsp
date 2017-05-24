<%@ page import="com.bsuir.by.library.controller.data.BookDataController" %>
<%@ page import="com.bsuir.by.library.bean.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bsuir.by.library.controller.data.UserDataController" %>
<%@ page import="com.bsuir.by.library.bean.User" %>
<%@ page import="com.bsuir.by.library.bean.Genre" %>
<%@ page import="com.bsuir.by.library.controller.data.GenreDataController" %>
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h3>${book_raiting }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${book_id }"/>
<input type="text" readonly value="${genre_id }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" class="book-genre-container" action="tables?tableName=BookGenre&createAction=GetAll&language=${table_language}" method="POST" name="table">
</form>
<input type="button"id="delete-book-genre" value="delete" name="action"/>
<input type="button"id="update-book-genre" value="update" name="action"/>
<form action="tables?tableName=BookGenre&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="BookGenre" hidden readonly/>

    <select  name="newBookGenreBookId"id="new-book-genre-book-id">

    </select>
    <select  name="newBookGenreGenreId" id="new-book-genre-genre-id">

    </select>
    <input type="button"id="insert-book-genre" name="action" value="insert"/>
</form>
<%--</c:if>--%>

</body>
<script src="../../../resource/js/bookGenreTable.js"></script>
</html>