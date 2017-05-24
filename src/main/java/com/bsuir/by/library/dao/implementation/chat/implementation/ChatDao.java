package com.bsuir.by.library.dao.implementation.chat.implementation;

import com.bsuir.by.library.bean.Chat;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.AbstractDaoController;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.chat.IChatDao;
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
public class ChatDao extends AbstractDaoController<Chat, Integer> implements IChatDao {
    @Override
    public Chat getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public Chat update(Chat chat) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }


    @Override
    protected List<Chat> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException, DataControllerException {
        LinkedList<Chat> result = new LinkedList<Chat>();
        try {
            while (rs.next()) {
                Chat chat = new Chat();
                chat.setId(rs.getInt("lib_chat_id"));
                chat.setPublicateDate(rs.getTimestamp("lib_chat_message_date"));
                chat.setSenderId(rs.getInt("lib_chat_message_sender_id"));
                chat.setText(rs.getString("lib_chat_message_text" + dbDataLanguage));
                result.add(chat);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String chatText = String.valueOf(parameterMap.get("chatText"));
        String chatTextEn = String.valueOf(parameterMap.get("chatTextEn"));
        String[] chatSenderId = parameterMap.get("chatSenderId");
        String sql = "INSERT INTO lib_chat(lib_chat_message_text,lib_chat_message_text_en,lib_chat_message_sender_id)VALUES (?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, chatText);
        stmt.setString(2, chatTextEn);
        stmt.setInt(3, Integer.parseInt(chatSenderId[0]));
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap2, Connection connection, String dbDataLanguage) throws SQLException {
        String formData = String.valueOf(parameterMap2.get("formData"));
        JSONObject answearObj = (JSONObject) JSONValue.parse(formData);
        String[] rowThatNeedToUpdate = String.valueOf(parameterMap2.get("checkBox")).split(",");
        JSONArray id = (JSONArray) answearObj.get("chatId");
        JSONArray chatText = (JSONArray) answearObj.get("chatText");
        JSONArray chatSenderId = (JSONArray) answearObj.get("chatSenderId");
        String sql = "UPDATE lib_chat SET lib_chat_message_text" + dbDataLanguage + "=?,lib_chat_message_sender_id=? WHERE lib_chat_id=?";
        for (String i : rowThatNeedToUpdate) {
            int j = Integer.valueOf(i);
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, String.valueOf(chatText.get(j)));
            stmt.setInt(2, Integer.parseInt(String.valueOf(chatSenderId.get(j))));
            stmt.setInt(3, Integer.parseInt(String.valueOf(id.get(j))));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete = String.valueOf(parameterMap.get("checkBox")).split(",");
        String formData = String.valueOf(parameterMap.get("formData"));
        JSONObject answearObj = (JSONObject) JSONValue.parse(formData);
        JSONArray id = (JSONArray) answearObj.get("chatId");
        String sql = "DELETE FROM lib_chat WHERE lib_chat_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(String.valueOf(id.get(j))));
            stmt.execute();

        }
    }
}
