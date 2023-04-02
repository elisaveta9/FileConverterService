package JsonToXml;

import model.Film;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XmlWriter {
    public static void writeFile(List<Film> films, String outputName) throws ParserConfigurationException, FileNotFoundException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = factory.newDocumentBuilder().newDocument();

        Element root = doc.createElement("FilmCatalogue");
        List<Element> elements = getFilms(films, doc);
        for (Element film : elements)
            root.appendChild(film);
        doc.appendChild(root);

        FileOutputStream output =
                new FileOutputStream(outputName);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }

    private static List<Element> getFilms(List<Film> films, Document doc){
        List<Element> result = new ArrayList<>();
        for (int i = 0; i < films.size(); ++i){
            Element childNode = doc.createElement("Film");
            childNode.setAttribute("id", String.valueOf((i + 1)));
            List<Element> attr = getFilmAttr(films.get(i), doc);
            for (Element element : attr)
                childNode.appendChild(element);
            result.add(childNode);
        }
        return result;
    }

    private static List<Element> getFilmAttr(Film film, Document doc){
        Element name = doc.createElement("Name");
        Element productionYear = doc.createElement("ProductionYear");
        Element director = doc.createElement("Director");
        Element screenwriters = doc.createElement("Screenwriters");
        Element producers = doc.createElement("Producers");
        Element genres = doc.createElement("Genres");

        name.setTextContent(film.getName());
        productionYear.setTextContent(String.valueOf(film.getProductionYear()));
        director.setTextContent(film.getDirector());

        for (String screenwriterName : film.getScreenwriters()){
            Element childEl = doc.createElement("Screenwriter");
            childEl.setAttribute("name", screenwriterName);
            screenwriters.appendChild(childEl);
        }

        for (String producerName : film.getProducers()){
            Element childEl = doc.createElement("Producer");
            childEl.setAttribute("name", producerName);
            producers.appendChild(childEl);
        }

        for (String genreName : film.getScreenwriters()){
            Element childEl = doc.createElement("Genre");
            childEl.setAttribute("name", genreName);
            genres.appendChild(childEl);
        }

        return new ArrayList<>(
                Arrays.asList(name, productionYear, director, screenwriters, producers, genres));
    }
}