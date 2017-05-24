<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h3>${news }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${header_of_smth }"/>
<input type="text" readonly value="${text }"/>
<input type="text" readonly value="${publicate_date }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" class="news-container" action="tables?tableName=News&createAction=GetAll&language=${table_language}" method="POST"
      name="table">
</form>
<input type="button" value="delete"id="delete-news" name="action"/>
<input type="button" value="update"id="update-news" name="action"/>
<form action="tables?tableName=News&createAction=GetAll&language=${table_language}" method="post" name="table"
      accept-charset="UTF-8">
    <input type="text" name="table" value="News" hidden readonly/>
    <input type="text" id="newNewsHeader" placeholder="Заголовок новости"/>
    <input type="text" id="newNewsHeaderEn" placeholder="Заголовок новости англ"/>
    <input type="text" id="newNewsText" placeholder="Текст новости"/>
    <input type="text" id="newNewsTextEn" placeholder="Текст новости англ"/>
    <input type="button" id="insert-news" name="action" value="insert"/>
</form>
<a href="../../../resource/news.pdf">news.pdf</a>
<a href="../../../resource/news.xls">news.xls</a>
<a href="../../../resource/news.csb">news.csb</a>
<%--</c:if>--%>

</body>
<script src="../../../resource/js/newsTable.js"></script>
</html>
