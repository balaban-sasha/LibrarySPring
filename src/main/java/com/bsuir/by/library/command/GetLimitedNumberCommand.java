package com.bsuir.by.library.command;

import com.sun.javafx.image.IntPixelGetter;
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
 * Created by Саша on 07.05.2017.
 */
public class GetLimitedNumberCommand extends Command {
    public GetLimitedNumberCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    List<Entity> invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) throws DaoException, CommandException, DataControllerException, SQLException, NoSuchAlgorithmException {
        String tableName = String.valueOf(parameterMap.get("tableName"));
        String limit = String.valueOf(parameterMap.get("limit"));
        int page=1;
        if(parameterMap.get("page")!=null)
            page= Integer.valueOf(String.valueOf(parameterMap.get("page")));
        int dataLimit = 10;
        if (limit != null)
            dataLimit = Integer.valueOf(limit);
        List<Entity> entities = null;
        if ((tableName != null)) {
            String ids=null;
            int id=0;
            if(parameterMap.containsKey("id"))
                ids= String.valueOf(parameterMap.get("id"));
            if(ids!=null)
                id=Integer.valueOf(ids);
            BeanControllerFactory beanControllerFactory = new BeanControllerFactory();
            AbstractBeanController beanController = beanControllerFactory.getBeanController(tableName);
            entities = getEntityList(beanController, dbDataLanguage, tableName, dataLimit,id,page);
        }
        return entities;
    }

    List<Entity> getEntityList(AbstractBeanController beanController, String dbDataLanguage, String s, int dataLimit, int id, int page) throws DaoException, DataControllerException, SQLException {
        List<Entity> entityList = beanController.getLimitedData(dbDataLanguage, s, dataLimit,id,page);
        //request.getRequestDispatcher("tables.jsp").forward(request, response);}
        return entityList;
    }
}
