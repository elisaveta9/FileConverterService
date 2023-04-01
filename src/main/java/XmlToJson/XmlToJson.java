package XmlToJson;

import model.Director;
import model.Film;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class XmlToJson{

    public static void Convert(String outputFileName) throws IOException, ParserConfigurationException, SAXException {
        if (!Pattern.matches("^([a-zA-Z\\d]+:\\\\)?([^\\\\/:*?\"<>|]+/)*[^\\\\/:*?\"<>|]+\\.json$", outputFileName)) {
            throw new IllegalArgumentException("Неверное расширение файла");
        }

        List<Director> directors = GetDirectors();
        JsonWriter.writeFile(directors, outputFileName);
    }

    private static List<Director> GetDirectors() throws IOException, ParserConfigurationException, SAXException {
        List<Director> directors = new ArrayList<>();
        List<Film> films = XmlReader.readFile();

        for (Film film : films) {
            int index = IndexOfDirector(film.getDirector(), directors);
            if (index != -1)
                directors.get(index).addFilm(film);
            else
                directors.add(new Director(film.getDirector(), film));
        }

        return directors;
    }

    private static int IndexOfDirector(String name, List<Director> directors){
        for (int i = 0; i < directors.size(); ++i)
            if (directors.get(i).getName().equals(name))
                return i;
        return -1;
    }
}
