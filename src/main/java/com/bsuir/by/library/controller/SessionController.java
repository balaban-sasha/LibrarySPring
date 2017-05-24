package com.bsuir.by.library.controller;

import java.util.List;

/**
 * Created by Саша on 02.04.2017.
 */
public class SessionController {
    private String context;
    private int userId = 0;
    private String request = "";
    public void setUserId(int id)
    {
        this.userId=id;
    }
    public void setRequest(String request)
    {
        this.request=request;
    }
    public int getUserId()
    {
     return this.userId;
    }
    public String getRequest(){return this.request;}
    public boolean ifExistUserId()
    {
        if(this.userId!=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }
}
