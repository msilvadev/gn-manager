package br.com.standard.domain.dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DashboardStandardReportDtoTest {

    private DashboardStandardReportDto dashboardStandardReportDto;

    @BeforeEach
    void setUp() {
        dashboardStandardReportDto = new DashboardStandardReportDto(1, 1, 1);
    }

    @Test
    void getIndustrialQuantity() {
        assertThat(dashboardStandardReportDto.getIndustrialQuantity()).isEqualTo(1);
    }

    @Test
    void getEnvironmentalQuantity() {
        assertThat(dashboardStandardReportDto.getEnvironmentalQuantity()).isEqualTo(1);
    }

    @Test
    void getDefaultQuantity() {
        assertThat(dashboardStandardReportDto.getDefaultQuantity()).isEqualTo(1);
    }
}