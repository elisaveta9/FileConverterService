import JsonToXml.JsonToXml;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class JsonToXmlConverter {
    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
        if (args.length != 1)
            System.out.println("Incorrect number of arguments");

        String outputFile = args[0];
        JsonToXml.Convert(outputFile);
        System.out.printf("The file film.xml has been converted to a file %s", outputFile);
    }
}
