package com.bsuir.by.library.controller.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.bsuir.by.library.bean.Author;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.author.implementation.AuthorDao;

public class AuthorDataController extends AbstractBeanController {
    @Override
    public List<Author> setTableContent(String dbDataLanguage) throws DataControllerException, DaoException, SQLException {
        AuthorDao dbConnection = new AuthorDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Author> authorList = new ArrayList<Author>();
        try {
            authorList = dbConnection.getDataFromDB("SELECT * FROM lib_author", dbDataLanguage);
        } catch (Exception e) {
            throw new DataControllerException(e);
        }
        //request.setAttribute("authorList", authorList);
        return authorList;
    }

    @Override
    public List getDataIfExist(String userLogin, String userPassword) throws DataControllerException {
        return null;
    }

    @Override
    public List<Author> getDataById(String dbDataLanguage, int authorId) throws DataControllerException {
        AuthorDao dbConnection = new AuthorDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Author> authorList = new ArrayList<Author>();
        try {
            authorList = dbConnection.getDataFromDB("SELECT * FROM lib_author WHERE lib_author_id=" + authorId, "");
        } catch (Exception e) {
            throw new DataControllerException(e);
        }
        return authorList;
    }

    public List<Author> getLimitedData(String dbDataLanguage, String s, int dataLimit, int id, int page) throws DataControllerException {
        AuthorDao dbConnection = new AuthorDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Author> authorList = new ArrayList<Author>();
        int startNum = (page - 1) * dataLimit;
        int endNum = page * dataLimit;
        try {
            authorList = dbConnection.getDataFromDB("SELECT * FROM lib_author ORDER BY lib_author_id DESC LIMIT " + startNum + "," + endNum, dbDataLanguage);
        } catch (Exception e) {
            throw new DataControllerException(e);
        }
        return authorList;
    }
}
