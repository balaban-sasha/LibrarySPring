package com.bsuir.by.library.command;

import com.bsuir.by.library.command.exception.CommandException;
import com.bsuir.by.library.controller.SessionController;
import com.bsuir.by.library.dao.AbstractDaoController;
import com.bsuir.by.library.dao.factory.DAOFactory;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Саша on 18.04.2017.
 */
public class UpdateCommand extends Command {
    public UpdateCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    List<Entity> invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) throws CommandException {
        List<Entity> entities=null;
        DAOFactory daoFactory = new DAOFactory();
        String tablesName = String.valueOf(parameterMap.get("table"));
        AbstractDaoController controller = daoFactory.getController(tablesName);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        controller.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        String checkRow = String.valueOf(parameterMap.get("checkBox"));
        if (checkRow != null) {
            try {
                controller.updateAll(parameterMap,dbDataLanguage);
            } catch (Exception e) {
                new CommandException(e);
            }
        }
        commandManager.update(this);
        return null;
    }
}
