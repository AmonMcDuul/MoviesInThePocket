package com.amonmcduul.moviesinthepocket.Model;

/**
 * The type Data model.
 */
public class DataModel {

    /**
     * The Id.
     */
    int id;
    /**
     * The Title.
     */
    String title, /**
     * The Review.
     */
    review, /**
     * The Rating.
     */
    rating;

    /**
     * Instantiates a new Data model.
     *
     * @param id     the id
     * @param title  the title
     * @param review the review
     * @param rating the rating
     */
    public DataModel(int id, String title, String review, String rating) {
        this.id = id;
        this.title = title;
        this.review = review;
        this.rating = rating;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets review.
     *
     * @return the review
     */
    public String getReview() {
        return review;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public String getRating() {
        return rating;
    }

}