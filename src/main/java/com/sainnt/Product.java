package com.sainnt;

public class Product {
    private long id;
    private String title;
    private long cost;
    public Product(){
    }
    public Product( String title, long cost) {
        this.title = title;
        this.cost = cost;
    }


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getCost() {
        return cost;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }
}
