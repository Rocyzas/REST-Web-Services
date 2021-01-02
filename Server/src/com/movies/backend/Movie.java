package com.movies.backend;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.util.ArrayList;

public class Movie {
    @CsvBindByPosition(position = 5)
    private int id;
    @CsvBindByPosition(position = 6)
    private String imdbId;
    @CsvBindByPosition(position = 20)
    private String title;
    @CsvBindByPosition(position = 9)
    private String overview;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
