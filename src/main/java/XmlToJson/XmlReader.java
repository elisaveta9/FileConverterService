package XmlToJson;

import model.Film;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlReader {

    private static final List<Film> films = new ArrayList<>();
    public static List<Film> readFile() throws IOException, SAXException, ParserConfigurationException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XmlHandler handler = new XmlHandler();
        parser.parse(new File("resources/input/films.xml"), handler);

        return  films;
    }

    private static class XmlHandler extends DefaultHandler {
        private String thisElement;
        private Film thisFilm;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            thisElement = qName;

            switch (thisElement) {
                case "Screenwriter" -> thisFilm.addScreenwriter(attributes.getValue("name"));
                case "Producer" -> thisFilm.addProducer(attributes.getValue("name"));
                case "Genre" -> thisFilm.addGenre(attributes.getValue("name"));
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            if (!thisElement.isEmpty())
                if (thisElement.equals("Film")) {
                    thisFilm = new Film();
                }
                else if (thisElement.equals("Name")) {
                    String information = new String(ch, start, length);
                    information = information.replace("\n", "").trim();
                    thisFilm.setName(information);
                }
                else if (thisElement.equals("ProductionYear")) {
                    String information = new String(ch, start, length);
                    information = information.replace("\n", "").trim();
                    thisFilm.setProductionYear(Integer.parseInt(information));
                }
                else if (thisElement.equals("Director")) {
                    String information = new String(ch, start, length);
                    information = information.replace("\n", "").trim();
                    thisFilm.setDirector(information);
                }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals("Film")){
                films.add(thisFilm);
                thisFilm = null;
            }
            thisElement = "";
        }
    }
}