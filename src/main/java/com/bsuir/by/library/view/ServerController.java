package com.bsuir.by.library.view;

import com.bsuir.by.library.bean.Chat;
import com.bsuir.by.library.command.CommandInvoker;
import com.bsuir.by.library.command.CommandManager;
import com.bsuir.by.library.command.exception.CommandException;
import com.bsuir.by.library.controller.LocaleController;
import com.bsuir.by.library.controller.SessionController;
import com.bsuir.by.library.controller.data.ChatDataController;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.exception.DaoException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import netscape.javascript.JSObject;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.nio.ch.IOUtil;

import javax.jws.WebParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;
import javax.swing.text.html.parser.Parser;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bsuir.by.library.controller.docs.DocumentController;

@Controller
@SessionAttributes(types = SessionController.class)
public class ServerController extends HttpServlet {


    private CommandInvoker commandInvoker;
    private CommandManager commandManager;

    @RequestMapping(value = "/tables/action", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String tableAction(@RequestParam Map<String, String[]> requestData, Model model, SessionController sessionController) throws CommandException, DataControllerException, NoSuchAlgorithmException, SQLException, DaoException {
        List<Entity> entityList = null;
        commandManager = new CommandManager();
        entityList = commandInvoker.invoke(String.valueOf(requestData.get("action")), requestData, "", sessionController);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String json = gson.toJson(entityList);
        return "success";
    }

    @RequestMapping(value = "/tables", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView table(@RequestParam Map<String, String[]> obj) throws CommandException, DataControllerException, NoSuchAlgorithmException, SQLException, DaoException {

        commandManager = new CommandManager();
        commandInvoker = new CommandInvoker(commandManager);
        String tableName = String.valueOf(obj.get("tableName"));
        //String createAction = String.valueOf(obj.get("createAction"));
        String language = String.valueOf(obj.get("language"));
        //String dbDataLanguage;
        String country;
        String languages;
        if (language != null) {
            String[] planguage = language.split("_");
            //dbDataLanguage = "_" + planguage[0];
            country = planguage[1];
            languages = planguage[0];
        } else {
            //  dbDataLanguage = "_ru";
            country = "Ru";
            languages = "ru";
        }
        ModelAndView model = new ModelAndView("tables");
      /*  if ((dbDataLanguage.equals("_ru")) || (dbDataLanguage.equals("_")))
            dbDataLanguage = "";
        List<Entity> entityList = null;
        SessionController sessionController = new SessionController();
        if (createAction != null) {
            entityList = commandInvoker.invoke(createAction, obj, dbDataLanguage, sessionController);
            if (entityList != null) {
                model.addObject(tableName, entityList);
            }
        }*/
        LocaleController localeController = new LocaleController();
        localeController.changeLocale(languages, country);
        localeController.loadData(model);
        model.addObject("TableName", tableName + "Table");
        return model;
    }


    @RequestMapping(value = "/library/getData", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String getData(@RequestParam Map<String, String[]> requestData, Model model, SessionController sessionController) throws CommandException, DataControllerException, NoSuchAlgorithmException, SQLException, DaoException {

        List<Entity> entityList = null;
        commandManager = new CommandManager();
        if (!model.containsAttribute("session")) {
            model.addAttribute("session", new SessionController());
        }
        commandInvoker = new CommandInvoker(commandManager);
        entityList = commandInvoker.invoke(String.valueOf(requestData.get("action")), requestData, "", sessionController);
        if (String.valueOf(requestData.get("action")).equals("auth")) {
            model.addAttribute("session", sessionController);
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String json = gson.toJson(entityList);
        return json;
    }

    @RequestMapping(value = "/library/insertData", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String insertData(@RequestParam Map<String, String[]> requestData, Model model, SessionController sessionController) throws CommandException, DataControllerException, NoSuchAlgorithmException, SQLException, DaoException {
        List<Entity> entityList = null;
        commandManager = new CommandManager();
        entityList = commandInvoker.invoke(String.valueOf(requestData.get("action")), requestData, "", sessionController);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String json = gson.toJson(entityList);
        return "success";
    }


    @RequestMapping(value = "/library", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView library(Model model, SessionController sessionController) {
        ModelAndView modelAndView = new ModelAndView("index");
        if (!model.containsAttribute("session"))
            model.addAttribute("session", new SessionController());
        if (sessionController.getUserId() != 0) {
            model.addAttribute("templateName", "personalRoom.jsp");
        } else
            model.addAttribute("templateName", "authAndRegistry.jsp");
        return modelAndView;

    }

    @RequestMapping(value = "/chat", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView chat(SessionController sessionController, Model models) {
        if (sessionController.getUserId() == 0)
            return new ModelAndView("redirect:/library");
        else {
            ModelAndView model = new ModelAndView("chat");
            if (sessionController.getUserId() != 0) {
                model.addObject("templateName", "personalRoom.jsp");
            } else
                model.addObject("templateName", "authAndRegistry.jsp");
            return model;
        }


    }

    @RequestMapping(value = "/news", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView news(@RequestParam Map<String, String[]> requestData, SessionController sessionController) throws CommandException, DataControllerException, NoSuchAlgorithmException, SQLException, DaoException {
        ModelAndView model = null;
      /*  commandManager = new CommandManager();
        commandInvoker = new CommandInvoker(commandManager);*/
        if (requestData.containsKey("id")) {
            // List<Entity> entityList=commandInvoker.invoke(String.valueOf(requestData.get("action")),requestData,"",null);
            model = new ModelAndView("oneNews");
            //  model.addObject("section",entityList);

        } else
            model = new ModelAndView("news");
        if (sessionController.getUserId() != 0) {
            model.addObject("templateName", "personalRoom.jsp");
        } else
            model.addObject("templateName", "authAndRegistry.jsp");
        return model;

    }


    @RequestMapping(value = "/author", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView author(@RequestParam Map<String, String[]> requestData, SessionController sessionController) {
        ModelAndView model = null;
      /*  commandManager = new CommandManager();
        commandInvoker = new CommandInvoker(commandManager);*/
        if (requestData.containsKey("id")) {
            // List<Entity> entityList=commandInvoker.invoke(String.valueOf(requestData.get("action")),requestData,"",null);
            model = new ModelAndView("oneAuthor");
            //  model.addObject("section",entityList);

        } else
            model = new ModelAndView("author");
        if (sessionController.getUserId() != 0) {
            model.addObject("templateName", "personalRoom.jsp");
        } else
            model.addObject("templateName", "authAndRegistry.jsp");
        return model;

    }

    @RequestMapping(value = "/books", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView books(@RequestParam Map<String, String[]> requestData, SessionController sessionController) {
        ModelAndView model = null;
      /*  commandManager = new CommandManager();
        commandInvoker = new CommandInvoker(commandManager);*/
        if (requestData.containsKey("id")) {
            // List<Entity> entityList=commandInvoker.invoke(String.valueOf(requestData.get("action")),requestData,"",null);
            model = new ModelAndView("oneBook");
            //  model.addObject("section",entityList);

        } else
            model = new ModelAndView("book");
        if (sessionController.getUserId() != 0) {
            model.addObject("templateName", "personalRoom.jsp");
        } else
            model.addObject("templateName", "authAndRegistry.jsp");
        return model;

    }

    @RequestMapping(value = "/documents/download", method = RequestMethod.GET)
    public void servicesDocumentsDownload(String mime, String ent, String rec, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        PDDocument pdf = new PDDocument();
        if (ent.equals("chat")) {
            if (mime.equals("pdf"))
                DocumentController.pdfServices(null, response);
            if (mime.equals("excel")) {
                DocumentController.xlsChat(null, response);
            }
            if (mime.equals("csv")) {
                DocumentController.csvChat(null, response);
            }
        }
        if (ent.equals("comment")) {
            if (mime.equals("pdf"))
                DocumentController.pdfBook(null, response);
            if (mime.equals("excel")) {
                DocumentController.xlsComment(null, response);
            }
            if (mime.equals("csv")) {
                DocumentController.csvComment(null, response);
            }
        }
        if (ent.equals("news")) {
            if (mime.equals("pdf"))
                DocumentController.pdfNews(null, response);
            if (mime.equals("excel")) {
                DocumentController.xlsNews(null, response);
            }
            if (mime.equals("csv")) {
                DocumentController.csvNews(null, response);
            }
        }
    }
}
