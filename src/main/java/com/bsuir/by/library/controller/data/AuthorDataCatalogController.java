package com.bsuir.by.library.controller.data;

import com.bsuir.by.library.bean.Author;
import com.bsuir.by.library.bean.AuthorCatalog;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.author.implementation.AuthorDao;
import com.bsuir.by.library.dao.implementation.authorCatalog.implementation.AuthorCatalogDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Саша on 28.03.2017.
 */
public class AuthorDataCatalogController extends AbstractBeanController {

    @Override
    public List<AuthorCatalog> setTableContent(String dbDataLanguage)
            throws DataControllerException, DaoException, SQLException {
        AuthorCatalogDao dbConnection = new AuthorCatalogDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"),
                resourceBundle.getString("password"));
        List<AuthorCatalog> authorCatalogList;
        try {
            authorCatalogList = dbConnection.getDataFromDB("SELECT * FROM lib_author_catalog",dbDataLanguage);
        } catch (Exception e) {
            throw new DataControllerException(e);
        }
        return authorCatalogList;
        //request.setAttribute("authorCatalogList", authorCatalogList);

    }

    @Override
    public List getDataIfExist(String userLogin, String userPassword) throws DataControllerException {
        return null;
    }

}
