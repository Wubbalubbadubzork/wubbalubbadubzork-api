package wubbalubbadubzork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {

        port(8000);
        path("/api", () -> {
            before("/*",(request, response) -> {
                boolean authorized = true;
                if(!authorized) {
                    halt(400, "No name, huh?");
                }
            });
            get("/:name", (request, response) -> {
                response.status(200);
                response.type("application/json");
                return "{\"id\":1, \"name\":\""
                        + request.params(":name")
                        + "\"}";
            });
            notFound("<html><body> <h1> Not Found :/ </h1> </body></html>");
        });

        // Stopping the server.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter any string to stop the server...");
            br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }

    }
}
