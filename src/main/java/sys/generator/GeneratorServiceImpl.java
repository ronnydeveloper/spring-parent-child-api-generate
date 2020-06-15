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

public class GeneratorServiceImpl {

    public static String packageImport(String root, String entity) {
        String repositoryPackages = root + ".service.impl;";
        StringBuilder sbx = new StringBuilder();
        sbx.append("package " + repositoryPackages);
        sbx.append("\n");
        sbx.append("\n");
        sbx.append("import id.co.ptap.circleaf.core.dto.ApiResponse;");
        sbx.append("\n");
        sbx.append("import id.co.ptap.circleaf.core.enums.ResponseCode;");
        sbx.append("\n");
        sbx.append("import " + root + ".entity." + entity + ";");
        sbx.append("\n");
        sbx.append("import " + root + ".dto." + entity + "DTO;");
        sbx.append("\n");
        sbx.append("import " + root + ".repository." + entity + "Repo;");
        sbx.append("\n");
        sbx.append("import " + root + ".service." + entity + "Service;");
        sbx.append("\n");
        sbx.append("import org.apache.log4j.Logger;");
        sbx.append("\n");
        sbx.append("import org.modelmapper.ModelMapper;");
        sbx.append("\n");
        sbx.append("import org.springframework.beans.factory.annotation.Autowired;");
        sbx.append("\n");
        sbx.append("import org.springframework.stereotype.Service;").append("\n");
        sbx.append("\n");
        sbx.append("import javax.persistence.EntityExistsException;");
        sbx.append("\n");
        sbx.append("import javax.persistence.EntityNotFoundException;");
        sbx.append("\n");
        sbx.append("import java.util.Optional;");
        sbx.append("\n");
        sbx.append("import java.util.stream.Collectors;");
        sbx.append("\n");
        sbx.append("import java.util.stream.StreamSupport;");
        sbx.append("\n");
        sbx.append("import java.util.List;");
        sbx.append("\n");
        sbx.append("import java.util.ArrayList;");
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

    public static String createServiceImpl(String root, Node nNode) {

        StringBuilder sb = new StringBuilder();
        System.out.println("\nCurrent Element :" + nNode.getNodeName());

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;

            StringBuilder sbimp = new StringBuilder(packageImport(root, eElement.getAttribute("name")));
            sb.append("@Service(\""+ GeneratorEntity.lop(eElement.getAttribute("name")) +"Service\")");
            sb.append("\n");
            sb.append("public class " + eElement.getAttribute("name") + "ServiceImpl implements " + eElement.getAttribute("name") + "Service {").append("\n");
            sb.append("\n");
            sb.append("    static final Logger logger = Logger.getLogger(" + eElement.getAttribute("name") + "ServiceImpl.class);").append("\n\n");
            sb.append("    @Autowired").append("\n");
            sb.append("    private "+ eElement.getAttribute("name") +"Repo "+ GeneratorEntity.lop(eElement.getAttribute("name")) +"Repository;").append("\n\n");

            NodeList nColumnList = nNode.getChildNodes();

            for (int i = 0; i < nColumnList.getLength(); i++) {
                Node nNodeCol = nColumnList.item(i);

                System.out.println("\nCurrent Element :" + nNodeCol.getNodeName());
                if (nNodeCol.getNodeType() == Node.ELEMENT_NODE) {

                    if (nNodeCol.getNodeName().equals("column")) {
                        Element eElementCol = (Element) nNodeCol;

                        if (eElementCol.getAttribute("primary").equalsIgnoreCase("true")) {

                            sb.append("    @Override");
                            sb.append("\n");
                            sb.append("    public "+ eElement.getAttribute("name") + " createOrUpdate"+eElement.getAttribute("name")+"("+eElement.getAttribute("name")+" "+GeneratorEntity.lop(eElement.getAttribute("name"))+") {");
                            sb.append("\n");
                            sb.append("         Optional<"+ eElement.getAttribute("name") + "> "+ GeneratorEntity.lop(eElement.getAttribute("name")) + "Optional = "+ GeneratorEntity.lop(eElement.getAttribute("name")) +"Repository.findById("+ GeneratorEntity.lop(eElement.getAttribute("name")) +".get"+GeneratorEntity.cap(eElementCol.getAttribute("name"))+"());");
                            sb.append("\n");
                            sb.append("         if("+ GeneratorEntity.lop(eElement.getAttribute("name")) + "Optional.isPresent())");
                            sb.append("\n");
                            sb.append("         {");
                            sb.append("\n");
                            sb.append("             ModelMapper modelMapper = new ModelMapper();");
                            sb.append("\n");
                            sb.append("             "+ eElement.getAttribute("name") + " new"+ eElement.getAttribute("name") + " = modelMapper.map("+ GeneratorEntity.lop(eElement.getAttribute("name")) + ", "+ eElement.getAttribute("name") + ".class);");
                            sb.append("\n");
                            sb.append("             new"+ eElement.getAttribute("name") + " = "+ GeneratorEntity.lop(eElement.getAttribute("name")) + "Repository.save(new"+ eElement.getAttribute("name") +");");
                            sb.append("\n");
                            sb.append("             return new"+ eElement.getAttribute("name") + ";");
                            sb.append("\n");
                            sb.append("         } else {");
                            sb.append("\n");
                            sb.append("             "+ GeneratorEntity.lop(eElement.getAttribute("name")) + " = "+ GeneratorEntity.lop(eElement.getAttribute("name")) + "Repository.save(" + GeneratorEntity.lop(eElement.getAttribute("name")) + ");");
                            sb.append("\n");
                            sb.append("             return "+ GeneratorEntity.lop(eElement.getAttribute("name")) + ";");
                            sb.append("\n");
                            sb.append("         }");
                            sb.append("\n");
                            sb.append("    }");
                            sb.append("\n");
                            sb.append("\n");

                            sb.append("    @Override");
                            sb.append("\n");
                            sb.append("    public void delete"+ eElement.getAttribute("name") + "ById(" + eElementCol.getAttribute("type") + " " + GeneratorEntity.lop(eElementCol.getAttribute("name")) + ") throws EntityNotFoundException {");
                            sb.append("\n");
                            sb.append("         Optional<"+ eElement.getAttribute("name") + "> "+ GeneratorEntity.lop(eElement.getAttribute("name")) + "Optional = "+ GeneratorEntity.lop(eElement.getAttribute("name")) +"Repository.findById("+ GeneratorEntity.lop(eElementCol.getAttribute("name")) + ");");
                            sb.append("\n");
                            sb.append("         if("+ GeneratorEntity.lop(eElement.getAttribute("name")) + "Optional.isPresent())");
                            sb.append("\n");
                            sb.append("         {");
                            sb.append("\n");
                            sb.append("            "+ GeneratorEntity.lop(eElement.getAttribute("name")) +"Repository.deleteById("+GeneratorEntity.lop(eElementCol.getAttribute("name"))+");");
                            sb.append("\n");
                            sb.append("         } else {");
                            sb.append("\n");
                            sb.append("            throw new EntityNotFoundException(\"No "+ eElement.getAttribute("name") +" record exist for given id\");");
                            sb.append("\n");
                            sb.append("         }");
                            sb.append("\n");
                            sb.append("    }");
                            sb.append("\n");
                            sb.append("\n");

                            sb.append("    @Override");
                            sb.append("\n");
                            sb.append("    public "+ eElement.getAttribute("name") + " get"+eElement.getAttribute("name")+ "ById(" + eElementCol.getAttribute("type") + " " + eElementCol.getAttribute("name") + ") throws EntityNotFoundException {");
                            sb.append("\n");
                            sb.append("         Optional<"+ eElement.getAttribute("name") + "> "+ GeneratorEntity.lop(eElement.getAttribute("name")) + "Optional = "+ GeneratorEntity.lop(eElement.getAttribute("name")) +"Repository.findById("+ eElementCol.getAttribute("name") + ");");
                            sb.append("\n");
                            sb.append("         if("+ GeneratorEntity.lop(eElement.getAttribute("name")) + "Optional.isPresent())");
                            sb.append("\n");
                            sb.append("         {");
                            sb.append("\n");
                            sb.append("            return "+ GeneratorEntity.lop(eElement.getAttribute("name")) + "Optional.get();");
                            sb.append("\n");
                            sb.append("         } else {");
                            sb.append("\n");
                            sb.append("            throw new EntityNotFoundException(\"No "+ eElement.getAttribute("name") + " record exist for given id\");");
                            sb.append("\n");
                            sb.append("         }");
                            sb.append("\n");
                            sb.append("    }");
                            sb.append("\n");
                            sb.append("\n");

                            sb.append("    @Override");
                            sb.append("\n");
                            sb.append("    public ApiResponse<List<"+ eElement.getAttribute("name") +"DTO>> doView() {");
                            sb.append("\n");
                            sb.append("         ApiResponse apiResponse = new ApiResponse();");
                            sb.append("\n");
                            sb.append("         try {");
                            sb.append("\n");
                            sb.append("             List<"+ eElement.getAttribute("name") +"DTO> "+ GeneratorEntity.lop(eElement.getAttribute("name")) + "List = this.findAll();");
                            sb.append("\n");
                            sb.append("             apiResponse.setData("+ GeneratorEntity.lop(eElement.getAttribute("name")) + "List);");
                            sb.append("\n");
                            sb.append("         } catch (Exception var3) {");
                            sb.append("\n");
                            sb.append("             logger.error(var3);");
                            sb.append("\n");
                            sb.append("             apiResponse.setResponseCodeEnum(ResponseCode._999);");
                            sb.append("\n");
                            sb.append("             apiResponse.setResponseMessage(var3.getMessage());");
                            sb.append("\n");
                            sb.append("         }");
                            sb.append("\n");
                            sb.append("         return apiResponse;");
                            sb.append("\n");
                            sb.append("    }");
                            sb.append("\n");
                            sb.append("\n");

                            sb.append("    @Override");
                            sb.append("\n");
                            sb.append("    public ApiResponse doAdd("+ eElement.getAttribute("name") +" "+ GeneratorEntity.lop(eElement.getAttribute("name")) +") {");
                            sb.append("\n");
                            sb.append("         ApiResponse apiResponse = new ApiResponse();");
                            sb.append("\n");
                            sb.append("         try {");
                            sb.append("\n");
                            sb.append("            if ("+GeneratorEntity.lop(eElement.getAttribute("name"))+" == null) {");
                            sb.append("\n");
                            sb.append("                throw new NullPointerException(\""+eElement.getAttribute("name")+" cannot be null\");");
                            sb.append("\n");
                            sb.append("            }");
                            sb.append("\n");
                            sb.append("            else {");
                            sb.append("\n");
                            sb.append("                long max = 0;");
                            sb.append("\n");
                            sb.append("                long count = "+ GeneratorEntity.lop(eElement.getAttribute("name")) +"Repository.count();");
                            sb.append("\n");
                            sb.append("                if(count < 1) {");
                            sb.append("\n");
                            sb.append("                    max = 1;");
                            sb.append("\n");
                            sb.append("                } else {");
                            sb.append("\n");
                            sb.append("                    max = "+ GeneratorEntity.lop(eElement.getAttribute("name")) +"Repository.max();");
                            sb.append("\n");
                            sb.append("                }");
                            sb.append("\n");
                            sb.append("                "+GeneratorEntity.lop(eElement.getAttribute("name"))+".set"+GeneratorEntity.cap(eElementCol.getAttribute("name"))+"(max);");
                            sb.append("\n");
                            sb.append("            }");
                            sb.append("\n");
                            sb.append("            Optional<"+eElement.getAttribute("name")+"> "+ GeneratorEntity.lop(eElement.getAttribute("name")) +"Optional = "+ GeneratorEntity.lop(eElement.getAttribute("name")) +"Repository.findById("+GeneratorEntity.lop(eElement.getAttribute("name"))+".get"+GeneratorEntity.cap(eElementCol.getAttribute("name"))+"());");
                            sb.append("\n");
                            sb.append("            if("+ GeneratorEntity.lop(eElement.getAttribute("name")) +"Optional.isPresent()) {");
                            sb.append("\n");
                            sb.append("                throw new EntityExistsException(\"There is a "+ eElement.getAttribute("name") +" record exist for given " + eElementCol.getAttribute("name") + "\");");
                            sb.append("\n");
                            sb.append("            } else {");
                            sb.append("\n");
                            sb.append("                this.createOrUpdate"+eElement.getAttribute("name")+"("+GeneratorEntity.lop(eElement.getAttribute("name"))+");");
                            sb.append("\n");
                            sb.append("            }");
                            sb.append("\n");
                            sb.append("        } catch (Exception var5) {");
                            sb.append("\n");
                            sb.append("            logger.error(var5);");
                            sb.append("\n");
                            sb.append("            apiResponse.setResponseCodeEnum(ResponseCode._999);");
                            sb.append("\n");
                            sb.append("            apiResponse.setResponseMessage(var5.getMessage());");
                            sb.append("\n");
                            sb.append("        }");
                            sb.append("\n");
                            sb.append("        return apiResponse;");
                            sb.append("\n");
                            sb.append("    }");
                            sb.append("\n");
                            sb.append("\n");

                            sb.append("    @Override");
                            sb.append("\n");
                            sb.append("    public ApiResponse doEdit("+ eElement.getAttribute("name") +" "+ GeneratorEntity.lop(eElement.getAttribute("name")) +") {");
                            sb.append("\n");
                            sb.append("        ApiResponse apiResponse = new ApiResponse();");
                            sb.append("\n");
                            sb.append("        try {");
                            sb.append("\n");
                            sb.append("            if (" + GeneratorEntity.lop(eElement.getAttribute("name")) + " == null) {");
                            sb.append("\n");
                            sb.append("                throw new NullPointerException(\"" + GeneratorEntity.lop(eElement.getAttribute("name")) + " cannot be null\");");
                            sb.append("\n");
                            sb.append("            }");
                            sb.append("\n");
                            sb.append("            this.createOrUpdate"+eElement.getAttribute("name")+"("+GeneratorEntity.lop(eElement.getAttribute("name"))+");");
                            sb.append("\n");
                            sb.append("        } catch (Exception var5) {");
                            sb.append("\n");
                            sb.append("            logger.error(var5);");
                            sb.append("\n");
                            sb.append("            apiResponse.setResponseCodeEnum(ResponseCode._999);");
                            sb.append("\n");
                            sb.append("            apiResponse.setResponseMessage(var5.getMessage());");
                            sb.append("\n");
                            sb.append("        }");
                            sb.append("\n");
                            sb.append("        return apiResponse;");
                            sb.append("\n");
                            sb.append("    }");
                            sb.append("\n");
                            sb.append("\n");

                            sb.append("    @Override");
                            sb.append("\n");
                            sb.append("    public ApiResponse doDelete(List<"+ eElement.getAttribute("name") +"> "+ GeneratorEntity.lop(eElement.getAttribute("name")) +"List) {");
                            sb.append("\n");
                            sb.append("        ApiResponse apiResponse = new ApiResponse();");
                            sb.append("\n");
                            sb.append("        try {");
                            sb.append("\n");
                            sb.append("            if ("+ GeneratorEntity.lop(eElement.getAttribute("name")) +"List.size() < 1) {");
                            sb.append("\n");
                            sb.append("                throw new NullPointerException(\""+ GeneratorEntity.lop(eElement.getAttribute("name")) +" cannot be null\");");
                            sb.append("\n");
                            sb.append("            }");
                            sb.append("\n");
                            sb.append("            for ("+eElement.getAttribute("name")+" obj : "+ GeneratorEntity.lop(eElement.getAttribute("name")) +"List) {");
                            sb.append("\n");
                            sb.append("                this.delete"+ eElement.getAttribute("name") + "ById(obj.get" + GeneratorEntity.cap(eElementCol.getAttribute("name")) + "());");
                            sb.append("\n");
                            sb.append("            }");
                            sb.append("\n");
                            sb.append("        } catch (Exception var5) {");
                            sb.append("\n");
                            sb.append("            logger.error(var5);");
                            sb.append("\n");
                            sb.append("            apiResponse.setResponseCodeEnum(ResponseCode._999);");
                            sb.append("\n");
                            sb.append("            apiResponse.setResponseMessage(var5.getMessage());");
                            sb.append("\n");
                            sb.append("        }");
                            sb.append("\n");
                            sb.append("        return apiResponse;");
                            sb.append("\n");
                            sb.append("    }");
                            sb.append("\n");
                            sb.append("\n");

                            sb.append("    @Override");
                            sb.append("\n");
                            sb.append("    public ApiResponse doPreview("+ eElement.getAttribute("name") +"DTO "+ GeneratorEntity.lop(eElement.getAttribute("name")) +"DTO) {");
                            sb.append("\n");
                            sb.append("        ApiResponse apiResponse = new ApiResponse();");
                            sb.append("\n");
                            sb.append("        try {");
                            sb.append("\n");
                            sb.append("            apiResponse.setData(this.get"+ eElement.getAttribute("name") +"ById("+GeneratorEntity.lop(eElement.getAttribute("name"))+"DTO.get"+GeneratorEntity.cap(eElementCol.getAttribute("name"))+"()));");
                            sb.append("\n");
                            sb.append("        } catch (Exception var3) {");
                            sb.append("\n");
                            sb.append("            logger.error(var3);");
                            sb.append("\n");
                            sb.append("            apiResponse.setResponseCodeEnum(ResponseCode._999);");
                            sb.append("\n");
                            sb.append("            apiResponse.setResponseMessage(var3.getMessage());");
                            sb.append("\n");
                            sb.append("        }");
                            sb.append("\n");
                            sb.append("        return apiResponse;");
                            sb.append("\n");
                            sb.append("    }");
                            sb.append("\n");
                            sb.append("\n");

                            sb.append("    @Override");
                            sb.append("\n");
                            sb.append("    public List<"+ eElement.getAttribute("name") +"DTO> findAll() {");
                            sb.append("\n");
                            sb.append("        List<"+ eElement.getAttribute("name") +"DTO> newList = new ArrayList<>();");
                            sb.append("\n");
                            sb.append("        for("+ eElement.getAttribute("name") +" p : "+ GeneratorEntity.lop(eElement.getAttribute("name")) +"Repository.findAll()) {");
                            sb.append("\n");
                            sb.append("             "+ eElement.getAttribute("name") +"DTO " + eElement.getAttribute("name").toLowerCase() + "DTO = " + eElement.getAttribute("name") + "DTO.builder()");
                            sb.append("\n");
                            sb.append("                     ."+ eElementCol.getAttribute("name") + "(p.get"+ cap(eElementCol.getAttribute("name")) +"())");

                        } else {
                            sb.append("\n");
                            if (eElementCol.getAttribute("type").equalsIgnoreCase("class")) {
                                sb.append("                     ."+ eElementCol.getAttribute("alias") + "(p.get"+ cap(eElementCol.getAttribute("alias")) +"())");
                            }
                            else if (eElementCol.getAttribute("type").equalsIgnoreCase("collection")) {
                                sb.append("                     ."+ eElementCol.getAttribute("alias") + "(p.get"+ cap(eElementCol.getAttribute("alias")) +"())");
                            }
                            else if (eElementCol.getAttribute("type").equalsIgnoreCase("HashSet")) {
                                sb.append("                     ."+ eElementCol.getAttribute("alias") + "(p.get"+ cap(eElementCol.getAttribute("alias")) +"())");
                            }
                            else {
                                sb.append("                     ."+ eElementCol.getAttribute("name") + "(p.get"+ cap(eElementCol.getAttribute("name")) +"())");
                            }

                        }
                    }
                }
            }

            sb.append(".build();");
            sb.append("\n");
            sb.append("             newList.add(" + eElement.getAttribute("name").toLowerCase() + "DTO);");
            sb.append("\n");
            sb.append("        }");
            sb.append("\n");
            sb.append("        return newList;");
            sb.append("\n");
            sb.append("    }");
            sb.append("\n");
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

    public static void generateServiceImpl(Document doc) throws IOException {
        String servicePackage = GeneratorRepository.getRootElement(doc) + ".service.impl";
        List<Node> childNodes = GeneratorEntity.getChildNodes(doc.getElementsByTagName("service").item(0), "entity");

        for (Node item : childNodes) {
            Element el = (Element) item;
            String data = createServiceImpl(GeneratorRepository.getRootElement(doc), item);
            createJavaFile(data, className(servicePackage, el.getAttribute("name") + "ServiceImpl.java"));
        }
    }

    public static String cap(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
