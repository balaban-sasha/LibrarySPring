package com.bsuir.by.library.controller.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.bsuir.by.library.bean.Bean;
import com.bsuir.by.library.bean.BookCatalog;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.bookCatalog.implementation.BookCatalogDao;

public class BookCatalogDataController extends AbstractBeanController<BookCatalog> {
	@Override
	public List<BookCatalog> setTableContent(String dbDataLanguage) throws DataControllerException, DaoException, SQLException
	{
		BookCatalogDao dbConnection = new BookCatalogDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"),resourceBundle.getString("login"),resourceBundle.getString("password"));
		List<BookCatalog> bookCatalogList=new ArrayList<BookCatalog>();
		try{
			bookCatalogList=dbConnection.getDataFromDB("SELECT * FROM lib_book_catalog",dbDataLanguage);
		}catch(Exception e)
		{
			throw new DataControllerException(e);
		}
		return bookCatalogList;
		//request.setAttribute("bookCatalogList", bookCatalogList);
		
	}
	@Override
	public List getDataIfExist(String userLogin, String userPassword) throws DataControllerException {
		return null;
	}
}
