package com.bsuir.by.library.controller.docs;

/**
 * Created by Саша on 24.05.2017.
 */

import com.bsuir.by.library.bean.Book;
import com.bsuir.by.library.bean.Chat;
import com.bsuir.by.library.bean.Comment;
import com.bsuir.by.library.bean.News;
import com.bsuir.by.library.controller.data.*;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.exception.DaoException;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Array;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static org.json.XMLTokener.entity;

public class DocumentController {

    public static void pdfServices(Chat chat, HttpServletResponse response) throws Exception {

        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);
        PDImageXObject pdImage = PDImageXObject.createFromFile("C:/logo.jpeg", doc);
        PDImageXObject line = PDImageXObject.createFromFile("C:/line.png", doc);
        PDPageContentStream contents = new PDPageContentStream(doc, page);
        contents.drawImage(line, 60, 700);

        ChatDataController chatDataController = new ChatDataController();
        java.util.List<Chat> chats = chatDataController.setTableContent("");
        contents.beginText();
        contents.setFont(PDType1Font.TIMES_ROMAN, 12);
        contents.newLineAtOffset(80, 650);
        String text = "Number of chat messages:  " + chats.size();
        contents.showText(text);
        contents.endText();

        contents.beginText();
        contents.setFont(PDType1Font.TIMES_ROMAN, 12);
        contents.newLineAtOffset(80, 630);
        text = "Last message from: " + chats.get(chats.size() - 1).getUserName();
        contents.showText(text);
        contents.endText();

        contents.beginText();
        contents.setFont(PDType1Font.TIMES_ROMAN, 12);
        contents.newLineAtOffset(80, 610);
        text = "Message: " + chats.get(chats.size() - 1).getText();
        contents.showText(text);
        contents.endText();

        contents.drawImage(pdImage, 0, 0);
        contents.close();

