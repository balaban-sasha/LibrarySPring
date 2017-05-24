package com.bsuir.by.library.command;

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
 * Created by Саша on 22.05.2017.
 */
public class GetOneSectionByIdCommand extends Command {
    public GetOneSectionByIdCommand(CommandManager commandManager) {
        super();
    }List<Entity> invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) throws DaoException, CommandException, DataControllerException, SQLException {
        String tableName = String.valueOf(parameterMap.get("tableName"));
        int id= Integer.parseInt(String.valueOf(parameterMap.get("id")));
        List<Entity> entities = null;

        if ((tableName != null)&&(id!=0)) {
            BeanControllerFactory beanControllerFactory = new BeanControllerFactory();
            AbstractBeanController beanController = beanControllerFactory.getBeanController(tableName);
            entities = getEntityList(beanController, dbDataLanguage,id);
        }
        return entities;
    }

    List<Entity> getEntityList(AbstractBeanController beanController, String dbDataLanguage, int id) throws DaoException, DataControllerException, SQLException {
        List<Entity> entityList = beanController.getDataById(dbDataLanguage,id);
        //request.getRequestDispatcher("tables.jsp").forward(request, response);}
        return entityList;
    }
}
