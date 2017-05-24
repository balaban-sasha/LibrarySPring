package com.bsuir.by.library.command;

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
 * Created by Саша on 18.04.2017.
 */
public class CreateCommand extends Command {
    public CreateCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    List<Entity>  invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) throws DaoException, DataControllerException, SQLException {
        String tableName = String.valueOf(parameterMap.get("tableName"));
        List<Entity> entities=null;
        if ((tableName != null)) {
            BeanControllerFactory beanControllerFactory = new BeanControllerFactory();
            AbstractBeanController beanController = beanControllerFactory.getBeanController(tableName);
            entities =getEntityList(beanController,dbDataLanguage);
        }
        commandManager.create(this);
        return entities;
    }
    List<Entity> getEntityList(AbstractBeanController beanController,String dbDataLanguage) throws DaoException, DataControllerException, SQLException {
        List<Entity> entityList= beanController.setTableContent(dbDataLanguage);
        //request.getRequestDispatcher("tables.jsp").forward(request, response);}
        return entityList;
    }
}
