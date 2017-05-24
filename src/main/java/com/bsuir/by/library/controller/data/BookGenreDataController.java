package com.bsuir.by.library.controller.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.bsuir.by.library.bean.Bean;
import com.bsuir.by.library.bean.BookGenre;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.bookGenre.implementation.BookGenreDao;

public class BookGenreDataController extends AbstractBeanController<BookGenre> {
	@Override
	public List<BookGenre> setTableContent(String dbDataLanguage) throws DataControllerException, DaoException, SQLException
	{
		BookGenreDao dbConnection = new BookGenreDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"),resourceBundle.getString("login"),resourceBundle.getString("password"));
		List<BookGenre> bookGenreList=new ArrayList<BookGenre>();
		try{
			bookGenreList=dbConnection.getDataFromDB("SELECT * FROM lib_book_genre",dbDataLanguage);
		}catch(Exception e)
		{
			throw new DataControllerException(e);	
		}
		return bookGenreList;
		//request.setAttribute("bookGenreList", bookGenreList);
		
	}
	@Override
	public List getDataIfExist(String userLogin, String userPassword) throws DataControllerException {
		return null;
	}

    public List<BookGenre> getDataByBookId(int id) throws DataControllerException {
		BookGenreDao dbConnection = new BookGenreDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
		List<BookGenre> bookGenreList = new ArrayList<BookGenre>();
		try {
			bookGenreList = dbConnection.getDataFromDB("SELECT * FROM lib_book_genre WHERE `lib_book_genre_id`=" + id, "");
		} catch (Exception e) {
			throw new DataControllerException(e);
		}

		if (bookGenreList.isEmpty())
			return null;
		else
			return bookGenreList;
    }
}
