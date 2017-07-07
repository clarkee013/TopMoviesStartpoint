package com.codeclan.topmovieslist;


import java.io.Serializable;

import java.io.Serializable;

// - 1st - make movie class Serializable so that it can be changed to a String.
public class Movie implements Serializable {

    private int ranking;
    private String title;
    private int year;

    public Movie(Integer ranking, String title, Integer year) {
        this.ranking = ranking;
        this.title = title;
        this.year = year;
    }

    public Integer getRanking() {
        return ranking;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

}
