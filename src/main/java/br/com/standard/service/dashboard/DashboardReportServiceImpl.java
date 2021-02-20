package br.com.standard.service.dashboard;

import br.com.standard.domain.dashboard.DashboardStandardReportDto;
import br.com.standard.domain.dashboard.DashboardStandardReportDtoBuilder;
import br.com.standard.domain.dashboard.DashboardStandardReportRepository;
import br.com.standard.domain.standard.StandardDto;
import br.com.standard.domain.standard.StandardRepository;
import br.com.standard.domain.standard.StandardType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DashboardReportServiceImpl implements DashboardReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardReportServiceImpl.class);

    private final StandardRepository standardRepository;
    private final DashboardStandardReportRepository dashboardStandardReportRepository;

    public DashboardReportServiceImpl(StandardRepository standardRepository,
                                      DashboardStandardReportRepository dashboardStandardReportRepository) {
        this.standardRepository = standardRepository;
        this.dashboardStandardReportRepository = dashboardStandardReportRepository;
    }

    @Override
    public void initializeCache() {
        LOGGER.info("Loading Dashboard Report Cache...");

        long start = System.currentTimeMillis();

        this.dashboardStandardReportRepository.clearDashboard();

        this.addDashboardReportToCache();

        LOGGER.info("Dashboard Report Cache loaded in {} ms", System.currentTimeMillis() - start);
    }

    @Override
    public void updateCacheDashboarReport(StandardDto process) {
        dashboardStandardReportRepository.update(process);
    }

    @Override
    public DashboardStandardReportDto getDashboardReport() {
        return dashboardStandardReportRepository.getAllDashboardReports();
    }

    private void addDashboardReportToCache() {
        DashboardStandardReportDtoBuilder builder = new DashboardStandardReportDtoBuilder();

        DashboardStandardReportDto report = builder
                .setAdvisoryQuantity(standardRepository.countByProcessTypeAndProcessStatus(StandardType.INDUSTRIAL))
                .setConsultancyQuantity(standardRepository.countByProcessTypeAndProcessStatus(StandardType.ENVIRONMENTAL))
                .setDefaultQuantity(standardRepository.countByProcessTypeAndProcessStatus(StandardType.DEFAULT))
                .build();

        this.dashboardStandardReportRepository.add(report);
    }
}
