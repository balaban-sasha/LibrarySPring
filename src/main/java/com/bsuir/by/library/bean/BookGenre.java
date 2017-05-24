package com.bsuir.by.library.bean;

import com.bsuir.by.library.controller.data.GenreDataController;
import com.bsuir.by.library.controller.exception.DataControllerException;
import com.bsuir.by.library.dao.implementation.genre.implementation.GenreDao;

public class BookGenre {
    private int id;
    private int bookId;
    private int genreId;
    private String genreName = "";

    public void setId(int id) {
        this.id = id;

    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setGenreId(int genreId) throws DataControllerException {
        this.genreId = genreId;
        GenreDataController genreDataController = new GenreDataController();
        genreName=genreDataController.getNameById(this.genreId);
    }

    public int getId() {
        return this.id;
    }
public String getBookGenreName(){return this.genreName;}
    public int getBookId() {
        return this.bookId;
    }

    public int getGenreId() {
        return this.genreId;
    }
}