        doc.save(response.getOutputStream());
        response.getOutputStream().flush();
        doc.save("C:/Users/Саша/Downloads/JavaEEWithSpring-master/Demo10/src/main/webapp/resource/sample.pdf");
        doc.close();
    }

    public static void pdfBook(Object o, HttpServletResponse response) throws IOException, DaoException, DataControllerException, SQLException {
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);
        PDImageXObject pdImage = PDImageXObject.createFromFile("C:/logo.jpeg", doc);
        PDImageXObject line = PDImageXObject.createFromFile("C:/line.png", doc);
        PDPageContentStream contents = new PDPageContentStream(doc, page);
        contents.drawImage(line, 60, 700);

        CommentDataController commentDataController = new CommentDataController();
        java.util.List<Comment> comments = commentDataController.setTableContent("");
        contents.beginText();
        contents.setFont(PDType1Font.TIMES_ROMAN, 12);
        contents.newLineAtOffset(80, 650);
        String text = "Total number of comments: " + comments.size();
        contents.showText(text);
        contents.endText();

        contents.beginText();
        contents.setFont(PDType1Font.TIMES_ROMAN, 12);
        contents.newLineAtOffset(80, 630);
        text = "Last comment text: " + comments.get(comments.size() - 1).getText();
        contents.showText(text);
        contents.endText();

        UserDataController userDataController = new UserDataController();
        contents.beginText();
        contents.setFont(PDType1Font.TIMES_ROMAN, 12);
        contents.newLineAtOffset(80, 610);
        text = "User name: " + userDataController.getUserLogin(comments.get(comments.size() - 1).getSenderId());
        contents.showText(text);
        contents.endText();


        contents.drawImage(pdImage, 0, 0);
        contents.close();

        doc.save(response.getOutputStream());
        response.getOutputStream().flush();
        doc.save("C:/Users/Саша/Downloads/JavaEEWithSpring-master/Demo10/src/main/webapp/resource/comment.pdf");
        doc.close();
    }

    public static void pdfNews(Object o, HttpServletResponse response) throws IOException, DaoException, DataControllerException, SQLException {
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);
        PDImageXObject pdImage = PDImageXObject.createFromFile("C:/logo.jpeg", doc);
        PDImageXObject line = PDImageXObject.createFromFile("C:/line.png", doc);
        PDPageContentStream contents = new PDPageContentStream(doc, page);
        contents.drawImage(line, 60, 700);

        NewsDataController newsDataController = new NewsDataController();
        java.util.List<News> newsList = newsDataController.setTableContent("");
        contents.beginText();
        contents.setFont(PDType1Font.TIMES_ROMAN, 12);
        contents.newLineAtOffset(80, 650);
        String text = "Total number of news: " + newsList.size();
        contents.showText(text);
        contents.endText();

        contents.beginText();
        contents.setFont(PDType1Font.TIMES_ROMAN, 12);
        contents.newLineAtOffset(80, 630);
        text = "Last news header: " + newsList.get(newsList.size() - 1).getHeader();
        contents.showText(text);
        contents.endText();

        contents.beginText();
        contents.setFont(PDType1Font.TIMES_ROMAN, 12);
        contents.newLineAtOffset(80, 610);
        text = "Last news text: " + newsList.get(newsList.size() - 1).getText();
        contents.showText(text);
        contents.endText();

        contents.beginText();
        contents.setFont(PDType1Font.TIMES_ROMAN, 12);
        contents.newLineAtOffset(80, 590);
        text = "Publicate date: " + newsList.get(newsList.size() - 1).getPublicateDate();
        contents.showText(text);
        contents.endText();


        contents.drawImage(pdImage, 0, 0);
        contents.close();

        doc.save(response.getOutputStream());
        response.getOutputStream().flush();
        doc.save("C:/Users/Саша/Downloads/JavaEEWithSpring-master/Demo10/src/main/webapp/resource/news.pdf");
        doc.close();
    }

    public static void xlsChat(Chat temp, HttpServletResponse response) throws IOException, DaoException, DataControllerException, SQLException {

        ChatDataController chatDataController = new ChatDataController();
        List<Chat> chatList = chatDataController.setTableContent("");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {

            UserDataController userDataController = new UserDataController();
            Map<Integer, Object[]> dataToExcel = new TreeMap<Integer, Object[]>();
            dataToExcel.put(0, new Object[]{"Text", "Data", "UserName "});

            int i = 1;
            for (Chat chat : chatList) {
                dataToExcel.put(i, new Object[]{(String.valueOf(chat.getText())),
                        (dateFormat.format(chat.getPublicateDate())), (userDataController.getUserLogin(chat.getSenderId()))});
                i++;
            }
            xlsDoc(dataToExcel, response, "chat");

        } catch (Exception e)
        {
            new DataControllerException(e);

        }

    }

    public static void xlsComment(Object o, HttpServletResponse response) {
        try {
            CommentDataController commentDataController = new CommentDataController();
            List<Comment> commentList = commentDataController.setTableContent("");
            UserDataController userDataController = new UserDataController();
            BookDataController bookDataController = new BookDataController();
            Map<Integer, Object[]> dataToExcel = new TreeMap<Integer, Object[]>();
            dataToExcel.put(0, new Object[]{"Text", "Data", "Book Name ", "User Name "});
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            int i = 1;
            for (Comment comment : commentList) {
                dataToExcel.put(i, new Object[]{(String.valueOf(comment.getText())),
                        (dateFormat.format(comment.getPublicateDate())), (userDataController.getUserLogin(comment.getSenderId())),
                        (bookDataController.getDataById("", comment.getBookId()).get(0).getBookName())});
                i++;
            }
            xlsDoc(dataToExcel, response, "Comment");

        } catch (Exception e) {
            new DataControllerException(e);

        }
    }

    public static void xlsNews(Object o, HttpServletResponse response) {
        try {

            NewsDataController newsDataController = new NewsDataController();
            List<News> newsList = newsDataController.setTableContent("");
            Map<Integer, Object[]> dataToExcel = new TreeMap<Integer, Object[]>();
            dataToExcel.put(0, new Object[]{"Header", "Text", "Publicate data "});
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            int i = 1;
            for (News news : newsList) {
                dataToExcel.put(i, new Object[]{(String.valueOf(news.getHeader())),
                        (String.valueOf(news.getText())), (dateFormat.format(news.getPublicateDate()))});
                i++;
            }
            xlsDoc(dataToExcel, response, "news");

        } catch (Exception e) {
            new DataControllerException(e);

        }
    }

    public static void xlsDoc(Map<Integer, Object[]> dataToExcel, HttpServletResponse response, String fileName) {

        try {

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Docs");

            Set<Integer> keyset = dataToExcel.keySet();
            int rownum = 0;
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < dataToExcel.get(0).length; i++) {
                list.add(0);
            }
            for (int key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object[] objArr = dataToExcel.get(key);
                int i = 0;
                int cellnum = 0;
                for (Object obj : objArr) {

                    Cell cell = row.createCell(cellnum++);

                    if (obj instanceof String) {
                        cell.setCellValue((String) obj);
                        if (((String) obj).length() > list.get(i))
                            list.set(i, ((String) obj).length());
                    }
                    i++;
                }
            }
            FileOutputStream fos = new FileOutputStream(new File("C:/Users/Саша/Downloads/JavaEEWithSpring-master/Demo10/src/main/webapp/resource/" + fileName + ".xls"));
            workbook.write(fos);
            workbook.close();
            fos.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void csvComment(Object o, HttpServletResponse response) throws IOException, DaoException, DataControllerException, SQLException {
        CommentDataController commentDataController = new CommentDataController();
        List<Comment> commentList = commentDataController.setTableContent("");
        UserDataController userDataController = new UserDataController();
        BookDataController bookDataController = new BookDataController();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String csvFile = "C:/Users/Саша/Downloads/JavaEEWithSpring-master/Demo10/src/main/webapp/resource/comment.csv";
        FileWriter writer = new FileWriter(csvFile);
        CSVUtils.writeLine(writer, Arrays.asList("Text","Date","Book Name","User Name"), ',', '"');
        for (Comment comment : commentList)
            CSVUtils.writeLine(writer, Arrays.asList((String.valueOf(comment.getText())),
                    (dateFormat.format(comment.getPublicateDate())), (userDataController.getUserLogin(comment.getSenderId())),
                    (bookDataController.getDataById("", comment.getBookId()).get(0).getBookName())), ',', '"');
        writer.flush();
        writer.close();

    }

    public static void csvChat(Object o, HttpServletResponse response) throws DaoException, DataControllerException, SQLException, IOException {ChatDataController chatDataController = new ChatDataController();
        List<Chat> chatList = chatDataController.setTableContent("");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String csvFile = "C:/Users/Саша/Downloads/JavaEEWithSpring-master/Demo10/src/main/webapp/resource/chat.csv";
        FileWriter writer = new FileWriter(csvFile);
        CSVUtils.writeLine(writer, Arrays.asList("Text","Date","UserName"), ',', '"');
        for (Chat chat : chatList)
            CSVUtils.writeLine(writer, Arrays.asList(chat.getText(),dateFormat.format(chat.getPublicateDate()),chat.getUserName()), ',', '"');
        writer.flush();
        writer.close();
    }

    public static void csvNews(Object o, HttpServletResponse response) throws IOException, DaoException, DataControllerException, SQLException {
        NewsDataController newsDataController = new NewsDataController();
        List<News> newsList = newsDataController.setTableContent("");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String csvFile = "C:/Users/Саша/Downloads/JavaEEWithSpring-master/Demo10/src/main/webapp/resource/news.csv";
        FileWriter writer = new FileWriter(csvFile);
        CSVUtils.writeLine(writer, Arrays.asList("Text","Date","UserName"), ',', '"');
        for (News news : newsList)
            CSVUtils.writeLine(writer, Arrays.asList((String.valueOf(news.getHeader())),
                    (String.valueOf(news.getText())), (dateFormat.format(news.getPublicateDate()))), ',', '"');
        writer.flush();
        writer.close();
    }
}
