package com.bsuir.by.library.controller.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.bsuir.by.library.bean.News;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.news.implementation.NewsDao;

public class NewsDataController extends AbstractBeanController<News> {

    public List<News> setTableContent(String dbDataLanguage) throws DataControllerException, DaoException, SQLException {
        NewsDao dbConnection = new NewsDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<News> newsList = new ArrayList<News>();
        try {
            newsList = dbConnection.getDataFromDB("SELECT * FROM lib_news", dbDataLanguage);
        } catch (Exception e) {
            throw new DataControllerException(e);
        }
        return newsList;
        //request.setAttribute("newsList", newsList);

    }

    @Override
    public List getDataIfExist(String userLogin, String userPassword) throws DataControllerException {
        return null;
    }

    public List<News> getLimitedData(String dbDataLanguage, String s, int dataLimit, int id, int page) throws DataControllerException {
        NewsDao dbConnection = new NewsDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<News> newsList = new ArrayList<News>();
        int startNum=(page-1)*dataLimit;
        int endNum=page*dataLimit;
        try {
            newsList = dbConnection.getDataFromDB("SELECT * FROM lib_news ORDER BY lib_news_date DESC LIMIT "+startNum+"," + endNum, dbDataLanguage);
        } catch (Exception e) {
            throw new DataControllerException(e);
        }
        return newsList;
    }

    @Override
    public List<News> getDataById(String dbDataLanguage, int id) throws DataControllerException {
        NewsDao dbConnection = new NewsDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<News> newsList = new ArrayList<News>();
        try {
            newsList = dbConnection.getDataFromDB("SELECT * FROM lib_news WHERE lib_news_id=" + id, dbDataLanguage);
        } catch (Exception e) {
            throw new DataControllerException(e);
        }
        return newsList;
    }
}
