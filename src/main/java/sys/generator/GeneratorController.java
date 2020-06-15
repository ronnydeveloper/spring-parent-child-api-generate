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

public class GeneratorController {

    private static final String folderNameLocation = "fico/";

    public static String packageImport(String root, String model) {
        String repositoryPackages = root + ".controller;";
        StringBuilder sbx = new StringBuilder();
        sbx.append("package " + repositoryPackages);
        sbx.append("\n");
        sbx.append("\n");
        sbx.append("import org.apache.log4j.Logger;");
        sbx.append("\n");
        sbx.append("import org.springframework.stereotype.Controller;");
        sbx.append("\n");
        sbx.append("import org.springframework.web.bind.annotation.RequestMapping;");
        sbx.append("\n");
        sbx.append("import org.springframework.web.bind.annotation.RequestMethod;");
        sbx.append("\n");
        sbx.append("import org.springframework.web.servlet.ModelAndView;");
        sbx.append("\n");
        sbx.append("\n");
        return sbx.toString();
    }

    public static File className(String packages, String fileName) throws IOException {
        String locationFile = packages.replaceAll("\\.","\\/") + "/" + fileName;
        String path = Paths.get("").toAbsolutePath().toString() + "\\src\\main\\java\\" + locationFile;
//        String path = "C:\\ProjekLaporan\\" + fileName;
        System.out.println("Location File : " + path);
        File file = new File(path);
        GeneratorEntity.delete(file);

        if (file.createNewFile()){
            System.out.println("File is created!");
        }else{
            System.out.println("File already exists.");
        }
        return file;
    }


    public static String createController(String root, Node nNode) {

        StringBuilder sb = new StringBuilder();
        System.out.println("\nCurrent Element :" + nNode.getNodeName());

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

            Element eElement = (Element) nNode;

            StringBuilder sbimp = new StringBuilder(packageImport(root, eElement.getAttribute("name")));
            sb.append("@Controller");
            sb.append("\n");
            sb.append("@RequestMapping(\"/" + eElement.getAttribute("name").toLowerCase() + "\")");
            sb.append("\n");
            sb.append("public class " + eElement.getAttribute("name") + "Controller {");
            sb.append("\n");
            sb.append("\n");

            NodeList nColumnList = nNode.getChildNodes();

            for (int i = 0; i < nColumnList.getLength(); i++) {
                Node nNodeCol = nColumnList.item(i);

                System.out.println("\nCurrent Element :" + nNodeCol.getNodeName());
                if (nNodeCol.getNodeType() == Node.ELEMENT_NODE) {

                    if (nNodeCol.getNodeName().equals("column")) {
                        Element eElementCol = (Element) nNodeCol;

                        if (eElementCol.getAttribute("primary").equalsIgnoreCase("true")) {
                            sb.append("    final static Logger logger = Logger.getLogger(" + eElement.getAttribute("name") + "Controller.class);");
                            sb.append("\n");
                            sb.append("\n");
                            sb.append("    @RequestMapping(value=\"/content\", method = RequestMethod.GET)");
                            sb.append("\n");
                            sb.append("    public ModelAndView doView() {");
                            sb.append("\n");
                            sb.append("        logger.info(\"Call doView\");");
                            sb.append("\n");
                            sb.append("        ModelAndView modelAndView = new ModelAndView();");
                            sb.append("\n");
                            sb.append("        modelAndView.setViewName(\"" + folderNameLocation + eElement.getAttribute("name").toLowerCase() + " :: content\");");
                            sb.append("\n");
                            sb.append("        return modelAndView;");
                            sb.append("\n");
                            sb.append("    }");
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

    public static void generateController(Document doc) throws IOException {
        String servicePackage = GeneratorRepository.getRootElement(doc) + ".controller";
        List<Node> childNodes = GeneratorEntity.getChildNodes(doc.getElementsByTagName("service").item(0), "entity");

        for (Node item : childNodes) {
            Element el = (Element) item;
            String data = createController(GeneratorRepository.getRootElement(doc), item);
            createJavaFile(data, className(servicePackage, el.getAttribute("name") + "Controller.java"));
        }
    }
}
