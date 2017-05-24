package com.bsuir.by.library.dao.implementation.news.implementation;

import com.bsuir.by.library.bean.News;
import com.bsuir.by.library.dao.AbstractDaoController;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.news.INewsDao;
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
public class NewsDao extends AbstractDaoController<News, Integer> implements INewsDao {
    @Override
    public News getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public News update(News news) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }


    @Override
    protected List<News> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException {
        LinkedList<News> result = new LinkedList<News>();
        try {
            while (rs.next()) {
                News news = new News();
                news.setHeader(rs.getString("lib_news_header" + dbDataLanguage));
                news.setId(rs.getInt("lib_news_id"));
                news.setPublicateDate(rs.getTimestamp("lib_news_date"));
                news.setText(rs.getString("lib_news_text" + dbDataLanguage));
                result.add(news);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String newsHeader = String.valueOf(parameterMap.get("newsHeader"));
        String newsHeaderEn = String.valueOf(parameterMap.get("newsHeaderEn"));
        String newsText = String.valueOf(parameterMap.get("newsText"));
        String newsTextEn= String.valueOf(parameterMap.get("newsTextEn"));
        String sql = "INSERT INTO lib_news(lib_news_header,lib_news_text,lib_news_header_en,lib_news_text_en) VALUES (" +
                "?,?,?,?)";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,newsHeader);
        stmt.setString(2,newsText);
        stmt.setString(3,newsHeaderEn);
        stmt.setString(4,newsTextEn);
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap2, Connection connection, String dbDataLanguage) throws SQLException {
        String formData = String.valueOf(parameterMap2.get("formData"));
        JSONObject answearObj = (JSONObject) JSONValue.parse(formData);
        String[] checkedBook = String.valueOf(parameterMap2.get("checkBox")).split(",");
        JSONArray id = (JSONArray) answearObj.get("newsId");
        JSONArray newsHeader = (JSONArray) answearObj.get("newsHeader");
        JSONArray newsText = (JSONArray) answearObj.get("newsText");
        String sql = "UPDATE lib_news SET lib_news_header"+dbDataLanguage+"=?,lib_news_text"+dbDataLanguage+"=? WHERE lib_news_id=?";
        for (String i : checkedBook) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, String.valueOf(newsHeader.get(j)));
            stmt.setString(2, String.valueOf(newsText.get(j)));
            stmt.setInt(3, Integer.parseInt(String.valueOf(id.get(j))));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete = String.valueOf(parameterMap.get("checkBox")).split(",");
        String formData = String.valueOf(parameterMap.get("formData"));
        JSONObject answearObj = (JSONObject) JSONValue.parse(formData);
        JSONArray id = (JSONArray) answearObj.get("newsId");
        String sql = "DELETE FROM lib_news WHERE lib_news_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(String.valueOf(id.get(j))));
            stmt.execute();

        }
    }
}
