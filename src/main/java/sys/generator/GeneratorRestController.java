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

public class GeneratorRestController {

    public static String packageImport(String root, String entity) {
        String repositoryPackages = root + ".api;";
        StringBuilder sbx = new StringBuilder();
        sbx.append("package " + repositoryPackages);
        sbx.append("\n");
        sbx.append("\n");
        sbx.append("import id.co.ptap.circleaf.core.dto.ApiResponse;");
        sbx.append("\n");
        sbx.append("import " + root + ".entity." + entity + ";");
        sbx.append("\n");
        sbx.append("import " + root + ".dto." + entity + "DTO;");
        sbx.append("\n");
        sbx.append("import " + root + ".service." + entity + "Service;");
        sbx.append("\n");
        sbx.append("import org.springframework.beans.factory.annotation.Autowired;");
        sbx.append("\n");
        sbx.append("import org.apache.log4j.Logger;");
        sbx.append("\n");
        sbx.append("import org.springframework.http.HttpStatus;");
        sbx.append("\n");
        sbx.append("import org.springframework.http.MediaType;");
        sbx.append("\n");
        sbx.append("import org.springframework.http.ResponseEntity;");
        sbx.append("\n");
        sbx.append("import org.springframework.security.access.prepost.PreAuthorize;");
        sbx.append("\n");
        sbx.append("import org.springframework.web.bind.annotation.*;");
        sbx.append("\n");
        sbx.append("import java.util.List;");
        sbx.append("\n");
        sbx.append("\n");
        return sbx.toString();
    }

    public static File className(String packages, String fileName) throws IOException {
        String locationFile = packages.replaceAll("\\.", "\\/") + "/" + fileName;
        String path = Paths.get("").toAbsolutePath().toString() + "\\src\\main\\java\\" + locationFile;
//        String path = "C:\\ProjekLaporan\\" + fileName;
        System.out.println("Location File : " + path);
        File file = new File(path);
        GeneratorEntity.delete(file);

        if (file.createNewFile()) {
            System.out.println("File is created!");
        } else {
            System.out.println("File already exists.");
        }
        return file;
    }

