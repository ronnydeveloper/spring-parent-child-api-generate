package sys.util;

public class GenerateSetterGetter {

    public static String writeGetter(String name, String getterName,
                                    String type, String access) {

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("    " + access + " " + type + " get" + getterName + "() { ");
        sb.append("\n");
        sb.append("    return " + name + ";");
        sb.append("\n");
        sb.append("}");
        sb.append("\n");

        return sb.toString();
    }

    public static String writeSetter(String name, String setterName,
                                    String type, String access) {

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("    " + access + " void " + setterName + "(" + type + " " + name + ") { ");
        sb.append("\n");
        sb.append("    this." + name + " = " + name + ";");
        sb.append("\n");
        sb.append("}");

        return sb.toString();
    }

    public static String cap(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String lop(String s) {
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

    public static void main(String[] args) {
        String getter = writeGetter(lop("ColumnString"), cap("columnString"), "String", "public");
        String setter = writeSetter(lop("ColumnString"), cap("columnString"), "String", "public");
        System.out.println("Getter : " + getter);
        System.out.println("Setter : " + setter);
    }

}
