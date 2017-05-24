package com.bsuir.by.library.dao.implementation.comment.implementation;

import com.bsuir.by.library.bean.Comment;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.AbstractDaoController;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.comment.ICommentDao;
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
public class CommentDao extends AbstractDaoController<Comment,Integer> implements ICommentDao {
    @Override
    public Comment getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public Comment update(Comment comment) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }

    @Override
    protected List<Comment> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException, DataControllerException {
        LinkedList<Comment> result = new LinkedList<Comment>();
        try {
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setBookId(rs.getInt("lib_book_id"));
                comment.setId(rs.getInt("lib_comment_id"));
                comment.setSenderId(rs.getInt("lib_sender_id"));
                comment.setText(rs.getString("lib_comment_text"+dbDataLanguage));
                comment.setPublicateDate(rs.getTimestamp("lib_comment_date"));
                result.add(comment);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }


    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String commentText= String.valueOf(parameterMap.get("commentText"));
        String commentTextEn = String.valueOf(parameterMap.get("commentTextEn"));
        String commentBookId = String.valueOf(parameterMap.get("commentBookId"));
        String[] comentSenderId = parameterMap.get("comentSenderId");
        String sql = "INSERT INTO lib_comment(lib_comment_text,lib_comment_text_en,lib_book_id,lib_sender_id)VALUES (?,?,?,?)";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,commentText);
        stmt.setString(2,commentTextEn);
        stmt.setInt(3,Integer.parseInt(commentBookId));
        stmt.setInt(4,Integer.parseInt(comentSenderId[0]));
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap2, Connection connection, String dbDataLanguage) throws SQLException {
        String formData = String.valueOf(parameterMap2.get("formData"));
        JSONObject answearObj = (JSONObject) JSONValue.parse(formData);
        String[] rowThatNeedToUpdate = String.valueOf(parameterMap2.get("checkBox")).split(",");
        JSONArray id = (JSONArray) answearObj.get("commentId");
        JSONArray commentText = (JSONArray) answearObj.get("commentText");
        JSONArray commentBookId = (JSONArray) answearObj.get("commentBookId");
        JSONArray comentSenderId = (JSONArray) answearObj.get("comentSenderId");
        String sql = "UPDATE lib_comment SET lib_comment_text"+dbDataLanguage+"=?,lib_book_id=?,lib_sender_id=? WHERE lib_comment_id=?";
        for(String i:rowThatNeedToUpdate)
        {
            int j= Integer.valueOf(i);
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setString(1, String.valueOf(commentText.get(j)));
            stmt.setInt(2,Integer.parseInt(String.valueOf(commentBookId.get(j))));
            stmt.setInt(3,Integer.parseInt(String.valueOf(comentSenderId.get(j))));
            stmt.setInt(4,Integer.parseInt(String.valueOf(id.get(j))));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete = String.valueOf(parameterMap.get("checkBox")).split(",");
        String formData = String.valueOf(parameterMap.get("formData"));
        JSONObject answearObj = (JSONObject) JSONValue.parse(formData);
        JSONArray id = (JSONArray) answearObj.get("commentId");
        String sql = "DELETE FROM lib_comment WHERE lib_comment_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(String.valueOf(id.get(j))));
            stmt.execute();

        }
    }
}
