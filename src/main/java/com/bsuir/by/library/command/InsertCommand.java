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
public class InsertCommand extends Command {
    public InsertCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    List<Entity> invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) {
        List<Entity> entities = null;
        DAOFactory daoFactory = new DAOFactory();
        String tablesName = String.valueOf(parameterMap.get("table"));
        if (parameterMap.containsKey("chatSenderId")) {
            String userId = String.valueOf(parameterMap.get("chatSenderId"));
            String[] userIds = new String[1];
            userIds[0] = userId;
            parameterMap.put("chatSenderId", userIds);
        }
        if ((tablesName != null) && (tablesName.equals("Chat")) && (!parameterMap.containsKey("chatSenderId") && (sessionController.getUserId() != 0))) {
            int userId = sessionController.getUserId();
            String[] userIds = new String[1];
            userIds[0] = String.valueOf(userId);
            parameterMap.put("chatSenderId", userIds);
        } else {
            if ((tablesName.equals("NewsComment") && ((sessionController.getUserId() != 0) || (Integer.parseInt(toString().valueOf(parameterMap.get("newsCommentUserId"))) > 0)))) {
                int senderId = Integer.parseInt(toString().valueOf(parameterMap.get("newsCommentUserId")));
                String[] commentSenderId = new String[1];
                if (senderId != 0) {
                    commentSenderId[0] = String.valueOf(senderId);
                } else {
                    commentSenderId[0] = String.valueOf(sessionController.getUserId());
                }
                parameterMap.put("newsCommentUserId", commentSenderId);
            } else if ((tablesName.equals("Comment") && ((sessionController.getUserId() != 0) || (parameterMap.containsKey("commentUserId"))))) {
                int senderId;
                if (parameterMap.containsKey("commentUserId"))
                    senderId = Integer.parseInt(toString().valueOf(parameterMap.get("commentUserId")));
                else
                    senderId = 0;
                String[] commentSenderId = new String[1];
                if (senderId != 0) {
                    commentSenderId[0] = String.valueOf(senderId);
                } else {
                    commentSenderId[0] = String.valueOf(sessionController.getUserId());
                }
                parameterMap.put("comentSenderId", commentSenderId);
            }
        }
        AbstractDaoController controller = daoFactory.getController(tablesName);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        controller.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        try {
            controller.insert(parameterMap);
        } catch (Exception e) {
            new CommandException(e);
        }
        commandManager.insert(this);
        return null;
    }
}
