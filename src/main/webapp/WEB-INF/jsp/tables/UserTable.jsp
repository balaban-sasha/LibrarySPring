<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h3>${user }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${login }"/>
<input type="text" readonly value="${password }"/>
<input type="text" readonly value="${name }"/>
<input type="text" readonly value="${female }"/>
<input type="text" readonly value="${gender }"/>
<input type="text" readonly value="${age }"/>
<input type="text" readonly value="${user_status }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" class="user-container" action="tables?tableName=User&createAction=GetAll&language=${table_language}" method="POST" name="table">

</form>
<input type="Button" id="delete-user" value="delete" name="action"/>
<input type="Button" value="update" id="update-user" name="action"/>
<form action="tables?tableName=User&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="User" hidden readonly/>
    <input type="text" id="newUserLogin" placeholder="Логин"/>
    <input type="text" id="newUserPassword" placeholder="Пароль"/>
    <input type="text" id="newUserName" placeholder="Имя"/>
    <input type="text" id="newUserSurname" placeholder="Фамилия"/>
    <select id="newUserGender">
        <option>1</option>
        <option>2</option>
    </select>
    <input type="number" id="newUserAge" placeholder="Возраст"/>
    <select id="newUserStatus">
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>0</option>
    </select>
    <input type="button" id="insert-user" name="action" value="insert"/>
</form>
<%--</c:if>--%>

</body>
<script src="../../../resource/js/userTable.js"></script>
</html>
