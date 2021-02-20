package br.com.standard.controller;

import br.com.standard.domain.dashboard.DashboardStandardReportDto;
import br.com.standard.service.dashboard.DashboardReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("standard-dashboard-report")
public class DashboardReportController {

    private final DashboardReportService dashboardReportService;

    public DashboardReportController(DashboardReportService dashboardReportService) {
        this.dashboardReportService = dashboardReportService;
    }

    @GetMapping
    public ResponseEntity<DashboardStandardReportDto> getDashboardReport() {
        return ResponseEntity.ok(dashboardReportService.getDashboardReport());
    }

    @PostMapping("refresh-cache")
    public ResponseEntity<DashboardStandardReportDto> refreshCache() {
        dashboardReportService.initializeCache();
        return ResponseEntity.ok(dashboardReportService.getDashboardReport());
    }
}
