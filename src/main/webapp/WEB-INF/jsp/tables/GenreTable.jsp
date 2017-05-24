<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h3>${genre }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${genre }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" class="genre-container" action="tables?tableName=Genre&createAction=GetAll&language=${table_language}" method="POST" name="table">

</form>
<input type="button"id="delete-genre" value="delete" name="action"/>
<input type="button"id="update-genre" value="update" name="action"/>
<form action="tables?tableName=Genre&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="Genre" hidden readonly/>
    <input type="text" id="newGenreGenre" placeholder="Жанр"/>
    <input type="text" id="newGenreGenreEn" placeholder="Жанр англ"/>
    <input type="button"id="insert-genre" name="action" value="insert"/>
</form>
<%--</c:if>--%>

</body>
<script src="../../../resource/js/genreTable.js"></script>
</html>
