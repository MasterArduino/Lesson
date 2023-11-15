package guru;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLParser {
    public static void main(String[] args) {
        try {
            // Загрузка XML-файла
            File xmlFile = new File("resources/file.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Получение списка pointsList
            NodeList pointsListNodes = doc.getElementsByTagName("pointsList");
            for (int i = 0; i < pointsListNodes.getLength(); i++) {
                Node pointsListNode = pointsListNodes.item(i);
                if (pointsListNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element pointsListElement = (Element) pointsListNode;

                    // Получение списка point
                    NodeList pointNodes = pointsListElement.getElementsByTagName("point");
                    for (int j = 0; j < pointNodes.getLength(); j++) {
                        Node pointNode = pointNodes.item(j);
                        if (pointNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element pointElement = (Element) pointNode;

                            // Получение координат X и Y
                            String x = pointElement.getElementsByTagName("x").item(0).getTextContent();
                            String y = pointElement.getElementsByTagName("y").item(0).getTextContent();

                            // Вывод координат
                            System.out.println("X " + x + " px, Y " + y + " px");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
