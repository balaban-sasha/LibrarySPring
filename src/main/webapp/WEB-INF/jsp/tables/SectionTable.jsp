<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h3>${section }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${name }"/>
<input type="text" readonly value="${header_of_smth }"/>
<input type="text" readonly value="${description }"/>
<input type="text" readonly value="${number }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" class="section-container" action="tables?tableName=Section&createAction=GetAll&language=${table_language}" method="POST" name="table">

</form>
<input type="button"id="delete-section" value="delete" name="action"/>
<input type="button" value="update"id="update-section" name="action"/>
<form action="tables?tableName=Section&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="Section" hidden readonly/>
    <input type="text" id="newSectionName" placeholder="Название раздела"/>
    <input type="text" id="newSectionHeader" placeholder="Заголовок раздела"/>
    <input type="text" id="newSectionDescription" placeholder="Описание раздела"/>
    <input type="text" id="newSectionNameEn" placeholder="Название раздела англ"/>
    <input type="text" id="newSectionHeaderEn" placeholder="Заголовок раздела англ"/>
    <input type="text" id="newSectionDescriptionEn" placeholder="Описание раздела англ"/>
    <input type="number" id="newSectionNumber" placeholder="Порядковый номер раздела"/>
    <input type="button"id="insert-section" name="action" value="insert"/>
</form>
<%--</c:if>--%>

</body>
<script src="../../../resource/js/sectionTable.js"></script>
</html>
