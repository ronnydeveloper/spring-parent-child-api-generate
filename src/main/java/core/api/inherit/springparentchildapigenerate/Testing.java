package core.api.inherit.springparentchildapigenerate;

import java.math.BigDecimal;

public class Testing {

    public static void main(String[] args) {
        String a = "236712839892334";
        String b = "71";

        Double c = Double.parseDouble(a);
        Integer d = Integer.parseInt(b);
        Double e = (c * d) + 39.05;
        String f = Double.toString(e);
        Long h = 16806611632355714L;
        Double g = h + 39.05;
        System.out.println("Nilai a = " + a);
        System.out.println("Nilai b = " + b);
        System.out.println("Nilai c = " + c);
        System.out.println("Nilai d = " + d);
        System.out.println("Hasil a * b = " + f);
        System.out.println("Nilai g = " + g);

    }
}
