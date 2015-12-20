package com.flipingtest.model;

/**
 * Created by lalo.zhang on 2015/12/20.
 */
public class CardEntity {

    public String title;
    public String name;
    public String position;
    public String company;
    public String locCity;
    public String des;
    public String message;
    public String icon;

    public CardEntity(String title, String name, String position, String company, String locCity, String des, String message, String icon) {
        this.title = title;
        this.name = name;
        this.position = position;
        this.company = company;
        this.locCity = locCity;
        this.des = des;
        this.message = message;
        this.icon = icon;
    }
}
