package com.otus.dobj;

public class Category {
    private String name;
    private String header;

    public Category(String name, String header) {
        this.name = name;
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }
}
