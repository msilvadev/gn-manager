package br.com.standard.services.dashboard;

import br.com.standard.domains.dashboard.DashboardStandardReportDto;
import br.com.standard.domains.standard.StandardDto;

import java.util.concurrent.ConcurrentMap;

public interface DashboardReportService {

    void initializeCache();

    void updateCacheDashboarReport(StandardDto process);

    ConcurrentMap<Integer, DashboardStandardReportDto> getDashboardReport();
}
