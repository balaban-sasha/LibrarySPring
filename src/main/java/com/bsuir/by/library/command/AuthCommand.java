package com.bsuir.by.library.command;

import com.bsuir.by.library.bean.User;
import com.bsuir.by.library.command.exception.CommandException;
import com.bsuir.by.library.controller.SessionController;
import com.bsuir.by.library.controller.data.AbstractBeanController;
import com.bsuir.by.library.controller.data.BeanControllerFactory;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.exception.DaoException;

import javax.swing.text.html.parser.Entity;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 25.04.2017.
 */
public class AuthCommand extends Command {
    public AuthCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    List<Entity> invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) throws DaoException, CommandException, DataControllerException, SQLException, NoSuchAlgorithmException {
        List<User> entities=null;
        String tableName = String.valueOf(parameterMap.get("table"));
        BeanControllerFactory beanControllerFactory = new BeanControllerFactory();
        AbstractBeanController beanController = beanControllerFactory.getBeanController(tableName);
        entities =beanController.getDataIfExist(String.valueOf(parameterMap.get("userLogin")), String.valueOf(parameterMap.get("userPassword")));
        if(!entities.isEmpty())
        {
            int userId = 0;
            for(User entity : entities)
            {
                userId=entity.getId();
            }
            sessionController.setUserId(userId);
            sessionController.setRequest("success");
        }
        return null;
    }
}
