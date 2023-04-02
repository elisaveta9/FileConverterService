package XmlToJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.Director;
import model.Film;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonWriter {
    public static void writeFile(List<Director> directors, String outputName) throws IOException {
        JsonObject directorsJson = getDirectors(directors);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(directorsJson);

        File file = new File(outputName);
        file.createNewFile();
        FileWriter writer = new FileWriter(outputName, false);
        writer.write(json);
        writer.flush();
        writer.close();
    }

    private static JsonObject getDirectors(List<Director> directors){
        JsonArray directorsArray = new JsonArray();
        for (Director director : directors){
            JsonObject directorAttr = new JsonObject();
            directorAttr.addProperty("name", director.getName());
            directorAttr.add("films", getFilms(director.getFilms()));

            JsonObject directorJson = new JsonObject();
            directorJson.add("director", directorAttr);
            directorsArray.add(directorJson);
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("directors", directorsArray);
        return jsonObject;
    }

    private static JsonArray getFilms(List<Film> films){
        JsonArray filmsArray = new JsonArray();
        for (Film filmEl : films){
            JsonObject filmJson = new JsonObject();
            filmJson.addProperty("name", filmEl.getName());
            filmJson.addProperty("productionYear", filmEl.getProductionYear());
            filmJson.add("screenwriters", getScreenwriters(filmEl.getScreenwriters()));
            filmJson.add("producers", getProducers(filmEl.getProducers()));
            filmJson.add("genres", getGenres(filmEl.getGenres()));
            filmsArray.add(filmJson);
        }
        return filmsArray;
    }

    private static JsonArray getScreenwriters(List<String> screenwriters){
        JsonArray screenwritersArray = new JsonArray();
        for (String name : screenwriters) {
            JsonObject screenwriter = new JsonObject();
            screenwriter.addProperty("name", name);
            screenwritersArray.add(screenwriter);
        }
        return screenwritersArray;
    }

    private static JsonArray getProducers(List<String> producers){
        JsonArray producersArray = new JsonArray();
        for (String name : producers) {
            JsonObject producer = new JsonObject();
            producer.addProperty("name", name);
            producersArray.add(producer);
        }
        return producersArray;
    }

    private static JsonArray getGenres(List<String> genres){
        JsonArray genresArray = new JsonArray();
        for (String name : genres) {
            JsonObject genre = new JsonObject();
            genre.addProperty("genre", name);
            genresArray.add(genre);
        }
        return genresArray;
    }
}
