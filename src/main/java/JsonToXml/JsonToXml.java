package JsonToXml;

import model.Film;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class JsonToXml {
    public static void Convert(String outputFileName) throws IOException, ParserConfigurationException, TransformerException {
        if (!Pattern.matches("^([a-zA-Z\\d]+:\\\\)?([^\\\\/:*?\"<>|]+/)*[^\\\\/:*?\"<>|]+\\.xml$",
                outputFileName))
            throw new IllegalArgumentException("Неверное расширение файла");

        List<Film> films = JsonReader.readFile();
        XmlWriter.writeFile(films, outputFileName);
    }
}
