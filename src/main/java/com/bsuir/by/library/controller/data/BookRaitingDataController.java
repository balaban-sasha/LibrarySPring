package com.bsuir.by.library.controller.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.bsuir.by.library.bean.Bean;
import com.bsuir.by.library.bean.BookRaiting;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.bookRaiting.implementation.BookRaitingDao;

public class BookRaitingDataController extends AbstractBeanController<BookRaiting> {
	@Override
	public List<BookRaiting> setTableContent(String dbDataLanguage) throws DataControllerException, DaoException, SQLException
	{
		BookRaitingDao dbConnection = new BookRaitingDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"),resourceBundle.getString("login"),resourceBundle.getString("password"));
		List<BookRaiting> bookRaitingList=new ArrayList<BookRaiting>();
		try{
			bookRaitingList=dbConnection.getDataFromDB("SELECT * FROM lib_book_raiting",dbDataLanguage);
		}catch(Exception e)
		{
			throw new DataControllerException(e);	
		}
		//request.setAttribute("bookRaitingList", bookRaitingList);
		return bookRaitingList;
	}
	@Override
	public List getDataIfExist(String userLogin, String userPassword) throws DataControllerException {
		return null;
	}
}
