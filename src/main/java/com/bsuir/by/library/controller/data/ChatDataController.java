package com.bsuir.by.library.controller.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.text.html.parser.Entity;

import com.bsuir.by.library.bean.BookRaiting;
import com.bsuir.by.library.bean.Chat;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.chat.implementation.ChatDao;

public class ChatDataController  extends AbstractBeanController<Chat> {

	public List<Chat> setTableContent(String dbDataLanguage) throws DataControllerException, DaoException, SQLException
	{
		ChatDao dbConnection = new ChatDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"),resourceBundle.getString("login"),resourceBundle.getString("password"));
		List<Chat> chatList=new ArrayList<Chat>();
		try{
			chatList=dbConnection.getDataFromDB("SELECT * FROM lib_chat",dbDataLanguage);
		}catch(Exception e)
		{
			throw new DataControllerException(e);
		}
		return chatList;
		//request.setAttribute("chatList", chatList);
		
	}
	@Override
	public List getDataIfExist(String userLogin, String userPassword) throws DataControllerException {
		return null;
	}
	@Override
	public List<Chat> getContentByLastId(String dbDataLanguage, int lastId) throws DataControllerException {
		ChatDao dbConnection = new ChatDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"),resourceBundle.getString("login"),resourceBundle.getString("password"));
		List<Chat> chatList=new ArrayList<Chat>();
		try{
			chatList=dbConnection.getDataFromDB("SELECT * FROM lib_chat WHERE lib_chat_id>"+lastId+" ORDER BY lib_chat_message_date DESC LIMIT 50",dbDataLanguage);
		}catch(Exception e)
		{
			throw new DataControllerException(e);
		}
		return chatList;

	}
}
