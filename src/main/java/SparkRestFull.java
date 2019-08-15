import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import static spark.Spark.*;


public class SparkRestFull {

    public static void main(String[] args) {

        final ISiteService service = new SiteService();



        get("/sites", (req,res) -> {

            res.type("application/json");

            try {

                return service.getSites();

            } catch (UnknownHostException ex){

                res.status(503);
                halt(res.status(), "{" +
                        "\"message\": \"Server is Down\"" +
                        "}");
                return null;

            }
        });

        get("/sites/:id/categories", (req,res) -> {

            res.type("application/json");

            try {

                String json = service.getCategories(req.params(":id"));
                return json;

            } catch (SocketException ex){

                res.status(503);
                halt(res.status(), "{" +
                        "\"message\": \"Server is Down\"," +
                        "}");
                return null;

            } catch (IOException e) {

                System.out.println(e.toString());
                res.status(404);
                halt(res.status(), "{" +
                        "\"message\": \"Categories not found for this site\"," +
                        "\"error\": \"not_found\"," +
                        "\"status\": " + res.status() +
                        "}");
                return null;

            }

        });

        get("/*",(req, res) ->{

            res.status(404);
            res.type("application/json");
            halt(res.status(),"{" +
                    "\"message\": \"URL Incorrecta\"," +
                    "\"error\": \"resource not found\"" +
                    "}");
            return null;

        });
    }

}
