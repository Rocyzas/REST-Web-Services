package com.movies.backend;

import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.sun.jersey.spi.resource.Singleton;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.sound.midi.SysexMessage;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Categories {

    private Map<String,ArrayList<Integer>> categories;

    public Categories(){
        System.out.println("Reading categories.....");
        categories = new HashMap<>();
        readCategories();
        System.out.println("Categories read.");
    }

    public void readCategories(){

        CSVIterator iterator = null;
        try {
            iterator = new CSVIterator(new CSVReader(new FileReader("./resources/keywords.csv")));
            JSONParser parser = new JSONParser();
            for (CSVIterator it = iterator; it.hasNext(); ) {
                String[] nextLine = it.next();
                // Get id
                if(nextLine[0].equals("id")){
                    continue;
                }

                int id = Integer.parseInt(nextLine[0]);
                // Get json object for easier parsing
                String st = nextLine[1];
                String[] cats = st.split("'");
                ArrayList<String> temp = new ArrayList<>();
                for(int i=0; i+2<cats.length; i++){
                    if(cats[i].equals("name") && !cats[i+2].contains(" ")){
                        if(categories.containsKey(cats[i+2])){
                            categories.get(cats[i+2]).add(id);
                        }
                        else{
                            categories.put(cats[i+2], new ArrayList<>());
                            categories.get(cats[i+2]).add(id);
                        }
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getMoviesByCategory(String category){
        return categories.get(category);
    }
}
