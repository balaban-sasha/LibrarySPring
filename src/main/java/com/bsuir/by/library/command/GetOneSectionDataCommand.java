package com.bsuir.by.library.command;

import com.bsuir.by.library.command.exception.CommandException;
import com.bsuir.by.library.controller.SessionController;
import com.bsuir.by.library.controller.data.AbstractBeanController;
import com.bsuir.by.library.controller.data.BeanControllerFactory;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.exception.DaoException;

import javax.swing.text.html.parser.Entity;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 07.05.2017.
 */
public class GetOneSectionDataCommand extends Command {
    public GetOneSectionDataCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }


    @Override
    List<Entity> invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) throws DaoException, CommandException, DataControllerException, SQLException {
        String tableName = String.valueOf(parameterMap.get("tableName"));
        String rowName = String.valueOf(parameterMap.get("rowName"));
        List<Entity> entities = null;
        if ((rowName != null)) {
            BeanControllerFactory beanControllerFactory = new BeanControllerFactory();
            AbstractBeanController beanController = beanControllerFactory.getBeanController(tableName);
            entities = getEntityList(beanController, dbDataLanguage, rowName);
        }
        return entities;
    }

    List<Entity> getEntityList(AbstractBeanController beanController, String dbDataLanguage, String s) throws DaoException, DataControllerException, SQLException {
        List<Entity> entityList = beanController.getOneRowData(dbDataLanguage, s);
        //request.getRequestDispatcher("tables.jsp").forward(request, response);}
        return entityList;
    }
}
