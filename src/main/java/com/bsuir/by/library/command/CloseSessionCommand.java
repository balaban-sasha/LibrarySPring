package com.bsuir.by.library.command;

import com.bsuir.by.library.bean.User;
import com.bsuir.by.library.command.exception.CommandException;
import com.bsuir.by.library.controller.SessionController;
import com.bsuir.by.library.controller.data.AbstractBeanController;
import com.bsuir.by.library.controller.data.BeanControllerFactory;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.exception.DaoException;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 04.05.2017.
 */
public class CloseSessionCommand extends Command{
        public CloseSessionCommand(CommandManager commandManager) {
            this.commandManager = commandManager;
        }

        @Override
        List<Entity> invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) throws DaoException, CommandException, DataControllerException, SQLException {
                sessionController.setUserId(0);
                sessionController.setRequest("success");
            return null;
        }
}
