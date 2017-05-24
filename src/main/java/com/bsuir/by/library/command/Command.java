package com.bsuir.by.library.command;

import com.bsuir.by.library.command.exception.CommandException;
import com.bsuir.by.library.controller.SessionController;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.exception.DaoException;

import javax.swing.text.html.parser.Entity;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 18.04.2017.
 */
public abstract class Command {
    protected CommandManager commandManager;
    abstract List<Entity> invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) throws DaoException, CommandException, DataControllerException, SQLException, NoSuchAlgorithmException;
}
