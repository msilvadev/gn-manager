package br.com.standard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StandardManagerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(StandardManagerApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Initializing StandardManagerApplication Application...");

        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.LOG)
                .sources(StandardManagerApplication.class)
                .run(args);

        LOGGER.info("StandardManagerApplication has completed startup");
    }

}
