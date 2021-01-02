package com.movies.api;/*
 * Created on 10 Sep 2013
 * Revised 22 Oct 2017
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

//package com.tutorial.jaxrs.calc;
import com.movies.backend.Categories;
import com.movies.backend.Movie;
import com.movies.backend.Movies;
import com.sun.jersey.spi.resource.Singleton;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;

@Path("/movies")
@Singleton
public class MoviesREST {

    Categories cats = new Categories();
    Movies movies = new Movies();

    @GET
    @Path("/related/{keyword}/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    public String related(@PathParam("keyword") String keyword, @PathParam("n") int n) {
        System.out.println("GET Movies Request: " + keyword + " " + n);
        ArrayList<String> relatedMoviesJson = new ArrayList<>();
        ArrayList<Integer> relatedMovies = cats.getMoviesByCategory(keyword);

        int i = 1;
        Collections.shuffle(relatedMovies);
        for(int id : relatedMovies){

            JSONObject writer = new JSONObject();
            Movie movie = movies.getById(id);

            writer.put("Title", movie.getTitle());
            writer.put("Overview", movie.getOverview());
            writer.put("IMDB ID", movie.getImdbId());

            relatedMoviesJson.add(writer.toString() + "\n");

            if(i >= n){
                break;
            }
            i++;
        }

        int id = cats.getMoviesByCategory(keyword).get(0);

        return relatedMoviesJson.toString();
    }

    @GET
    @Path("/related/{keyword}")
    @Produces(MediaType.APPLICATION_JSON)
    public String related(@PathParam("keyword") String keyword) {
        System.out.println("GET Movies Request: " + keyword);
        ArrayList<Integer> relatedMovies = cats.getMoviesByCategory(keyword);

        Collections.shuffle(relatedMovies);

        int id = cats.getMoviesByCategory(keyword).get(0);

        Movie movie = movies.getById(id);

        JSONObject writer = new JSONObject();
        writer.put("Title", movie.getTitle());
        writer.put("Overview", movie.getOverview());
        writer.put("IMDB ID", movie.getImdbId());

        return writer.toString();
    }

}