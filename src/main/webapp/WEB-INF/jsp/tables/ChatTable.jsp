<%@ page import="com.bsuir.by.library.controller.data.UserDataController" %>
<%@ page import="com.bsuir.by.library.bean.User" %>
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
<h3>${chat }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${text }"/>
<input type="text" readonly value="${publicate_date }"/>
<input type="text" readonly value="${sender_id }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" class="chat-container" action="tables?tableName=Chat&createAction=GetAll&language=${table_language}" method="POST" name="table">
</form>
<input type="button"id="delete-chat" value="delete" name="action"/>
<input type="button"id="update-chat" value="update" name="action"/>
<form action="tables?tableName=Chat&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="Chat" hidden readonly/>
    <input type="text" id="newChatText" placeholder="Текст"/>
    <input type="text" id="newChatTextEn" placeholder="Текст англ"/>
    <select  id="new-chat-sender-id">
    </select>
    <input type="button"id="insert-chat" name="action" value="insert"/>
</form>
<a href="../../../resource/sample.pdf">chat.pdf</a>
<a href="../../../resource/chat.xls">chat.xls</a>
<a href="../../../resource/chat.csv">chat.csv</a>
<%--</c:if>--%>

</body>
<script src="../../../resource/js/chatTable.js"></script>
</html>
