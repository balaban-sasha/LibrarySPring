package com.bsuir.by.library.controller.data;

import com.bsuir.by.library.bean.News;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.exception.DaoException;

import javax.swing.text.html.parser.Entity;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Саша on 21.04.2017.
 */
public abstract class AbstractBeanController<Entity> implements BeanController<Entity> {
    public abstract List<Entity> setTableContent(String dbDataLanguage)  throws DataControllerException, DaoException, SQLException;
    public abstract List<Entity> getDataIfExist(String firstParam,String secondParam) throws DataControllerException, DaoException, SQLException, NoSuchAlgorithmException;

    public List<Entity> getContentByLastId(String dbDataLanguage, int lastId) throws DataControllerException {return null;};

    public List<Entity> getContentByTime(String dbDataLanguage, Timestamp date) throws SQLException, DaoException, DataControllerException {
        return null;
    }

    public List<Entity> getOneRowData(String dbDataLanguage, String s) throws DataControllerException {
        return null;
    }

    public List<Entity> getLimitedData(String dbDataLanguage, String s, int dataLimit, int id, int page) throws DataControllerException {
        return null;
    }

    public List<Entity> getDataById(String dbDataLanguage, int id) throws DataControllerException {
        return null;
    }

}
