import JsonToXml.JsonToXml;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class JsonToXmlConverter {
    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
        if (args.length != 1)
            System.out.println("������������ ���������� ����������");

        String outputFile = args[0];
        JsonToXml.Convert(outputFile);
        System.out.printf("���� film.xml ������������ � %s", outputFile);
    }
}
