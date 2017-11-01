package wubbalubbadubzork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import wubbalubbadubzork.classes.Partida;
import com.google.gson.*;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {

        Partida partida = new Partida("123");
        Gson gson = new Gson();
        String json = gson.toJson(partida);
        System.out.println(json);

        Partida target = gson.fromJson("{'codigo':'124'}", Partida.class);
        System.out.println(gson.toJson(target));
        target = gson.fromJson("{'codigo':'234'}", Partida.class);
        System.out.println(gson.toJson(target));
        target = gson.fromJson("{'codigo':'543'}", Partida.class);
        System.out.println(gson.toJson(target));

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
