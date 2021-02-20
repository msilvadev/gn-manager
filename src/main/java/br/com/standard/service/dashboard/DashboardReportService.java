package br.com.standard.service.dashboard;

import br.com.standard.domain.dashboard.DashboardStandardReportDto;
import br.com.standard.domain.standard.StandardDto;

public interface DashboardReportService {

    void initializeCache();

    void updateCacheDashboarReport(StandardDto process);

    DashboardStandardReportDto getDashboardReport();
}
