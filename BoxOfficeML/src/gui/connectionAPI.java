package gui;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;

public class connectionAPI {

    private static final String API_KEY = "5c56fd3ca45b0c5cb03bffe174e07739";
    private static final String BASE_URL = "https://api.themoviedb.org/3";


    public static ArrayList<String> obtainActor(String nombre, ArrayList<String> list) throws Exception {
        String urlString = BASE_URL + "/search/person?include_adult=false&language=en-US&page=1&api_key=" + API_KEY + "&query=" + nombre;
        HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject object =  new JSONObject(response.toString());
        JSONArray actors = object.getJSONArray("results");
        
        for (int i = 0; i < actors.length(); i++) {
            list.add(actors.getJSONObject(i).getString("name"));
        }
        
        return list;
        
    }

}
