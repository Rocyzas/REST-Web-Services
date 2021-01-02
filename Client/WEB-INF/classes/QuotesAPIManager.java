
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.util.*;

public class QuotesAPIManager {

  static final String REST_URI = "http://localhost:9999/quote/";
  static final String RandomPath = "random";
  static final String TAG = "tag/";
  static final String AUTHOR = "author/";

  WebResource service;

  public QuotesAPIManager(){
      ClientConfig config = new DefaultClientConfig();
      Client client = Client.create(config);
      service = client.resource(REST_URI);
  }

  public String getRandom(){
      WebResource getOneRelated = service.path(RandomPath);
      String response = getOneRelated.get(String.class);
      return response;
  }

  public String getTag(String name, int n){
      WebResource getOneRelated = service.path(TAG + name + "/" + n);
      String response = getOneRelated.get(String.class);
      return response;
  }

  public String getAuthor(String name, int n){
      WebResource getOneRelated = service.path(AUTHOR + name + "/" + n);
      String response = getOneRelated.get(String.class);
      return response;
  }

}
