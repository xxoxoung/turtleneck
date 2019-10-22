package com.example.turtleneck;

public class ListVO {
    private String Number;
    private String Date;
    private String User;
    private String Title;
    private String Content;

    //getter
    public String getNumber() { return Number; }
    public String getDate() { return Date; }
    public String getUser() { return User; }
    public String getTitle() { return Title; }
    public String getContent() { return Content;}

    //setter
    public void setNumber(String number) { Number=number;}
    public void setDate(String date) { Date=date;}
    public void setUser(String user) { User=user;}
    public void setTitle(String title) { Title=title;}
    public void setContent(String content) { Content=content;}
}
