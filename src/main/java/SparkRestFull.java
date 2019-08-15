
import com.google.gson.Gson;
import spark.Spark;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;


public class SparkRestFull {

    public static void main(String[] args) {

        final ISiteService service = new SiteService();

        Spark.port(8083);

        get("/sites", (req,res) -> {
            res.type("application/json");
            return service.getSites();
        });

        get("/sites/:id/categories", (req,res) -> {
            res.type("application/json");
            return service.getCategories(req.params(":id"));
        });

    }

}
