package br.com.standard.service.dashboard;

import br.com.standard.domain.dashboard.DashboardStandardReportDto;
import br.com.standard.domain.standard.StandardDto;

import java.util.concurrent.ConcurrentMap;

public interface DashboardReportService {

    void initializeCache();

    void updateCacheDashboarReport(StandardDto process);

    ConcurrentMap<Integer, DashboardStandardReportDto> getDashboardReport();
}
