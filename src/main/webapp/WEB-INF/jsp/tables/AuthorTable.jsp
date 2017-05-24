<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h3>${author }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${author_name }"/>
<input type="text" readonly value="${author_female }"/>
<input type="text" readonly value="${author_patronymic }"/>
<input type="text" readonly value="${author_biography }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" class="author-container" action="tables?tableName=Author&createAction=GetAll&language=${table_language}" method="POST" name="table">

</form>
<input type="button" value="delete" name="action" id="delete-author"/>
<input type="button" value="update" name="action" id="update-author"/>
<form action="tables?tableName=Author&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="Author" hidden readonly/>
    <input type="text" id="newAuthorName" placeholder="Имя автора"/>
    <input type="text" id="newAuthorSurname" placeholder="Фамилия автора"/>
    <input type="text" id="newAuthorPatronymic" placeholder="Отчество автора"/>
    <input type="text" id="newAuthorBiography" placeholder="Биография автора"/>
    <input type="text" id="newAuthorNameEn" placeholder="Имя автора англ"/>
    <input type="text" id="newAuthorSurnameEn" placeholder="Фамилия автора англ"/>
    <input type="text" id="newAuthorPatronymicEn" placeholder="Отчество автора англ"/>
    <input type="text" id="newAuthorBiographyEn" placeholder="Биография автора англ"/>
    <input type="button" name="action" value="insert" id="insert-author"/>
</form>
<%--</c:if>--%>

</body>
<script src="../../../resource/js/authorTable.js"></script>
</html>
