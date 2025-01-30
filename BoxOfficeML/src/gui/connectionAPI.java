package gui;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONObject;

import dom.Actor;

import org.json.JSONArray;

public class connectionAPI {

    private static final String API_KEY = "5c56fd3ca45b0c5cb03bffe174e07739";
    private static final String BASE_URL = "https://api.themoviedb.org/3";


    public static ArrayList<Actor> obtainActor(String nombre, ArrayList<Actor> list) throws Exception {
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
            int id = actors.getJSONObject(i).getInt("id");
            
            String knownDepartment = null;
            
            try {
            	 knownDepartment = actors.getJSONObject(i).getString("known_for_department");
            }catch (Exception e) {
            	 knownDepartment = "No data";
            }
            
            String name = actors.getJSONObject(i).getString("name");
            Double popularity = actors.getJSONObject(i).getDouble("popularity");
            String picture = obtainPhoto(id);
            
            JSONArray filmList = actors.getJSONObject(i).getJSONArray("known_for");
            ArrayList<String> filmList2 = new ArrayList<>();

            for (int j = 0; j < filmList.length(); j++) {
            	 String originalTitle = null;
            	 
            	 try {
                     originalTitle = filmList.getJSONObject(j).getString("title");
                 } catch (Exception e) {
                     try {
                         originalTitle = filmList.getJSONObject(j).getString("name");
                     } catch (Exception ex) {
                     }
                 }
            	 filmList2.add(originalTitle);
            }

            Actor actor = new Actor(id, knownDepartment, name, popularity, picture, filmList2);
            list.add(actor);
        }
        
        return list;
        
    }

    public static String obtainPhoto(int id) throws Exception {
    	String image = "No";
    	
    	String urlString = BASE_URL + "/person/"+id+"/images?api_key=" + API_KEY;
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
        JSONArray profiles = object.getJSONArray("profiles");
    	
        if(!profiles.isEmpty()) {
        	image = "https://image.tmdb.org/t/p/original/" + profiles.getJSONObject(0).getString("file_path");
        }
        
    	return image;
    }
    
}
