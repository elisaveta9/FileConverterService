import XmlToJson.XmlToJson;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlToJsonConverter {
    public static void main(String[] args) throws  IOException, ParserConfigurationException, SAXException {
        if (args.length != 1)
            System.out.println("������������ ���������� ����������");

        String outputFile = args[0];
        XmlToJson.Convert(outputFile);
        System.out.printf("���� film.xml ������������ � %s", outputFile);
    }
}
