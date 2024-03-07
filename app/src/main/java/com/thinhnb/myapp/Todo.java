package com.thinhnb.myapp;

import java.util.HashMap;
import java.util.Objects;

public class Todo {
    private String id , title,content;


    public Todo() {
    }

    public Todo(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    // ham convert du lieu sang dang hashmap : de luu vaof firebase
    public HashMap<String, Object> convertToHashMap(){
        HashMap<String,Object> h = new HashMap<>();
        h.put("id",id);
        h.put("title",title);
        h.put("content",content);
        return h;
    }
}

