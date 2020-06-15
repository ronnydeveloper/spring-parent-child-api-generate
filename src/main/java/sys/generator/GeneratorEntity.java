package sys.generator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GeneratorEntity {
    public static void readXML() {
        try {
            String path = Paths.get("").toAbsolutePath().toString();
            File fXmlFile = new File(path + "/service.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            generatePackage(getRootElement(doc));
            generateEntity(doc);

            GeneratorDto.generateDto(doc);
            GeneratorRepository.generateRepository(doc);
            GeneratorService.generateService(doc);
            GeneratorServiceImpl.generateServiceImpl(doc);

            GeneratorController.generateController(doc);
            GeneratorRestController.generateRestController(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generatePackage(String packages) {
        String[] directories = {"api", "controller", "dto", "entity", "repository", "service", "service\\impl"};
        packages = packages.replaceAll("\\.", "\\/");
        String path = Paths.get("").toAbsolutePath().toString() + "\\src\\main\\java\\" + packages;

        for (String dir : directories) {
            File files = new File(path + "\\/" + dir);
            try {
                delete(files);

                if (!files.exists()) {
                    if (files.mkdirs()) {
                        System.out.println("Multiple directories are created!");
                    } else {
                        System.out.println("Failed to create multiple directories!");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }

        }
    }

    public static void delete(File file)
            throws IOException {

        if (file.isDirectory()) {

            //directory is empty, then delete it
            if (file.list().length == 0) {

                file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());

            } else {

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        } else {
            //if file, then delete it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }

    public static String getRootElement(Document doc) {
        NodeList nList = doc.getElementsByTagName(doc.getDocumentElement().getNodeName());

        String packages_loc = "";
        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                return eElement.getAttribute("package");
            }
        }

        return packages_loc;
    }

    public static String packageImport(String root) {
        String entityPackage = root + ".entity;";
        StringBuilder sb = new StringBuilder();
        sb.append("package " + entityPackage);
        sb.append("\n");
        sb.append("\n");
        sb.append("import com.fasterxml.jackson.annotation.JsonIgnore;");
        sb.append("\n");
        sb.append("import com.fasterxml.jackson.annotation.JsonIgnoreProperties;");
        sb.append("\n");
        sb.append("import com.fasterxml.jackson.annotation.JsonInclude;");
        sb.append("\n");
        sb.append("import com.fasterxml.jackson.annotation.JsonProperty;");
        sb.append("\n");
        sb.append("import lombok.EqualsAndHashCode;");
        sb.append("\n");
        sb.append("\n");
        sb.append("import javax.persistence.*;");
        sb.append("\n");
        return sb.toString();
    }

    public static Element getParent(Element elem) {
        Node parent = elem.getParentNode();
        if (parent instanceof Element)
            return (Element) parent;
        return null;
    }

    public static String createEntity(String root, Node nNode) {
        StringBuilder sbimp = new StringBuilder(packageImport(root));
        StringBuilder sg = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        StringBuilder ts = new StringBuilder();
        sb.append("\n");
        sb.append("@Entity");
        sb.append("\n");
        sb.append("@JsonInclude(JsonInclude.Include.NON_NULL)");
        sb.append("\n");
        sb.append("@JsonIgnoreProperties({\"hibernateLazyInitializer\", \"handler\"})");
        sb.append("\n");
        sb.append("@EqualsAndHashCode(onlyExplicitlyIncluded = true)");
        sb.append("\n");

        System.out.println("\nCurrent Element :" + nNode.getNodeName());

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

                        NodeList nHintList = nNodeCol.getChildNodes();
                        if (eElementCol.getAttribute("type").equalsIgnoreCase("class")) {

                            sg.append(writeSetter(lop(eElementCol.getAttribute("alias")),
                                    cap(eElementCol.getAttribute("alias")),
                                    cap(eElementCol.getAttribute("name")), "public"));
                            sg.append("\n");

                            sg.append(writeGetter(lop(eElementCol.getAttribute("alias")),
                                    cap(eElementCol.getAttribute("alias")),
                                    cap(eElementCol.getAttribute("name")), "public"));
                            sg.append("\n");
                        } else if (eElementCol.getAttribute("type").equalsIgnoreCase("collection")) {

                            sg.append(writeSetter(lop(eElementCol.getAttribute("alias")),
                                    cap(eElementCol.getAttribute("alias")),
                                    cap("List<" + eElementCol.getAttribute("name") + ">"), "public"));

                            sg.append("\n");
                            sg.append("    @JsonIgnore");

                            sg.append(writeGetter(lop(eElementCol.getAttribute("alias")),
                                    cap(eElementCol.getAttribute("alias")),
                                    cap("List<" + eElementCol.getAttribute("name") + ">"), "public"));
                        } else if (eElementCol.getAttribute("type").equalsIgnoreCase("HashSet")) {

                            sg.append(writeSetter(lop(eElementCol.getAttribute("alias")),
                                    cap(eElementCol.getAttribute("alias")),
                                    cap("Set<" + eElementCol.getAttribute("name") + ">"), "public"));

                            sg.append("\n");
                            sg.append("    @JsonIgnore");

                            sg.append(writeGetter(lop(eElementCol.getAttribute("alias")),
                                    cap(eElementCol.getAttribute("alias")),
                                    cap("Set<" + eElementCol.getAttribute("name") + ">"), "public"));
                        } else {

                            sg.append(writeSetter(lop(eElementCol.getAttribute("name")),
                                    cap(eElementCol.getAttribute("name")),
                                    cap(eElementCol.getAttribute("type")), "public"));

                            sg.append(writeGetter(lop(eElementCol.getAttribute("name")),
                                    cap(eElementCol.getAttribute("name")),
                                    cap(eElementCol.getAttribute("type")), "public"));

                        }

                        if (eElementCol.getAttribute("primary").equalsIgnoreCase("true")) {
                            ts.append("    @Override").append("\n");
                            ts.append("    public String toString() {").append("\n");
                            ts.append("        return \"" + eElement.getAttribute("name") + "{\" + ").append("\n");
                            if (eElementCol.getAttribute("type").equalsIgnoreCase("String")) {
                                ts.append("                  \"" + eElementCol.getAttribute("name") + "='\" + " + eElementCol.getAttribute("name") + "+ \"\\'\" + ").append("\n");
                            } else {
                                ts.append("                  \"" + eElementCol.getAttribute("name") + "=\" + " + eElementCol.getAttribute("name") + " + ").append("\n");
                            }
                            if (eElementCol.getAttribute("generation-type").length() > 0) {
                                String generationType = eElementCol.getAttribute("generation-type").toUpperCase();
                                String generator = "";
                                String constraint = "";
                                if (!eElementCol.getAttribute("generation-type").equalsIgnoreCase("table")) {
                                    if(eElement.getAttribute("unique-constraints-name").length() > 0) {
                                        if(eElement.getAttribute("constraints-column-name").length() > 0) {
                                            int result = eElement.getAttribute("constraints-column-name").indexOf(",");
                                            if(result > 0) {
                                                String[] constraintsColumnNames = eElement.getAttribute("constraints-column-name").split(",");
                                                String columnNames = "";
                                                for(String ccn : constraintsColumnNames) {
                                                    columnNames += "\"" + ccn + "\",";
                                                }
                                                columnNames = columnNames.substring(0, columnNames.lastIndexOf(','));
                                                constraint = ", uniqueConstraints = @UniqueConstraint(name = \""+ eElement.getAttribute("unique-constraints-name") +"\", columnNames = {" + columnNames + "})";
                                            } else {
                                                constraint = ", uniqueConstraints = @UniqueConstraint(name = \""+ eElement.getAttribute("unique-constraints-name") +"\", columnNames = {" + eElement.getAttribute("constraints-column-name") + "})";
                                            }
                                        }
                                    }
                                    if (eElement.getAttribute("table-name").length() > 0) {
                                        sb.append("@Table(name = \"" + eElement.getAttribute("table-name") + "\"" + constraint + ")");
                                        sb.append("\n");
                                    }
                                    if (eElement.getAttribute("table-name").length() < 1) {
                                        sb.append("@Table(name = \"" + eElement.getAttribute("name") + "\"" + constraint + ")");
                                        sb.append("\n");
                                    }
                                }

                                if (eElementCol.getAttribute("generation-type").equalsIgnoreCase("identity") ||
                                        eElementCol.getAttribute("generation-type").equalsIgnoreCase("auto")) {

                                    sb.append("public class " + eElement.getAttribute("name") + " { ");
                                    sb.append("\n");
                                    sb.append("\n");
                                    sb.append("    @Id");
                                    sb.append("\n");
                                    sb.append("    @Column(name = \"" + eElementCol.getAttribute("alias-dbname") + "\")");
                                    sb.append("\n");
                                }
                                if (eElementCol.getAttribute("generation-type").equalsIgnoreCase("sequence")) {

                                    sb.append("public class " + eElement.getAttribute("name") + " { ");
                                    sb.append("\n");
                                    sb.append("    @Id");
                                    sb.append("\n");
                                    sb.append("    @SequenceGenerator(name = \"" + eElementCol.getAttribute("generator") + "\", " +
                                            "initialValue = " + eElementCol.getAttribute("initial-value") + ", " +
                                            "allocationSize = " + eElementCol.getAttribute("allocation-size") + ")").append("\n");

                                    generator = ", generator = \"" + eElementCol.getAttribute("generator") + "\"";
                                }
                                if (eElementCol.getAttribute("generation-type").equalsIgnoreCase("table")) {
                                    sb.append("public class " + eElement.getAttribute("name") + " { ");
                                    sb.append("\n");
                                    sb.append("    @Id");
                                    sb.append("\n");
                                    sb.append("    @TableGenerator(name = \"" + eElementCol.getAttribute("generator") + "\", " +
                                            "initialValue = " + eElementCol.getAttribute("initial-value") + ", " +
                                            "allocationSize = " + eElementCol.getAttribute("allocation-size") + ")").append("\n");

                                    generator = ", generator = \"" + eElementCol.getAttribute("generator") + "\"";
                                }
                                sb.append("    @GeneratedValue(strategy = GenerationType." + generationType + generator + ")");
                                sb.append("\n");
                                sb.append("    @EqualsAndHashCode.Include");
                                sb.append("\n");

                            }
                        } else {

                            String alias = "";
                            if (eElementCol.getAttribute("alias-dbname").length() > 0) {
                                alias = eElementCol.getAttribute("alias-dbname");
                            }

                            if (eElementCol.getAttribute("alias-dbname").length() < 1) {
                                alias = eElementCol.getAttribute("name");
                            }

                            if (nHintList.getLength() > 0) {
                                for (int hi = 0; hi < nHintList.getLength(); hi++) {

                                    Node nNodeHint = nHintList.item(hi);
                                    if (nNodeHint.getNodeType() == Node.ELEMENT_NODE) {
                                        Element eElementHint = (Element) nNodeHint;
                                        if (eElementCol.getAttribute("type").equalsIgnoreCase("class")
                                                || eElementCol.getAttribute("type").equalsIgnoreCase("collection")
                                                || eElementCol.getAttribute("type").equalsIgnoreCase("HashSet")) {
                                            if (eElementHint.getTagName().equalsIgnoreCase("join-column") ||
                                                    eElementHint.getTagName().equalsIgnoreCase("join-table")) {
                                                if (eElementHint.getAttribute("maps-id").equalsIgnoreCase("true")) {
                                                    sb.append("    @MapsId").append("\n");
                                                }
                                            }
                                            if (eElementHint.getTagName().equalsIgnoreCase("join-column")) {

                                                sb.append("    @JoinColumn(name = \"" + eElementHint.getAttribute("name") + "\", " +
                                                        "referencedColumnName = \"" + eElementHint.getAttribute("referencedColumnName") + "\", " +
                                                        "foreignKey=@ForeignKey(name = \"" + eElementHint.getAttribute("foreign-key-name") + "\"), " +
                                                        "unique = " + eElementHint.getAttribute("unique") + ", " +
                                                        "nullable = " + eElementHint.getAttribute("nullable") + ", " +
                                                        "updatable = " + eElementHint.getAttribute("updatable") + " )").append("\n");

                                            }

                                            if (eElementHint.getTagName().equalsIgnoreCase("join-table")) {
                                                sb.append("    @JoinTable(name = \"" + eElementHint.getAttribute("name") + "\")").append("\n");
                                            }

                                            if (eElementHint.getTagName().equalsIgnoreCase("foreign-key")) {
                                                sb.append("    @ForeignKey(name=\"" + eElementHint.getAttribute("name") + "\")").append("\n");
                                            }
                                            if (eElementHint.getTagName().equalsIgnoreCase("association")) {
                                                if (eElementHint.getAttribute("mappedBy").length() > 0) {

                                                    if (eElementHint.getAttribute("name").equalsIgnoreCase("OneToMany")) {

                                                        sb.append("    @" + eElementHint.getAttribute("name") + "(mappedBy = \"" + eElementHint.getAttribute("mappedBy") + "\", " +
                                                                "orphanRemoval = " + eElementHint.getAttribute("orphan-removal") + ", " +
                                                                "cascade = CascadeType." + eElementHint.getAttribute("cascade").toUpperCase() + ", " +
                                                                "fetch = FetchType." + eElementHint.getAttribute("fetch").toUpperCase() + ")").append("\n");

                                                        sb.append("    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)").append("\n");
                                                    } else if (eElementHint.getAttribute("name").equalsIgnoreCase("ManyToMany")) {

                                                        sb.append("    @" + eElementHint.getAttribute("name") + "(mappedBy = \"" + eElementHint.getAttribute("mappedBy") + "\", " +
                                                                "cascade = CascadeType." + eElementHint.getAttribute("cascade").toUpperCase() + ", " +
                                                                "fetch = FetchType." + eElementHint.getAttribute("fetch").toUpperCase() + ")").append("\n");
                                                        sb.append("    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)").append("\n");
                                                    } else {

                                                        sb.append("    @" + eElementHint.getAttribute("name") + "(optional = " + eElementHint.getAttribute("optional") + ", " +
                                                                "mappedBy = \"" + eElementHint.getAttribute("mappedBy") + "\", " +
                                                                "cascade = CascadeType." + eElementHint.getAttribute("cascade").toUpperCase() + ", " +
                                                                "fetch = FetchType." + eElementHint.getAttribute("fetch").toUpperCase() + ")").append("\n");
                                                        sb.append("    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)").append("\n");
                                                    }
                                                } else {
                                                    if (eElementHint.getAttribute("name").equalsIgnoreCase("OneToMany") ||
                                                            eElementHint.getAttribute("name").equalsIgnoreCase("ManyToMany")) {

                                                        sb.append("    @" + eElementHint.getAttribute("name") + "(cascade = CascadeType." + eElementHint.getAttribute("cascade").toUpperCase() + ", " +
                                                                "fetch = FetchType." + eElementHint.getAttribute("fetch").toUpperCase() + ")").append("\n");
                                                    } else {

                                                        sb.append("    @" + eElementHint.getAttribute("name") + "(optional = " + eElementHint.getAttribute("optional") + ", " +
                                                                "cascade = CascadeType." + eElementHint.getAttribute("cascade").toUpperCase() + ", " +
                                                                "fetch = FetchType." + eElementHint.getAttribute("fetch").toUpperCase() + ")").append("\n");
                                                    }
                                                }
                                            }

                                        } else {
                                            if (eElementCol.getAttribute("type").equalsIgnoreCase("String")) {
                                                ts.append("                  \", " + eElementCol.getAttribute("name") + "='\" + " + eElementCol.getAttribute("name") + "+ \"\\'\" + ").append("\n");
                                            } else {
                                                ts.append("                  \", " + eElementCol.getAttribute("name") + "=\" + " + eElementCol.getAttribute("name") + " + ").append("\n");
                                            }

                                            if (eElementCol.getAttribute("name").equalsIgnoreCase(getParent(eElementHint).getAttribute("name"))) {

                                                if(eElementHint.getAttribute("column-definition").length() > 0) {

                                                    sb.append("    @Column(name = \"" + alias
                                                            + "\", columnDefinition  = \"" + eElementHint.getAttribute("column-definition") + "\")");
                                                    sb.append("\n");
                                                }
                                                else if(eElementHint.getAttribute("scale").length() > 0 ||
                                                        eElementHint.getAttribute("precision").length() > 0) {
                                                    sb.append("    @Column(name = \"" + alias
                                                            + "\", precision = " + eElementHint.getAttribute("precision")
                                                            + ", scale  = " + eElementHint.getAttribute("scale") + ")");
                                                    sb.append("\n");
                                                }
                                                else {

                                                    sb.append("    @Column(name = \"" + alias
                                                            + "\", nullable = " + eElementHint.getAttribute("nullable")
                                                            + ", length = " + eElementHint.getAttribute("length") + ")");
                                                    sb.append("\n");
                                                }
                                            }
                                            else {

                                                sb.append("    @Column(name = \"" + alias + "\")");
                                                sb.append("\n");
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (eElementCol.getAttribute("type").equalsIgnoreCase("class")) {

                                }
                                else if (eElementCol.getAttribute("type").equalsIgnoreCase("HashSet")) {

                                }
                                else {
                                    if (eElementCol.getAttribute("type").equalsIgnoreCase("String")) {
                                        ts.append("                  \", " + eElementCol.getAttribute("name") + "='\" + " + eElementCol.getAttribute("name") + "+ \"\\'\" + ").append("\n");
                                    } else {
                                        ts.append("                  \", " + eElementCol.getAttribute("name") + "=\" + " + eElementCol.getAttribute("name") + " + ").append("\n");
                                    }

                                    sb.append("    @Column(name = \"" + alias + "\")");
                                    sb.append("\n");
                                }
                            }
                            if (eElementCol.getAttribute("format").length() > 0) {
                                sb.append("    @JsonFormat(pattern = \"" + eElementCol.getAttribute("format") + "\")");
                                sb.append("\n");
                            }
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

                            ts.append("                  \", " + eElementCol.getAttribute("alias") + "=\" + " + eElementCol.getAttribute("alias") + " + ").append("\n");

                        } else if (eElementCol.getAttribute("type").equalsIgnoreCase("collection")) {
                            isCollection = true;

                            sb.append("    private List<" + eElementCol.getAttribute("name") + "> " + eElementCol.getAttribute("alias") + ";");
                            sb.append("\n");

                            ts.append("                  \", " + eElementCol.getAttribute("alias") + "=\" + " + eElementCol.getAttribute("alias") + " + ").append("\n");
                        } else if (eElementCol.getAttribute("type").equalsIgnoreCase("HashSet")) {
                            isHashSet = true;

                            sb.append("    private Set<" + eElementCol.getAttribute("name") + "> " + eElementCol.getAttribute("alias") + ";");
                            sb.append("\n");

                            ts.append("                  \", " + eElementCol.getAttribute("alias") + "=\" + " + eElementCol.getAttribute("alias") + " + ").append("\n");
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
                sbimp.append("import com.fasterxml.jackson.annotation.JsonFormat;");
                sbimp.append("\n");
                sbimp.append("\n");
            }
//                NodeList nColumnList = doc.getElementsByTagName("column");
//                NodeList nHintList = doc.getElementsByTagName("hint");
//
//                System.out.println("----------------------------");
//
//                for (int temp1 = 0; temp1 < nColumnList.getLength(); temp1++) {
//
//                    Node nNodeCol = nColumnList.item(temp1);
//
//                    System.out.println("\nCurrent Element :" + nNodeCol.getNodeName());
//
//                    if (nNodeCol.getNodeType() == Node.ELEMENT_NODE) {
//
//                        Element eElementCol = (Element) nNodeCol;
//                        if(eElementCol.getAttribute("primary").equalsIgnoreCase("true")) {
//
//                            if(eElementCol.getAttribute("generation-type").length() > 0) {
//                                String generationType = eElementCol.getAttribute("generation-type").toUpperCase();
//                                String generator = "";
//                                if(eElementCol.getAttribute("generation-type").equalsIgnoreCase("identity") ||
//                                        eElementCol.getAttribute("generation-type").equalsIgnoreCase("auto")) {
//
//                                    if(eElement.getAttribute("table-name").length() > 0) {
//                                        sb.append("@Table(name=\""+eElement.getAttribute("table-name")+"\")");
//                                        sb.append("\n");
//                                    }
//
//                                    if(eElement.getAttribute("table-name").length() < 1) {
//                                        sb.append("@Table(name=\""+eElement.getAttribute("name")+"\")");
//                                        sb.append("\n");
//                                    }
//
//                                    sb.append("public class "+eElement.getAttribute("name")+" { ");
//                                    sb.append("\n");
//                                    sb.append("\n");
//                                    sb.append("    @Id");
//                                    sb.append("\n");
//                                }
//                                if(eElementCol.getAttribute("generation-type").equalsIgnoreCase("sequence")) {
//                                    sb.append("@SequenceGenerator(name=\""+eElementCol.getAttribute("generator")+"\", "+
//                                            "initialValue=\""+eElementCol.getAttribute("initial-value")+"\", "+
//                                            "allocationSize=\""+eElementCol.getAttribute("allocation-size")+"\")");
//                                    sb.append("\n");
//                                    sb.append("public class "+eElement.getAttribute("name")+" { ");
//                                    sb.append("\n");
//                                    sb.append("\n");
//                                    sb.append("    @Id");
//                                    sb.append("\n");
//                                    generator = ", generator=\"" + eElementCol.getAttribute("generator") + "\"";
//                                }
//                                if(eElementCol.getAttribute("generation-type").equalsIgnoreCase("sequence")) {
//
//                                }
//
//                                if(eElementCol.getAttribute("alias-dbname").length() > 0) {
//                                    sb.append("    @Column(name=\"" + eElementCol.getAttribute("alias-dbname") + "\")");
//                                    sb.append("\n");
//                                }
//
//                                if(eElementCol.getAttribute("alias-dbname").length() < 1) {
//                                    sb.append("    @Column(name=\"" + eElementCol.getAttribute("name") + "\")");
//                                    sb.append("\n");
//                                }
//                                sb.append("    @GeneratedValue(strategy = GenerationType." + generationType + generator + ")");
//                                sb.append("\n");
//                            }
//                        } else {
//                            if(nHintList.getLength() > 0) {
//                                for (int hi=0; hi<nHintList.getLength(); hi++) {
//
//                                    Node nNodeHint = nHintList.item(hi);
//                                    if (nNodeHint.getNodeType() == Node.ELEMENT_NODE) {
//                                        Element eElementHint = (Element) nNodeHint;
//
//                                        if(eElementCol.getAttribute("name").equalsIgnoreCase(getParent(eElementHint).getAttribute("name"))) {
//                                            sb.append("\n");
//                                            sb.append("    @Column(name=\"" + eElementCol.getAttribute("name")
//                                                    + "\", nullable=" + eElementHint.getAttribute("nullable")
//                                                    + ", length=" + eElementHint.getAttribute("length") + ")");
//                                            sb.append("\n");
//                                        } else {
//                                            sb.append("\n");
//                                            sb.append("    @Column(name=\"" + eElementCol.getAttribute("name") + "\")");
//                                            sb.append("\n");
//                                        }
//                                    }
//                                }
//                            } else {
//                                sb.append("\n");
//                                sb.append("    @Column(name=\"" + eElementCol.getAttribute("name") + "\")");
//                                sb.append("\n");
//                            }
//                            if (eElementCol.getAttribute("format").length() > 0) {
//                                sb.append("    @JsonFormat(pattern=\""+eElementCol.getAttribute("format")+"\")");
//                                sb.append("\n");
//                            }
//                        }
//
//                        if(eElementCol.getAttribute("type").equalsIgnoreCase("Date")) {
//                            sbimp.append("import java.util.Date;");
//                            sbimp.append("\n");
//                            sbimp.append("import com.fasterxml.jackson.annotation.JsonFormat;");
//                            sbimp.append("\n");
//                            sbimp.append("\n");
//                        }
//                        sb.append("    private "+ eElementCol.getAttribute("type")+" "+eElementCol.getAttribute("name")+";");
//                        sb.append("\n");
//
//                    }
//                }
        }

        ts.append("                 '}';").append("\n");
        ts.append("    }").append("\n");
        sb.append(sg.toString());
        sb.append("\n");
        sb.append(ts.toString());
        sb.append("} ");
        return sbimp.toString() + sb.toString();
    }

    public static File className(String packages, String fileName) throws IOException {
        String locationFile = packages.replaceAll("\\.", "\\/") + "/" + fileName;
        String path = Paths.get("").toAbsolutePath().toString() + "\\src\\main\\java\\" + locationFile;
//        String path = "C:\\ProjekLaporan\\" + fileName;
        System.out.println("Location File : " + path);
        File file = new File(path);
        if (file.createNewFile()) {
            System.out.println("File is created!");
        } else {
            System.out.println("File already exists.");
        }
        return file;
    }

    public static void createJavaFile(String data, File file) throws IOException {
        FileOutputStream out = new FileOutputStream(file);
        out.write(data.getBytes());
        out.close();
    }

    public static void generateEntity(Document doc) throws IOException {
        String entityPackage = getRootElement(doc) + ".entity";
        List<Node> childNodes = getChildNodes(doc.getElementsByTagName("service").item(0), "entity");
        for (Node item : childNodes) {
            Element el = (Element) item;
            String data = createEntity(getRootElement(doc), item);
            createJavaFile(data, className(entityPackage, el.getAttribute("name") + ".java"));
        }
    }

    public static Node getChild(Node node, String tag) {
        NodeList nodes = node.getChildNodes();
        System.out.println("Node length : " + nodes.getLength());
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            System.out.println("Item node name " + item.getNodeName());
            if (item.getNodeName().equals(tag))
                return item;
        }
        return null;
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

    public static String writeGetter(String name, String getterName,
                                     String type, String access) {

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("    " + access + " " + type + " get" + getterName + "() { ");
        sb.append("\n");
        sb.append("        return " + name + ";");
        sb.append("\n");
        sb.append("    }");
        sb.append("\n");

        return sb.toString();
    }

    public static String writeSetter(String name, String setterName,
                                     String type, String access) {

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("    " + access + " void set" + setterName + "(" + type + " " + name + ") {");
        sb.append("\n");
        sb.append("        this." + name + " = " + name + ";");
        sb.append("\n");
        sb.append("    }");
        sb.append("\n");

        return sb.toString();
    }

    public static String cap(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String lop(String s) {
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

}
