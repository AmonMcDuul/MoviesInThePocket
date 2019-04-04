package com.amonmcduul.moviesinthepocket.Model;

public class DataModel {

    int id;
    String title, review, rating;

    public DataModel(int id, String title, String review, String rating) {
        this.id = id;
        this.title = title;
        this.review = review;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReview() {
        return review;
    }

    public String getRating() {
        return rating;
    }

}