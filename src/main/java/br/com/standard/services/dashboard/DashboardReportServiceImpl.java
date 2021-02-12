package br.com.standard.services.dashboard;

import br.com.standard.domains.dashboard.DashboardStandardReportDto;
import br.com.standard.domains.dashboard.DashboardStandardReportDtoBuilder;
import br.com.standard.domains.dashboard.DashboardStandardReportRepository;
import br.com.standard.domains.standard.StandardDto;
import br.com.standard.domains.standard.StandardRepository;
import br.com.standard.domains.standard.StandardType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DashboardReportServiceImpl implements DashboardReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardReportServiceImpl.class);

    private final StandardRepository standardRepository;
    private final DashboardStandardReportRepository dashboardStandardReportRepository;

    private final List<Runnable> tasks;

    public DashboardReportServiceImpl(StandardRepository standardRepository,
                                      DashboardStandardReportRepository dashboardStandardReportRepository) {
        this.standardRepository = standardRepository;
        this.dashboardStandardReportRepository = dashboardStandardReportRepository;

        tasks = Collections.singletonList(() -> {
            this.loadStandardTypeIndustrialDashboardReport();
            this.loadStandardTypeEnvironmentalDashboardReport();
        });
    }

    @Override
    public void initializeCache() {
        LOGGER.info("Loading Dashboard Report Cache...");

        long start = System.currentTimeMillis();

        this.dashboardStandardReportRepository.clearCache();

        this.executeAllTasks(tasks).join();

        LOGGER.info("Dashboard Report Cache loaded in {} ms", System.currentTimeMillis() - start);

        LOGGER.info("Load {} Dashboard Report Cache", this.dashboardStandardReportRepository.size());
    }

    @Override
    public void updateCacheDashboarReport(StandardDto process) {
        dashboardStandardReportRepository.update(process);
    }

    @Override
    public ConcurrentMap<Integer, DashboardStandardReportDto> getDashboardReport() {
        return dashboardStandardReportRepository.getAllDashboardReports();
    }

    public void loadStandardTypeIndustrialDashboardReport() {
        addDashboardReportToCache(StandardType.INDUSTRIAL);
    }

    public void loadStandardTypeEnvironmentalDashboardReport() {
        addDashboardReportToCache(StandardType.ENVIRONMENTAL);
    }

    private void addDashboardReportToCache(StandardType standardType) {
        DashboardStandardReportDtoBuilder builder = new DashboardStandardReportDtoBuilder();

        DashboardStandardReportDto report = builder
                .setAdvisoryQuantity(standardRepository.countByProcessTypeAndProcessStatus(StandardType.INDUSTRIAL))
                .setConsultancyQuantity(standardRepository.countByProcessTypeAndProcessStatus(StandardType.ENVIRONMENTAL))
                .setDefaultQuantity(standardRepository.countByProcessTypeAndProcessStatus(StandardType.DEFAULT))
                .build();

        this.dashboardStandardReportRepository.add(standardType, report);
    }

    private CompletableFuture<Void> executeAllTasks(List<Runnable> tasks) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        return CompletableFuture.allOf(tasks.stream()
                .map(task -> CompletableFuture.runAsync(task, executor)).toArray(CompletableFuture[]::new));
    }

}
