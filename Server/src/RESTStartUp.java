

import java.io.IOException;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class RESTStartUp {

    static final String BASE_URI = "http://localhost:9999/";

    public static void main(String[] args) {
        try {


            HttpServer serv = HttpServerFactory.create("http://localhost:9999/");
            serv.start();
            System.out.println("Press Enter to stop the server. ");
            System.in.read();
            serv.stop(0);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}