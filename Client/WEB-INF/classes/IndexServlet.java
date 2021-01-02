import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


// Main Servlet File
public class IndexServlet extends HttpServlet {

  // Creating Objects of three different services
  private QuotesAPIManager  quotesManager = new QuotesAPIManager();
  private MoviesAPIManager moviesManager = new MoviesAPIManager();
  private ImdbAPIManager imdbManager = new ImdbAPIManager();

  // Working on get request
  public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws IOException, ServletException
  {
    ArrayList<JSONObject> quotes = new ArrayList<>();
    JSONParser parser = new JSONParser();

    // Rretrieving quotes
    if(request.getParameter("btn") != null){
      String keyword = request.getParameter("keyword");
      int n = 1;
      if(!request.getParameter("number").equals("")){
        n = Integer.parseInt(request.getParameter("number"));
      }


      // Checking the type of request
      String stringToParse = "";
      if( request.getParameter("btn").equals("search")){
        stringToParse = quotesManager.getTag(keyword, n);
        try {
            JSONArray jarray = (JSONArray) parser.parse(stringToParse);
            for(int i = 0; i< jarray.size(); i++){
                JSONObject json = (JSONObject) parser.parse(jarray.get(i).toString());
                quotes.add(json);
            }
            // If the was no tag associated with the input, then it is author
            if(quotes.isEmpty()){
              stringToParse = "";
              // This time getting the author
              stringToParse = quotesManager.getAuthor(keyword, n);

              jarray = (JSONArray) parser.parse(stringToParse);
              for(int i = 0; i< jarray.size(); i++){
                  JSONObject json1 = (JSONObject) parser.parse(jarray.get(i).toString());
                  quotes.add(json1);
              }
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }

      }
      else{
        stringToParse = quotesManager.getRandom();
        try {
            JSONObject json = (JSONObject) parser.parse(stringToParse);
            quotes.add(json);
        }catch (ParseException e) {
            e.printStackTrace();
        }
      }
    }

    // Retrieving Movies related to most common quote category
    ArrayList<JSONObject> movies = new ArrayList<>();

    if(quotes.size() > 0){
        HashMap<String,Integer> categories = new HashMap<>();
        for( JSONObject q : quotes){
          String cat = q.get("Category").toString();
          if(!categories.containsKey(cat)){
            categories.put(cat, 0);
          }
          categories.put(cat, categories.get(cat)+1);
        }

// Selecting the most approporiate category to show for the user
        int max = 0;
        String maxCat = "";
        for (Map.Entry<String, Integer> entry : categories.entrySet()) {
          String key = entry.getKey();
          int value = entry.getValue();
          if(value > max){
            max = value;
            maxCat = key;
          }
        }

        // Showing manually the optimal number of movies
        String stringToParse = moviesManager.getRelated(3, maxCat);
            try {
                JSONArray jarray = (JSONArray) parser.parse(stringToParse);
                for(int i = 0; i< jarray.size(); i++){
                    JSONObject json = (JSONObject) parser.parse(jarray.get(i).toString());
                    movies.add(json);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
      }

    ArrayList<String> posters = new ArrayList<>();
    for(JSONObject movie : movies){
      String poster = imdbManager.getPosterLink(movie.get("IMDB ID").toString());
      posters.add(poster);
    }

    // For UI
    request.setAttribute("quotes", quotes);
    request.setAttribute("movies", movies);
    request.setAttribute("posters", posters);
    request.getRequestDispatcher("jsp/index.jsp").forward(request,response);
  }


}