    public static String createRestController(String root, Node nNode) {

        StringBuilder sb = new StringBuilder();
        System.out.println("\nCurrent Element :" + nNode.getNodeName());

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

            Element eElement = (Element) nNode;

            StringBuilder sbimp = new StringBuilder(packageImport(root, eElement.getAttribute("name")));
            sb.append("@RestController");
            sb.append("\n");
            sb.append("@RequestMapping(\"/api/" + eElement.getAttribute("name").toLowerCase() + "\")");
            sb.append("\n");
            sb.append("public class " + eElement.getAttribute("name") + "RestController {");
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
                            sb.append("    final static Logger logger = Logger.getLogger(" + eElement.getAttribute("name") + "RestController.class);");
                            sb.append("\n");
                            sb.append("\n");
                            sb.append("    @Autowired");
                            sb.append("\n");
                            sb.append("    private " + eElement.getAttribute("name") + "Service " + GeneratorEntity.lop(eElement.getAttribute("name")) + "Service;");
                            sb.append("\n");

                            sb.append("\n");
                            sb.append("    @GetMapping({\"/list\"})");
                            sb.append("\n");
                            sb.append("    @PreAuthorize(\"@securityConfigService.hasPermission()\")");
                            sb.append("\n");
                            sb.append("    public ResponseEntity<ApiResponse> list() {");
                            sb.append("\n");
                            sb.append("        logger.info(\"Call list function\");");
                            sb.append("\n");
                            sb.append("        ApiResponse<List<" + eElement.getAttribute("name") + "DTO>> apiResponse = this." + GeneratorEntity.lop(eElement.getAttribute("name")) + "Service.doView();");
                            sb.append("\n");
                            sb.append("        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);");
                            sb.append("\n");
                            sb.append("    }");
                            sb.append("\n");

                            sb.append("\n");
                            sb.append("    @RequestMapping(value = \"/create\",");
                            sb.append("\n");
                            sb.append("            produces = \"application/json\",");
                            sb.append("\n");
                            sb.append("            method= RequestMethod.POST)");
                            sb.append("\n");
                            sb.append("    @PreAuthorize(\"@securityConfigService.hasPermission()\")");
                            sb.append("\n");
                            sb.append("    public ResponseEntity<ApiResponse> create(@RequestBody " + eElement.getAttribute("name") + " " + GeneratorEntity.lop(eElement.getAttribute("name")) + ") {");
                            sb.append("\n");
                            sb.append("        logger.info(\"Call create function\");");
                            sb.append("\n");
                            sb.append("        ApiResponse apiResponse = this."+ GeneratorEntity.lop(eElement.getAttribute("name")) + "Service.doAdd("+ GeneratorEntity.lop(eElement.getAttribute("name")) + ");");
                            sb.append("\n");
                            sb.append("        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);");
                            sb.append("\n");
                            sb.append("    }");
                            sb.append("\n");

                            sb.append("\n");
                            sb.append("    @PostMapping(\"/edit\")");
                            sb.append("\n");
                            sb.append("    @PreAuthorize(\"@securityConfigService.hasPermission()\")");
                            sb.append("\n");
                            sb.append("    public ResponseEntity<ApiResponse> edit(@RequestBody " + eElement.getAttribute("name") + " " + GeneratorEntity.lop(eElement.getAttribute("name")) + ") {");
                            sb.append("\n");
                            sb.append("        logger.info(\"Call edit function\");");
                            sb.append("\n");
                            sb.append("        ApiResponse apiResponse = this."+ GeneratorEntity.lop(eElement.getAttribute("name")) + "Service.doEdit("+ GeneratorEntity.lop(eElement.getAttribute("name")) + ");");
                            sb.append("\n");
                            sb.append("        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);");
                            sb.append("\n");
                            sb.append("    }");
                            sb.append("\n");

                            sb.append("\n");
                            sb.append("    @PostMapping(\"/remove\")");
                            sb.append("\n");
                            sb.append("    @PreAuthorize(\"@securityConfigService.hasPermission()\")");
                            sb.append("\n");
                            sb.append("    public ResponseEntity<ApiResponse> remove(@RequestBody List<" + eElement.getAttribute("name") + "> " + GeneratorEntity.lop(eElement.getAttribute("name")) + "List) {");
                            sb.append("\n");
                            sb.append("        logger.info(\"Call remove function\");");
                            sb.append("\n");
                            sb.append("        ApiResponse apiResponse = this."+ GeneratorEntity.lop(eElement.getAttribute("name")) + "Service.doDelete("+ GeneratorEntity.lop(eElement.getAttribute("name")) + "List);");
                            sb.append("\n");
                            sb.append("        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);");
                            sb.append("\n");
                            sb.append("    }");
                            sb.append("\n");

                            sb.append("\n");
                            sb.append("    @PostMapping(\"/preview\")");
                            sb.append("\n");
                            sb.append("    @PreAuthorize(\"@securityConfigService.hasPermission()\")");
                            sb.append("\n");
                            sb.append("    public ResponseEntity<ApiResponse> preview(@RequestBody " + eElement.getAttribute("name") + "DTO " + GeneratorEntity.lop(eElement.getAttribute("name")) + "DTO) {");
                            sb.append("\n");
                            sb.append("        logger.info(\"Call preview function\");");
                            sb.append("\n");
                            sb.append("        ApiResponse apiResponse = this."+ GeneratorEntity.lop(eElement.getAttribute("name")) + "Service.doPreview("+ GeneratorEntity.lop(eElement.getAttribute("name")) + "DTO);");
                            sb.append("\n");
                            sb.append("        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);");
                            sb.append("\n");
                            sb.append("    }");
                            sb.append("\n");
                        }
                    }
                }
            }
            sb.append("\n");
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

    public static void generateRestController(Document doc) throws IOException {
        String servicePackage = GeneratorRepository.getRootElement(doc) + ".api";
        List<Node> childNodes = GeneratorEntity.getChildNodes(doc.getElementsByTagName("service").item(0), "entity");

        for (Node item : childNodes) {
            Element el = (Element) item;
            String data = createRestController(GeneratorRepository.getRootElement(doc), item);
            createJavaFile(data, className(servicePackage, el.getAttribute("name") + "RestController.java"));
        }
    }
}
