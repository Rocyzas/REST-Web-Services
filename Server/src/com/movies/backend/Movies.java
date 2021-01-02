package com.movies.backend;

import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Movies {
    private List<Movie> movies = null;

    public Movies(){
        System.out.println("Reading movies....");
        readMetadata();
        System.out.println("Movies read.");
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void readMetadata(){
        String filepath = "./resources/movies_metadata.csv";


        try {
            movies = new CsvToBeanBuilder(new FileReader(filepath))
                    .withType(Movie.class)
                    .withSkipLines(1)
                    .build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Movie getById(int id){
        for (Movie mv : movies){
            if(mv.getId() == id){
                return mv;
            }
        }
        return null;
    }
}
