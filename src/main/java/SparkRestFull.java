import java.io.IOException;
import java.net.SocketException;

import static spark.Spark.*;


public class SparkRestFull {

    public static void main(String[] args) {

        final ISiteService service = new SiteService();

        get("/sites", (req,res) -> {
            try {
                res.type("application/json");
                return service.getSites();
            } catch (SocketException ex){
                res.status(503);
                res.type("application/json");
                halt(res.status(), "{" +
                        "\"message\": \"Server is Down\"," +
                        "}");
                return null;
            }
        });

        get("/sites/:id/categories", (req,res) -> {
            try {
                String json = service.getCategories(req.params(":id"));
                res.type("application/json");
                return json;
            } catch (SocketException ex){
                res.status(503);
                res.type("application/json");
                halt(res.status(), "{" +
                        "\"message\": \"Server is Down\"," +
                        "}");
                return null;
            } catch (IOException e) {
                res.status(404);
                res.type("application/json");
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
