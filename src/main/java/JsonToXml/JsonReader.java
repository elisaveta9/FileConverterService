package JsonToXml;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import model.Film;

import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    public static List<Film> readFile() throws IOException {
        List<Film> films = new ArrayList<>();

        String content = getContent();
        JsonElement array = JsonParser.parseString(content);
        if (array.isJsonArray())
            films = getResult((JsonArray) array);

        return films;
    }

    private static String getContent() throws IOException {
        CharBuffer buffer = CharBuffer.allocate(10000) ;

        try (Reader reader = new FileReader("resources/input/directors.json"))
        {
            reader.read(buffer);
            var content = String.valueOf(buffer.array()).replace("\r", "").
                replace("\n", "").replace("  ", "");
            return content.substring(content.indexOf('['), content.lastIndexOf(']') + 1).
                replace("\"", "'").replace(": ", ":").
                replace("{ ", "{").replace(" }", "}");
        }
    }

    private static List<Film> getResult(JsonArray array){
        List<Film> result = new ArrayList<>();
        for (JsonElement element : array){
           element = element.getAsJsonObject().get("director");
           if (element.isJsonObject()) {
               String name = element.getAsJsonObject().get("name").getAsString();
               result.addAll(getFilms(name, (JsonArray) element.getAsJsonObject().get("films")));
           }
        }
        return result;
    }

    private static List<Film> getFilms(String director, JsonArray array){
        List<Film> films = new ArrayList<>();
        for (JsonElement element : array){
            String name = element.getAsJsonObject().get("name").getAsString();
            int productionYear = element.getAsJsonObject().get("productionYear").getAsInt();
            ArrayList<String> screenwriters = getScreenwriters(element.getAsJsonObject().get("screenwriters").
                    getAsJsonArray());
            ArrayList<String> producers = getProducers(element.getAsJsonObject().get("producers").getAsJsonArray());
            ArrayList<String> genres = getGenres(element.getAsJsonObject().get("genres").getAsJsonArray());
            films.add(new Film(name, productionYear, director, screenwriters, producers, genres));
        }
        return films;
    }

    private static ArrayList<String> getScreenwriters(JsonArray array){
        ArrayList<String> screenwriters = new ArrayList<>();
        for (JsonElement element : array)
            screenwriters.add(element.getAsJsonObject().get("name").getAsString());
        return screenwriters;
    }

    private static ArrayList<String> getProducers(JsonArray array){
        ArrayList<String> producers = new ArrayList<>();
        for (JsonElement element : array)
            producers.add(element.getAsJsonObject().get("name").getAsString());
        return producers;
    }

    private static ArrayList<String> getGenres(JsonArray array){
        ArrayList<String> genres = new ArrayList<>();
        for (JsonElement element : array)
            genres.add(element.getAsJsonObject().get("genre").getAsString());
        return genres;
    }
}
