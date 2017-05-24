package com.bsuir.by.library.command;

import com.bsuir.by.library.command.exception.CommandException;
import com.bsuir.by.library.controller.SessionController;
import com.bsuir.by.library.controller.data.UserDataController;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.exception.DaoException;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 05.05.2017.
 */
public class UpdateUserOnlineCommand extends Command {
    public UpdateUserOnlineCommand(CommandManager commandManager) {
    }

    @Override
    List<Entity> invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) throws DaoException, CommandException, DataControllerException, SQLException {
        int userId = sessionController.getUserId();
        if (userId > 0) {
            UserDataController userDataController = new UserDataController();
            try {
                userDataController.updateUserOnline(userId);
            } catch (Exception e) {
                throw new CommandException(e);
            }
            sessionController.setRequest("success");
        } else
            sessionController.setRequest("error");
        return null;
    }
}
