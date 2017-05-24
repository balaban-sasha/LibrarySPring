package com.bsuir.by.library.dao.implementation.book.implementation;

import com.bsuir.by.library.bean.Book;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.AbstractDaoController;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.book.IBookDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 28.03.2017.
 */
public class BookDao extends AbstractDaoController<Book,Integer> implements IBookDao {
    @Override
    public Book getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public Book update(Book book) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }

    @Override
    protected List<Book> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException, DataControllerException {
        LinkedList<Book> result = new LinkedList<Book>();
        try {
            while (rs.next()) {
                Book book = new Book();
                book.setBookName(rs.getString("lib_book_name"+dbDataLanguage));
                book.setBookDate(rs.getTimestamp("lib_book_date"));
                book.setBookDescription(rs.getString("lib_book_description"+dbDataLanguage));
                book.setBookTextLink(rs.getString("lib_book_text_link"+dbDataLanguage));
                book.setAuthorId(rs.getInt("lib_book_author_id"));
                book.setId(rs.getInt("lib_book_id"));
                result.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String bookName = String.valueOf(parameterMap.get("bookName"));
        String bookDescription = String.valueOf(parameterMap.get("bookDescription"));
        String bookTextLink = String.valueOf(parameterMap.get("bookTextLink"));
        String bookNameEn= String.valueOf(parameterMap.get("bookNameEn"));
        String bookDescriptionEn = String.valueOf(parameterMap.get("bookDescriptionEn"));
        String bookTextLinkEn = String.valueOf(parameterMap.get("bookTextLinkEn"));
        String bookAuthorId= String.valueOf(parameterMap.get("bookAuthorId"));
        String sql = "INSERT INTO lib_book(lib_book_name,lib_book_description,lib_book_text_link,lib_book_name_en,lib_book_description_en,lib_book_text_link_en,lib_book_author_id)" +
                "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,bookName);
        stmt.setString(2,bookDescription);
        stmt.setString(3,bookTextLink);
        stmt.setString(4,bookNameEn);
        stmt.setString(5,bookDescriptionEn);
        stmt.setString(6,bookTextLinkEn);
        stmt.setInt(7,Integer.parseInt(bookAuthorId));
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap2, Connection connection, String dbDataLanguage) throws SQLException {
        String formData = String.valueOf(parameterMap2.get("formData"));
        Map<String, String[]> parameterMap = null;
        JSONObject answearObj = (JSONObject) JSONValue.parse(formData);
        String[] checkedBook = String.valueOf(parameterMap2.get("checkBox")).split(",");
        JSONArray bookName = (JSONArray) answearObj.get("bookName");
        JSONArray bookDescription = (JSONArray) answearObj.get("bookDescription");
        JSONArray bookTextLink = (JSONArray) answearObj.get("bookTextLink");
        JSONArray bookId = (JSONArray) answearObj.get("bookId");
        JSONArray bookAuthorId = (JSONArray) answearObj.get("bookAuthorId");
        String sql = "UPDATE lib_book SET lib_book_name" + dbDataLanguage + " =?,lib_book_description" + dbDataLanguage + "=?,lib_book_text_link" + dbDataLanguage + "=?,lib_book_author_id" + dbDataLanguage + "=? WHERE lib_book_id=?";
        for (String i : checkedBook) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, String.valueOf(bookName.get(j)));
            stmt.setString(2, String.valueOf(bookDescription.get(j)));
            stmt.setString(3, String.valueOf(bookTextLink.get(j)));
            stmt.setInt(4, Integer.parseInt(String.valueOf(bookAuthorId.get(j))));
            stmt.setInt(5, Integer.parseInt(String.valueOf(bookId.get(j))));
            stmt.execute();

        }
        }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete = String.valueOf(parameterMap.get("checkBox")).split(",");
        String formData = String.valueOf(parameterMap.get("formData"));
        JSONObject answearObj = (JSONObject) JSONValue.parse(formData);
        JSONArray id = (JSONArray) answearObj.get("bookId");
        String sql = "DELETE FROM lib_book WHERE lib_book_id=?";
        for(String i:rowThatNeedDelete)
        {
            int j= Integer.valueOf(i);
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(String.valueOf(id.get(j))));
            stmt.execute();

        }
    }
}
