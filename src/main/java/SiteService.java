import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SiteService implements ISiteService {


    @Override
    public String getSites() {
        Gson gson = new Gson();
        BufferedReader in = null;
        try {
            in = createUrl("https://api.mercadolibre.com/sites");

            Site[] sites = gson.fromJson(in,Site[].class);
            Sort(sites);

            return gson.toJson( sites );

        } catch (MalformedURLException exception){
            System.out.println(exception.getMessage());
            return null;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public String getCategories(String id) {
        Gson gson = new Gson();
        BufferedReader in = null;

        try {
            in = createUrl("https://api.mercadolibre.com/sites/"+ id + "/categories");

            Categorie[] categories = gson.fromJson(in,Categorie[].class);
            Sort(categories);

            return gson.toJson( categories );

        } catch (MalformedURLException exception){
            System.out.println(exception.getMessage());
            return null;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }





    private static BufferedReader createUrl(String strUrl) throws MalformedURLException, IOException {

        URL url = new URL(strUrl);

        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

        if (urlConnection instanceof HttpURLConnection) {

            HttpURLConnection connection = (HttpURLConnection) urlConnection;
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return in;
        } else {

            throw new MalformedURLException();
        }

    }





    private static <T extends Comparable> void Sort(T[] array){

        boolean sorted = false;
        T temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i].compareTo( array[i+1])>0) {
                    temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    sorted = false;
                }
            }
        }

    }
}
