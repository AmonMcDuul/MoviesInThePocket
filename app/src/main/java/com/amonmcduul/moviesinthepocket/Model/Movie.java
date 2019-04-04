package com.amonmcduul.moviesinthepocket.Model;

import java.io.Serializable;

/**
 * The type Movie.
 */
public class Movie implements Serializable {
    private static final long id = 1L;
    private String title, year, runtime, imdbId, poster, writer, actors, plot, rating, movieType;

    /**
     * Instantiates a new Movie.
     */
    public Movie() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public static long getId() {
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
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Gets imdb id.
     *
     * @return the imdb id
     */
    public String getImdbId() {
        return imdbId;
    }

    /**
     * Sets imdb id.
     *
     * @param imdbId the imdb id
     */
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    /**
     * Gets poster.
     *
     * @return the poster
     */
    public String getPoster() {
        return poster;
    }

    /**
     * Sets poster.
     *
     * @param poster the poster
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }

    /**
     * Gets movie type.
     *
     * @return the movie type
     */
    public String getMovieType() {
        return movieType;
    }

}
