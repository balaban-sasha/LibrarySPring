package com.bsuir.by.library.controller.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.text.html.parser.Entity;

import com.bsuir.by.library.bean.Message;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.message.implementation.MessageDao;

public class MessageDataController  extends AbstractBeanController<Message> {

	public List<Message> setTableContent(String dbDataLanguage) throws DataControllerException, DaoException, SQLException
	{
		MessageDao dbConnection = new MessageDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"),resourceBundle.getString("login"),resourceBundle.getString("password"));
		List<Message> messageList=new ArrayList<Message>();
		try{
			messageList=dbConnection.getDataFromDB("SELECT * FROM lib_message",dbDataLanguage);
		}catch(Exception e)
		{
			throw new DataControllerException(e);
		}
		return messageList;
		//request.setAttribute("messageList", messageList);
		
	}
	@Override
	public List getDataIfExist(String userLogin, String userPassword) throws DataControllerException {
		return null;
	}
}
