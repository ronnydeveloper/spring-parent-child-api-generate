package sys.generator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class GeneratorRepository {

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

    public static String packageImport(String root, String entity) {
        String repositoryPackage = root + ".repository;";
        String entityImport = root + ".entity." + entity + ";";
        StringBuilder sb = new StringBuilder();
        sb.append("package " + repositoryPackage);
        sb.append("\n");
        sb.append("\n");
        sb.append("import " + entityImport);
        sb.append("\n");
        sb.append("import org.springframework.data.jpa.repository.JpaRepository;");
        sb.append("\n");
        sb.append("import org.springframework.data.jpa.repository.Query;");
        sb.append("\n");
        sb.append("import org.springframework.stereotype.Repository;");
        sb.append("\n");
        return sb.toString();
    }

    public static File className(String packages, String fileName) throws IOException {
        String locationFile = packages.replaceAll("\\.","\\/") + "/" + fileName;
        String path = Paths.get("").toAbsolutePath().toString() + "\\src\\main\\java\\" + locationFile;
//        String path = "C:\\ProjekLaporan\\" + fileName;
        System.out.println("Location File : " + path);
        File file = new File(path);
        if (file.createNewFile()){
            System.out.println("File is created!");
        }else{
            System.out.println("File already exists.");
        }
        return file;
    }

    public static String createRepository(String root, Node nNode) {

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("@Repository");
        sb.append("\n");

        System.out.println("\nCurrent Element :" + nNode.getNodeName());

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

            Element eElement = (Element) nNode;

            StringBuilder sbimp = new StringBuilder(packageImport(root, eElement.getAttribute("name")));
            NodeList nColumnList = nNode.getChildNodes();

            for (int i = 0; i < nColumnList.getLength(); i++) {
                Node nNodeCol = nColumnList.item(i);

                System.out.println("\nCurrent Element :" + nNodeCol.getNodeName());
                if (nNodeCol.getNodeType() == Node.ELEMENT_NODE) {

                    if (nNodeCol.getNodeName().equals("column")) {
                        Element eElementCol = (Element) nNodeCol;
                        if (eElementCol.getAttribute("primary").equalsIgnoreCase("true")) {
                            sb.append("public interface " + eElement.getAttribute("name") + "Repo extends JpaRepository<" + eElement.getAttribute("name") + ", " + eElementCol.getAttribute("type") + "> {");
                            sb.append("\n");
                            sb.append("\n");

                            sb.append("    @Query(value = \"SELECT (max(" + eElementCol.getAttribute("name") + ") + 1) as max FROM " + eElement.getAttribute("name") + "\")");
                            sb.append("\n");

                            sb.append("    long max();");
                            sb.append("\n");
                            sb.append("\n");
                        }
                    }
                }
            }

            sb.append("} ");
            return sbimp.toString() + sb.toString();
        }

        return "";
    }

    public static void createJavaFile(String data, File file) throws IOException {
        FileOutputStream out = new FileOutputStream(file);
        out.write(data.getBytes());
        out.close();
    }

    public static void generateRepository(Document doc) throws IOException {
        String repositoryPackage = getRootElement(doc) + ".repository";
        List<Node> childNodes = GeneratorEntity.getChildNodes(doc.getElementsByTagName("service").item(0), "entity");

        for (Node item : childNodes) {
            Element el = (Element) item;
            String data = createRepository(getRootElement(doc), item);
            createJavaFile(data, className(repositoryPackage, el.getAttribute("name") + "Repo.java"));
        }
    }

}
