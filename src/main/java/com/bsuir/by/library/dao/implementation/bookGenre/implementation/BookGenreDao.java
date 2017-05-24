package com.bsuir.by.library.dao.implementation.bookGenre.implementation;

import com.bsuir.by.library.bean.BookGenre;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.AbstractDaoController;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.bookGenre.IBookGenreDao;
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
public class BookGenreDao extends AbstractDaoController<BookGenre, Integer> implements IBookGenreDao {
    @Override
    public BookGenre getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public BookGenre update(BookGenre bookGenre) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }

    @Override
    protected List<BookGenre> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException, DataControllerException {
        LinkedList<BookGenre> result = new LinkedList<BookGenre>();
        try {
            while (rs.next()) {
                BookGenre bookGenre = new BookGenre();
                bookGenre.setBookId(rs.getInt("lib_book_id"));
                bookGenre.setGenreId(rs.getInt("lib_genre_id"));
                bookGenre.setId(rs.getInt("lib_book_genre_id"));
                result.add(bookGenre);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {

        String bookGenreBookId= String.valueOf(parameterMap.get("bookGenreBookId"));
        String bookGenreGenreId = String.valueOf(parameterMap.get("bookGenreGenreId"));
        String sql = "INSERT INTO lib_book_genre(lib_book_id,lib_genre_id)VALUES (?,?)";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setInt(1,Integer.parseInt(bookGenreBookId));
        stmt.setInt(2,Integer.parseInt(bookGenreGenreId));
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap2, Connection connection, String dbDataLanguage) throws SQLException {
        String formData = String.valueOf(parameterMap2.get("formData"));
        JSONObject answearObj = (JSONObject) JSONValue.parse(formData);
        String[] rowThatNeedToUpdate = String.valueOf(parameterMap2.get("checkBox")).split(",");
        JSONArray id = (JSONArray) answearObj.get("bookGenreId");
        JSONArray bookGenreBookId = (JSONArray) answearObj.get("bookGenreBookId");
        JSONArray bookGenreGenreId = (JSONArray) answearObj.get("bookGenreGenreId");
        String sql = "UPDATE lib_book_genre SET lib_book_id=?,lib_genre_id=? WHERE lib_book_genre_id=?";
        for(String i:rowThatNeedToUpdate)
        {
            int j= Integer.valueOf(i);
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(String.valueOf(bookGenreBookId.get(j))));
            stmt.setInt(2,Integer.parseInt(String.valueOf(bookGenreGenreId.get(j))));
            stmt.setInt(3,Integer.parseInt(String.valueOf(id.get(j))));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete = String.valueOf(parameterMap.get("checkBox")).split(",");
        String formData = String.valueOf(parameterMap.get("formData"));
        JSONObject answearObj = (JSONObject) JSONValue.parse(formData);
        JSONArray id = (JSONArray) answearObj.get("bookGenreId");
        String sql = "DELETE FROM lib_book_genre WHERE lib_book_genre_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(String.valueOf(id.get(j))));
            stmt.execute();

        }
    }
}
