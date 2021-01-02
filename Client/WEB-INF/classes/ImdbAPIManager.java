/*
 * Created on 10 Sep 2013
 * Revised 22 Oct 2017
 *
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// External IMDB service API
public class ImdbAPIManager {
    static final String REST_URI = "https://imdb-api.com/API/";
    static final String POSTERS_PATH = "Posters/";
    // Key for  API
    static final String KEY = "k_7qwiq8t8/";

    WebResource service;

    public ImdbAPIManager(){
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        service = client.resource(REST_URI);
    }

    public String getPosterLink(String id){
        WebResource getPosters = service.path(POSTERS_PATH + KEY).path(id);
        String stringToParse = getPosters.get(String.class);
        JSONParser parser = new JSONParser();

        try {
            JSONObject json = (JSONObject) parser.parse(stringToParse);
            JSONArray jarray = (JSONArray)json.get("posters");
            // If there is no image, just return template image
            if(jarray.size()==0){
              return "https://icons-for-free.com/iconfiles/png/512/part+2+imdb-1320568365691068262.png";
            }
            JSONObject poster =  (JSONObject) jarray.get(0);
            String link = poster.get("link").toString();
            // Return the link of the image
            return link;
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return "https://icons-for-free.com/iconfiles/png/512/part+2+imdb-1320568365691068262.png";
    }
}
