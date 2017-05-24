package com.bsuir.by.library.dao.implementation.author.implementation;

import com.bsuir.by.library.bean.Author;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.AbstractDaoController;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.author.IAuthorDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 28.03.2017.
 */
public class AuthorDao extends AbstractDaoController<Author, Integer> implements IAuthorDao {

    public AuthorDao() {
        super();
    }

    @Override
    public List<Author> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException, DataControllerException {
        LinkedList<Author> result = new LinkedList<Author>();
        try {
            while (rs.next()) {
                Author author = new Author();
                author.setAuthorName(rs.getString("lib_author_name" + dbDataLanguage));
                author.setAuthorFemale(rs.getString("lib_author_female" + dbDataLanguage));
                author.setAuthorBiography(rs.getString("lib_author_biography" + dbDataLanguage));
                author.setAuthorPatronymic(rs.getString("lib_author_patronymic" + dbDataLanguage));
                author.setId(rs.getInt("lib_author_id"));
                result.add(author);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap2, Connection connection, String dbDataLanguage) throws SQLException {
        String formData = String.valueOf(parameterMap2.get("formData"));
        Map<String, String[]> parameterMap = null;
        JSONObject answearObj = (JSONObject) JSONValue.parse(formData);
        JSONArray authorId = (JSONArray) answearObj.get("authorId");
        JSONArray authorBiography = (JSONArray) answearObj.get("authorBiography");
        JSONArray authorPatronymic = (JSONArray) answearObj.get("authorPatronymic");
        JSONArray authorSurname = (JSONArray) answearObj.get("authorSurname");
        JSONArray authorName = (JSONArray) answearObj.get("authorName");
        String[] rowThatNeedToUpdate = String.valueOf(parameterMap2.get("checkBox")).split(",");
        String sql = "UPDATE lib_author SET lib_author_name" + dbDataLanguage + " =?,lib_author_female" + dbDataLanguage + "=?,lib_author_patronymic" + dbDataLanguage + "=?,lib_author_biography" + dbDataLanguage + "=? WHERE lib_author_id=?";
        for (String i : rowThatNeedToUpdate) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, String.valueOf(authorName.get(j)));
            stmt.setString(2, String.valueOf(authorSurname.get(j)));
            stmt.setString(3, String.valueOf(authorPatronymic.get(j)));
            stmt.setString(4, String.valueOf(authorBiography.get(j)));
            stmt.setInt(5, Integer.parseInt(String.valueOf(authorId.get(j))));
            stmt.execute();

        }

    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete = String.valueOf(parameterMap.get("checkBox")).split(",");
        String formData = String.valueOf(parameterMap.get("formData"));
        JSONObject answearObj = (JSONObject) JSONValue.parse(formData);
        JSONArray id = (JSONArray) answearObj.get("authorId");
        String sql = "DELETE FROM lib_author WHERE lib_author_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(String.valueOf(id.get(j))));
            stmt.execute();

        }
    }

    @Override
    public Author getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public Author update(Author author) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }

    @Override
    public void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String authorName = String.valueOf(parameterMap.get("authorName"));
        String authorSurname = String.valueOf(parameterMap.get("authorSurname"));
        String authorPatronymic = String.valueOf(parameterMap.get("authorPatronymic"));
        String authorBiography = String.valueOf(parameterMap.get("authorBiography"));
        String authorNameEn = String.valueOf(parameterMap.get("authorNameEn"));
        String authorSurnameEn = String.valueOf(parameterMap.get("authorSurnameEn"));
        String authorPatronymicEn = String.valueOf(parameterMap.get("authorPatronymicEn"));
        String authorBiographyEn = String.valueOf(parameterMap.get("authorBiographyEn"));
        String sql = "INSERT INTO lib_author(lib_author_name,lib_author_female,lib_author_patronymic,lib_author_biography,lib_author_name_en,lib_author_female_en,lib_author_patronymic_en,lib_author_biography_en)" +
                " VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, authorName);
        stmt.setString(2, authorSurname);
        stmt.setString(3, authorPatronymic);
        stmt.setString(4, authorBiography);
        stmt.setString(5, authorNameEn);
        stmt.setString(6, authorSurnameEn);
        stmt.setString(7, authorPatronymicEn);
        stmt.setString(8, authorBiographyEn);
        stmt.execute();

    }
}
