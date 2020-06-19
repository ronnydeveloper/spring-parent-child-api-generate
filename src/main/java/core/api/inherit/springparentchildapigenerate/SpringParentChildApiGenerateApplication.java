package core.api.inherit.springparentchildapigenerate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sys.generator.GeneratorEntity;

@SpringBootApplication
public class SpringParentChildApiGenerateApplication {

    public static void main(String[] args) {
        GeneratorEntity.readXML();
        SpringApplication.run(SpringParentChildApiGenerateApplication.class, args);
    }

}
