import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import java.util.*;

public class SiteService implements ISiteService {

    private final ResourceBundle mybundle = ResourceBundle.getBundle("MyAPIs");

    @Override
    public String getSites() throws UnknownHostException,IOException {

        Gson gson = new Gson();
        BufferedReader in = null;

        try {

            in = createUrl(mybundle.getString("APISITE"));

            Site[] sites = gson.fromJson(in,Site[].class);
            Arrays.sort(sites);

            return gson.toJson( sites );

        } catch (UnknownHostException ex) {

            System.out.println(ex.toString());
            throw ex;

        } catch (IOException e) {
            System.out.println(e.toString());
            return "";
        }

    }

    @Override
    public String getCategories(String id) throws SocketException,IOException {
        Gson gson = new Gson();
        BufferedReader in = null;

        try {
            in = createUrl(mybundle.getString("APISITE") + id + mybundle.getString("CATEGORIE"));

            Categorie[] categories = gson.fromJson(in, Categorie[].class);
            Arrays.sort(categories);

            return gson.toJson(categories);

        } catch (SocketException ex) {

            System.out.println(ex.toString());
            throw ex;

        }
    }





    private static BufferedReader createUrl(String strUrl) throws SocketException, IOException {



        URL url = new URL(strUrl);

        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

        if (urlConnection instanceof HttpURLConnection) {

            try {
                HttpURLConnection connection = (HttpURLConnection) urlConnection;
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                return in;
            } catch (SocketException ex){

                System.out.println(ex.toString());
                throw ex;

            }
        } else {
            return null;
        }

    }
}
