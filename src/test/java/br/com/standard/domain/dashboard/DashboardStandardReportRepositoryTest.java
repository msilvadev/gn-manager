package br.com.standard.domain.dashboard;

import br.com.standard.domain.standard.StandardDto;
import br.com.standard.domain.standard.StandardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DashboardStandardReportRepositoryTest {

    private DashboardStandardReportRepository repository;
    private final StandardType standardType = StandardType.DEFAULT;
    private DashboardStandardReportDto dto;
    private StandardDto standardDto;

    @BeforeEach
    void setUp() {
        repository = new DashboardStandardReportRepository();
        repository.initialize();

        DashboardStandardReportDtoBuilder builder = new DashboardStandardReportDtoBuilder();
        this.dto = builder
                .setAdvisoryQuantity(1)
                .setConsultancyQuantity(1)
                .setDefaultQuantity(1)
                .build();

        repository.add(this.dto);

        standardDto = new StandardDto(1, 3, "Test", LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void add() {
        repository.add(this.dto);

        assertThat(repository.getAllDashboardReports().getIndustrialQuantity()).isEqualTo(2);
    }

    @Test
    void getAllDashboardReports() {
        assertThat(repository.getAllDashboardReports()).isNotNull();
    }

    @Test
    void size() {
        assertThat(repository.getAllDashboardReports()).isNotNull();
    }
}