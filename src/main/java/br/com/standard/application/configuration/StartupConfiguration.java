package br.com.standard.application.configuration;

import br.com.standard.domain.standard.StandardDto;
import br.com.standard.service.dashboard.DashboardReportService;
import br.com.standard.service.standard.StandardManagerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.standard.domain")
public class StartupConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupConfiguration.class);

    private final DashboardReportService dashboardReportService;

    public StartupConfiguration(DashboardReportService dashboardReportService) {
        this.dashboardReportService = dashboardReportService;
    }

    @Bean("initialLoad")
    CommandLineRunner initialLoad(StandardManagerService service) {
        return args -> {
            // read json file and write to db
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<StandardDto>> typeReference = new TypeReference<List<StandardDto>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/standards.json");
            try {
                List<StandardDto> processDtos = mapper.readValue(inputStream,typeReference);
                service.saveStandard(processDtos);

                dashboardReportService.initializeCache();

                LOGGER.info("Load json with mock data to test!");
            } catch (IOException e){
                LOGGER.info("Failed to load json data to test from /json/standards.json, {}", e.getMessage());
            }
        };
    }
}
