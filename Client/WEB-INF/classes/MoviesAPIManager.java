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

public class MoviesAPIManager {
    static final String REST_URI = "http://localhost:9999/movies/";
    static final String RELATED_PATH = "related/";

    WebResource service;

    public MoviesAPIManager(){
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        service = client.resource(REST_URI);
    }

    public String getRelated(int n, String keyword){
        WebResource getOneRelated = service.path(RELATED_PATH).path(keyword + "/" + n);
        String response = getOneRelated.get(String.class);
        return response;
    }
}
