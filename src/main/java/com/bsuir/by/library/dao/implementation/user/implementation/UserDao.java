package com.bsuir.by.library.dao.implementation.user.implementation;

import com.bsuir.by.library.bean.User;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.AbstractDaoController;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.user.IUserDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class UserDao extends AbstractDaoController<User, Integer> implements IUserDao {
    @Override
    public void updateFileById(Map<String, String[]> parameterMap, String dbDataLanguage, int id) {

    }

    @Override
    public User getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public User update(User user) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }


    @Override
    protected List<User> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException, DataControllerException {
        LinkedList<User> result = new LinkedList<User>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setAge(rs.getInt("lib_user_age"));
                user.setFemale(rs.getString("lib_user_female"));
                user.setGender(rs.getInt("lib_user_gender"));
                user.setId(rs.getInt("lib_user_id"));
                user.setLogin(rs.getString("lib_user_login"));
                user.setName(rs.getString("lib_user_name"));
                user.setPassword(rs.getString("lib_user_password"));
                user.setStatus(rs.getInt("lib_user_status"));
                user.setUserLastOnlineDate(rs.getTimestamp("lib_user_online"));
                user.setUserAvatar(rs.getString("lib_user_avatar"));
                result.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException, NoSuchAlgorithmException {
        String userLogin = String.valueOf(parameterMap.get("userLogin"));
        String userPassword = String.valueOf(parameterMap.get("userPassword"));
        String userName = String.valueOf(parameterMap.get("userName"));
        String userSurname = String.valueOf(parameterMap.get("userSurname"));
        String userGender = String.valueOf(parameterMap.get("userGender"));
        String userAge = String.valueOf(parameterMap.get("userAge"));
        String userStatus;
        if (parameterMap.containsKey("userStatus")) {
            userStatus = String.valueOf(parameterMap.get("userStatus"));
        } else
            userStatus = "1";
        String sql = "INSERT INTO lib_user(lib_user_login,lib_user_password,lib_user_name,lib_user_female,lib_user_gender,lib_user_age,lib_user_status)" +
                "VALUES (?,?,?,?,?,?,?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        StringBuffer code = new StringBuffer();
        stmt.setString(1, userLogin);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte bytes[] = userPassword.getBytes();
        byte digest[] = messageDigest.digest(bytes); //create code
        for (int i = 0; i < digest.length; ++i) {
            code.append(Integer.toHexString(0x0100 + (digest[i] & 0x00FF)).substring(1));
        }
        stmt.setString(2, String.valueOf(code));
        stmt.setString(3, userName);
        stmt.setString(4, userSurname);
        stmt.setInt(5, Integer.parseInt(userGender));
        stmt.setInt(6, Integer.parseInt(userAge));
        stmt.setInt(7, Integer.parseInt(userStatus));
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap2, Connection connection, String dbDataLanguage) throws SQLException {
        String formData = String.valueOf(parameterMap2.get("formData"));
        JSONObject answearObj = (JSONObject) JSONValue.parse(formData);
        String[] rowThatNeedToUpdate = String.valueOf(parameterMap2.get("checkBox")).split(",");
        JSONArray id = (JSONArray) answearObj.get("userId");
        JSONArray userLogin = (JSONArray) answearObj.get("userLogin");
        JSONArray userName = (JSONArray) answearObj.get("userName");
        JSONArray userSurname = (JSONArray) answearObj.get("userSurname");
        JSONArray userGender = (JSONArray) answearObj.get("userGender");
        JSONArray userAge = (JSONArray) answearObj.get("userAge");
        JSONArray userStatus = (JSONArray) answearObj.get("userStatus");
        JSONArray userPassword = (JSONArray) answearObj.get("userPassword");
        String sql = "UPDATE lib_user SET lib_user_login=?,lib_user_password=?,lib_user_name=?,lib_user_female=?,lib_user_gender=?,lib_user_age=?," +
                "lib_user_status=? WHERE lib_user_id=?";
        for (String i : rowThatNeedToUpdate) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, String.valueOf(userLogin.get(j)));
            stmt.setString(2, String.valueOf(userPassword.get(j)));
            stmt.setString(3, String.valueOf(userName.get(j)));
            stmt.setString(4, String.valueOf(userSurname.get(j)));
            stmt.setInt(5, Integer.parseInt(String.valueOf(userGender.get(j))));
            stmt.setInt(6, Integer.parseInt(String.valueOf(userAge.get(j))));
            stmt.setInt(7, Integer.parseInt(String.valueOf(userStatus.get(j))));
            stmt.setInt(8, Integer.parseInt(String.valueOf(id.get(j))));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete = String.valueOf(parameterMap.get("checkBox")).split(",");
        String formData = String.valueOf(parameterMap.get("formData"));
        JSONObject answearObj = (JSONObject) JSONValue.parse(formData);
        JSONArray id = (JSONArray) answearObj.get("userId");
        String sql = "DELETE FROM lib_user WHERE lib_user_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(String.valueOf(id.get(j))));
            stmt.execute();

        }

    }

    @Override
    protected void updateData(String s, Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(s);
        stmt.execute();
    }
}
