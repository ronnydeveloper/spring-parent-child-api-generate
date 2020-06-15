package sys.generator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GeneratorDto {

    public static String getRootElement(Document doc) {
        String root = doc.getDocumentElement().getNodeName();
        NodeList nList = doc.getElementsByTagName(root);

        String packages = "";
        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                packages = eElement.getAttribute("package");
            }
        }

        return packages;
    }

    public static String packageImport(String root) {
        String repositoryPackage = root + ".dto;";
        StringBuilder sb = new StringBuilder();
        sb.append("package " + repositoryPackage);
        sb.append("\n");
        sb.append("\n");
        sb.append("import com.fasterxml.jackson.annotation.JsonIgnoreProperties;");
        sb.append("\n");
        sb.append("import com.fasterxml.jackson.annotation.JsonInclude;");
        sb.append("\n");
        sb.append("import lombok.Builder;");
        sb.append("\n");
        sb.append("import lombok.Data;");
        sb.append("\n");
        return sb.toString();
    }

    public static File className(String packages, String fileName) throws IOException {
        String locationFile = packages.replaceAll("\\.","\\/") + "/" + fileName;
        String path = Paths.get("").toAbsolutePath().toString() + "\\src\\main\\java\\" + locationFile;
        System.out.println("Location File : " + path);
        File file = new File(path);
        if (file.createNewFile()){
            System.out.println("File is created!");
        }else{
            System.out.println("File already exists.");
        }
        return file;
    }

    public static String createDto(String root, Node nNode) {
        StringBuilder sbimp = new StringBuilder(packageImport(root));
        StringBuilder sg = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        StringBuilder ts = new StringBuilder();
        sb.append("\n");
        sb.append("@Data");
        sb.append("\n");
        sb.append("@Builder");
        sb.append("\n");
        sb.append("@JsonInclude(JsonInclude.Include.NON_NULL)");
        sb.append("\n");
        sb.append("@JsonIgnoreProperties({\"hibernateLazyInitializer\", \"handler\"})");
        sb.append("\n");

        boolean isDate = false, isBigDecimal = false, isCollection = false, isHashSet = false;

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

            Element eElement = (Element) nNode;

            NodeList nColumnList = nNode.getChildNodes();

            for (int i = 0; i < nColumnList.getLength(); i++) {
                Node nNodeCol = nColumnList.item(i);

                System.out.println("\nCurrent Element :" + nNodeCol.getNodeName());
                if (nNodeCol.getNodeType() == Node.ELEMENT_NODE) {

                    if (nNodeCol.getNodeName().equals("column")) {
                        Element eElementCol = (Element) nNodeCol;

                        if (eElementCol.getAttribute("primary").equalsIgnoreCase("true")) {
                            sb.append("public class " + eElement.getAttribute("name") + "DTO { ");
                            sb.append("\n");
                            sb.append("\n");
                        }

                        if (eElementCol.getAttribute("type").equalsIgnoreCase("BigDecimal")) {
                            isBigDecimal = true;
                        }
                        if (eElementCol.getAttribute("type").equalsIgnoreCase("Date")) {
                            isDate = true;
                        }
                        if (eElementCol.getAttribute("type").equalsIgnoreCase("class")) {

                            sb.append("    private " + eElementCol.getAttribute("name") + " " + eElementCol.getAttribute("alias") + ";");
                            sb.append("\n");

                            sbimp.append("import org.api.spring.generate.entity." + eElementCol.getAttribute("name") + ";");
                            sbimp.append("\n");
                            sbimp.append("\n");

                        } else if (eElementCol.getAttribute("type").equalsIgnoreCase("collection")) {
                            isCollection = true;

                            sb.append("    private List<" + eElementCol.getAttribute("name") + "> " + eElementCol.getAttribute("alias") + ";");
                            sb.append("\n");

                            sbimp.append("import org.api.spring.generate.entity." + eElementCol.getAttribute("name") + ";");
                            sbimp.append("\n");
                            sbimp.append("\n");

                        } else if (eElementCol.getAttribute("type").equalsIgnoreCase("HashSet")) {
                            isHashSet = true;

                            sb.append("    private Set<" + eElementCol.getAttribute("name") + "> " + eElementCol.getAttribute("alias") + ";");
                            sb.append("\n");

                            sbimp.append("import org.api.spring.generate.entity." + eElementCol.getAttribute("name") + ";");
                            sbimp.append("\n");
                            sbimp.append("\n");

                        } else {

                            sb.append("    private " + eElementCol.getAttribute("type") + " " + eElementCol.getAttribute("name") + ";");
                            sb.append("\n");

                        }
                        sb.append("\n");
                    }
                }
            }

            if(isCollection) {
                sbimp.append("import java.util.List;");
                sbimp.append("\n");
                sbimp.append("\n");
            }
            if(isHashSet) {
                sbimp.append("import java.util.Set;");
                sbimp.append("\n");
                sbimp.append("\n");
            }
            if (isBigDecimal) {
                sbimp.append("import java.math.BigDecimal;");
                sbimp.append("\n");
            }
            if (isDate) {
                sbimp.append("import java.util.Date;");
                sbimp.append("\n");
            }
        }

        ts.append("                 '}';").append("\n");
        ts.append("    }").append("\n");
        sb.append(sg.toString());
        sb.append("\n");
        sb.append("} ");
        return sbimp.toString() + sb.toString();
    }

    public static void createJavaFile(String data, File file) throws IOException {
        FileOutputStream out = new FileOutputStream(file);
        out.write(data.getBytes());
        out.close();
    }

    public static void generateDto(Document doc) throws IOException {
        String entityPackage = getRootElement(doc) + ".dto";
        List<Node> childNodes = getChildNodes(doc.getElementsByTagName("service").item(0), "entity");
        for (Node item : childNodes) {
            Element el = (Element) item;
            String data = createDto(getRootElement(doc), item);
            createJavaFile(data, className(entityPackage, el.getAttribute("name") + "DTO.java"));
        }
    }

    public static List<Node> getChildNodes(Node node, String tag) {
        NodeList nodes = node.getChildNodes();
        System.out.println("Node length : " + nodes.getLength());
        List<Node> childNodes = new ArrayList<Node>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            System.out.println("Item node name " + item.getNodeName());
            if (item.getNodeName().equals(tag))
                childNodes.add(item);
        }
        return childNodes;
    }

}
