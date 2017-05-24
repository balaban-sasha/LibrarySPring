<%@ page import="com.bsuir.by.library.controller.data.NewsDataController" %>
<%@ page import="com.bsuir.by.library.bean.News" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bsuir.by.library.controller.data.SectionDataController" %>
<%@ page import="com.bsuir.by.library.bean.Section" %>
<%@ page import="com.bsuir.by.library.controller.data.BookDataController" %>
<%@ page import="com.bsuir.by.library.bean.Book" %>
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h3>${book_catalog }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${book_status }"/>
<input type="text" readonly value="${book_id }"/>
<input type="text" readonly value="${section_id }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" class="book-catalog-container" action="tables?tableName=BookCatalog&createAction=GetAll&language=${table_language}" method="POST" name="table">
    <!--  <% int index = 1;%>
    <% BookDataController bookDataController = new BookDataController();
        List<Book> bookList = bookDataController.setTableContent("");
        SectionDataController sectionDataController = new SectionDataController();
        List<Section> sections = sectionDataController.setTableContent("");
    %>
    <c:forEach items="${BookCatalog}" var="bookCatalog">
       <input type="text" name="table" value="BookCatalog" hidden readonly/>

        <select  name="bookCatalogBookId">
            <% for(Book book:bookList)
            {
                out.println("<option>"+book.getId()+"</option>");
            }
            %>
        </select>
        <input type="text" name="bookCatalogSectionIdOld" value="${bookCatalog.sectionId}" style="width:120px;"/>
        <select  name="bookCatalogSectionId">

        </select>
        <input type="checkbox" name="checkBookCatalog" value="<% out.print(index); %>"/><br>
    </c:forEach>!-->
</form>
<input type="button" id="delete-book-catalog-button" value="delete" name="action"/>
<input type="button" id="update-book-catalog-button" value="update" name="action"/>
<form action="tables?tableName=BookCatalog&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="BookCatalog" hidden readonly/>

    <input type="number" name="newBookCatalogStatus"id="new-book-catalog-status" placeholder="Статус каталога"/>
    <select  name="newBookCatalogBookId" id="new-book-catalog-book-id">

    </select>
    <select  name="newBookCatalogSectionId" id="new-book-catalog-section-id">

    </select>
    <input type="button" name="action" id="insert-new-data" value="insert"/>
</form>
<%--</c:if>--%>

</body>
<script src="../../../resource/js/bookCatalog.js"></script>
</html>
