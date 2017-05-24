package com.bsuir.by.library.dao.implementation.bookCatalog.implementation;

import com.bsuir.by.library.bean.BookCatalog;
import com.bsuir.by.library.dao.AbstractDaoController;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.bookCatalog.IBookCatalogDao;
import com.google.common.base.Splitter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 28.03.2017.
 */
public class BookCatalogDao extends AbstractDaoController<BookCatalog,Integer> implements IBookCatalogDao {
    @Override
    public BookCatalog getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public BookCatalog update(BookCatalog bookCatalog) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }

    @Override
    protected List<BookCatalog> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException {
        LinkedList<BookCatalog> result = new LinkedList<BookCatalog>();
        try {
            while (rs.next()) {
                BookCatalog bookCatalog = new BookCatalog();
                bookCatalog.setBookId(rs.getInt("lib_book_id"));
                bookCatalog.setBookStatus(rs.getInt("lib_book_catalog_status"));
                bookCatalog.setId(rs.getInt("lib_book_catalog_id"));
                bookCatalog.setSectionId(rs.getInt("lib_section_id"));
                result.add(bookCatalog);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
            String bookCatalogStatus= String.valueOf(parameterMap.get("bookCatalogStatus"));
            String bookCatalogBookId = String.valueOf(parameterMap.get("bookCatalogBookId"));
            String bookCatalogSectionId = String.valueOf(parameterMap.get("bookCatalogSectionId"));
            String sql = "INSERT INTO lib_book_catalog(lib_book_catalog_status,lib_book_id,lib_section_id)" +
                    "VALUES (?,?,?)";
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(bookCatalogStatus));
            stmt.setInt(2,Integer.parseInt(bookCatalogBookId));
            stmt.setInt(3,Integer.parseInt(bookCatalogSectionId));
            stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap2, Connection connection, String dbDataLanguage) throws SQLException {
        String formData= String.valueOf(parameterMap2.get("formData"));
        Map<String, String[]> parameterMap=null;

        JSONObject answearObj= (JSONObject) JSONValue.parse(formData);
        JSONArray id = (JSONArray) answearObj.get("bookCatalogId");
        JSONArray bookCatalogStatus = (JSONArray) answearObj.get("bookCatalogStatus");
        JSONArray bookCatalogBookId = (JSONArray) answearObj.get("bookCatalogBookId");
        JSONArray bookCatalogSectionId = (JSONArray) answearObj.get("bookCatalogSectionId");
        /*for (int i=0;i< ((org.json.simple.JSONArray) answearObj).size();i++)
        {
            org.json.simple.JSONObject obj2=(org.json.simple.JSONObject)array.get(i);
            adp.add(new ChatMessage(false, (String)obj2.get("sm_chat_user_name"),(String)obj2.get("sm_chat_message")));
            if(((org.json.simple.JSONArray) answearObj).size()==(i+1))
                setLastMessageId((Integer)obj2.get("sm_chat_id"));
        }*/
        String[] rowThatNeedToUpdate = String.valueOf(parameterMap2.get("checkBox")).split(",");
        String sql = "UPDATE lib_book_catalog SET lib_book_catalog_status=?,lib_book_id=?,lib_section_id=? WHERE lib_book_catalog_id=?";
        for(String i:rowThatNeedToUpdate)
        {
            int j= Integer.valueOf(i);
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(String.valueOf(bookCatalogStatus.get(j))));
            stmt.setInt(2,Integer.parseInt(String.valueOf(bookCatalogBookId.get(j))));
            stmt.setInt(3,Integer.parseInt(String.valueOf(bookCatalogSectionId.get(j))));
            stmt.setInt(4,Integer.parseInt(String.valueOf(id.get(j))));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete=String.valueOf(parameterMap.get("checkBox")).split(",");
        String formData= String.valueOf(parameterMap.get("formData"));
        JSONObject answearObj= (JSONObject) JSONValue.parse(formData);
        JSONArray id = (JSONArray) answearObj.get("bookCatalogId");
        String sql = "DELETE FROM lib_book_catalog WHERE lib_book_catalog_id=?";
        for(String i:rowThatNeedDelete)
        {
            int j= Integer.valueOf(i);
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(String.valueOf(id.get(j))));
            stmt.execute();

        }
    }
}
