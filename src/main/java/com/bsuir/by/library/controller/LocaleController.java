package com.bsuir.by.library.controller;

import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Саша on 28.03.2017.
 */
public class LocaleController {

    private Locale locale;
    private ResourceBundle resourceBundle;
    private String language;
    public void changeLocale(String language,String country)
    {
        this.locale = new Locale(language,country);
        this.resourceBundle = ResourceBundle.getBundle("resource.content",locale);
        this.language=language;
        System.out.println("locale changed");
    }
    public String getLanguage()
    {
        return this.language;
    }
    public void loadData(ModelAndView model)
    {
        model.addObject("table_language",locale.getLanguage()+"_"+locale.getCountry());
        model.addObject("author", this.resourceBundle.getString("author"));
        model.addObject("id", this.resourceBundle.getString("id"));
        model.addObject("author_name", this.resourceBundle.getString("author_name"));
        model.addObject("author_female", this.resourceBundle.getString("author_female"));
        model.addObject("author_patronymic", this.resourceBundle.getString("author_patronymic"));
        model.addObject("author_biography", this.resourceBundle.getString("author_biography"));
        model.addObject("book", this.resourceBundle.getString("book"));
        model.addObject("book_name", this.resourceBundle.getString("book_name"));
        model.addObject("book_date", this.resourceBundle.getString("book_date"));
        model.addObject("description", this.resourceBundle.getString("description"));
        model.addObject("book_text_link", this.resourceBundle.getString("book_text_link"));
        model.addObject("author_id", this.resourceBundle.getString("author_id"));
        model.addObject("user", this.resourceBundle.getString("user"));
        model.addObject("login", this.resourceBundle.getString("login"));
        model.addObject("password", this.resourceBundle.getString("password"));
        model.addObject("name", this.resourceBundle.getString("name"));
        model.addObject("female", this.resourceBundle.getString("female"));
        model.addObject("gender", this.resourceBundle.getString("gender"));
        model.addObject("age", this.resourceBundle.getString("age"));
        model.addObject("user_status", this.resourceBundle.getString("user_status"));
        model.addObject("section", this.resourceBundle.getString("section"));
        model.addObject("header_of_smth", this.resourceBundle.getString("header_of_smth"));
        model.addObject("number", this.resourceBundle.getString("number"));
        model.addObject("news_page", this.resourceBundle.getString("news_page"));
        model.addObject("news_status", this.resourceBundle.getString("news_status"));
        model.addObject("news_id", this.resourceBundle.getString("news_id"));
        model.addObject("section_id", this.resourceBundle.getString("section_id"));
        model.addObject("news_comment", this.resourceBundle.getString("news_comment"));
        model.addObject("text", this.resourceBundle.getString("text"));
        model.addObject("publicate_date", this.resourceBundle.getString("publicate_date"));
        model.addObject("user_id", this.resourceBundle.getString("user_id"));
        model.addObject("news", this.resourceBundle.getString("news"));
        model.addObject("message", this.resourceBundle.getString("message"));
        model.addObject("recipient_status", this.resourceBundle.getString("recipient_status"));
        model.addObject("sender_status", this.resourceBundle.getString("sender_status"));
        model.addObject("sender_id", this.resourceBundle.getString("sender_id"));
        model.addObject("recipient_id", this.resourceBundle.getString("recipient_id"));
        model.addObject("genre", this.resourceBundle.getString("genre"));
        model.addObject("comment", this.resourceBundle.getString("comment"));
        model.addObject("book_id", this.resourceBundle.getString("book_id"));
        model.addObject("chat", this.resourceBundle.getString("chat"));
        model.addObject("book_genre", this.resourceBundle.getString("book_genre"));
        model.addObject("genre_id", this.resourceBundle.getString("genre_id"));
        model.addObject("book_catalog", this.resourceBundle.getString("book_catalog"));
        model.addObject("book_status", this.resourceBundle.getString("book_status"));
        model.addObject("author_catalog", this.resourceBundle.getString("author_catalog"));
        model.addObject("author_status", this.resourceBundle.getString("author_status"));
        model.addObject("book_rating", this.resourceBundle.getString("book_rating"));
        model.addObject("raiting_count", this.resourceBundle.getString("raiting_count"));
    }
}
