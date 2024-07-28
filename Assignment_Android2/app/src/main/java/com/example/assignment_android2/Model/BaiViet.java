package com.example.assignment_android2.Model;

import java.io.Serializable;

public class BaiViet implements Serializable {
    private Integer Id;
    private String tilte;
    private String Content;
    private String Date;

    public BaiViet(Integer id, String tilte, String content, String date) {
        Id = id;
        this.tilte = tilte;
        Content = content;
        Date = date;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
