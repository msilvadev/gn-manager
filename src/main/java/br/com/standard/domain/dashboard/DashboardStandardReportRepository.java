package br.com.standard.domain.dashboard;

import br.com.standard.domain.standard.StandardDto;
import br.com.standard.domain.standard.StandardType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Consumer;

@Component
public class DashboardStandardReportRepository {

    private final DashboardStandardReportDto dashboardCache = new DashboardStandardReportDto();

    private final Map<Integer, Consumer<DashboardStandardReportDto>> incrementQuantityToProcessStatus = new HashMap<>();

    @PostConstruct
    public void initialize() {
        incrementQuantityToProcessStatus.put(StandardType.INDUSTRIAL.getCode(),
                dashboardStandardReportDto -> dashboardStandardReportDto.setIndustrialQuantity(1));

        incrementQuantityToProcessStatus.put(StandardType.ENVIRONMENTAL.getCode(),
                dashboardStandardReportDto -> dashboardStandardReportDto.setEnvironmentalQuantity(1));

        incrementQuantityToProcessStatus.put(StandardType.DEFAULT.getCode(),
                dashboardStandardReportDto -> dashboardStandardReportDto.setDefaultQuantity(1));
    }

    public void add(DashboardStandardReportDto newReport) {
        if (Objects.nonNull(dashboardCache)) {
            if(newReport.getIndustrialQuantity() > 0){
                this.dashboardCache.setIndustrialQuantity(newReport.getIndustrialQuantity());
            }
            if(newReport.getDefaultQuantity() > 0){
                this.dashboardCache.setDefaultQuantity(newReport.getDefaultQuantity());
            }
            if(newReport.getEnvironmentalQuantity() > 0) {
                this.dashboardCache.setEnvironmentalQuantity(newReport.getEnvironmentalQuantity());
            }
        }
    }

    public DashboardStandardReportDto getAllDashboardReports() {
        return dashboardCache;
    }

    public void update(StandardDto standardDto) {
        Integer assistanceType = Integer.valueOf(standardDto.getStandardType());

        if (Objects.nonNull(dashboardCache)) {
            incrementQuantityToProcessStatus.get(assistanceType).accept(dashboardCache);
        }
    }

    public void clearDashboard(){
        this.dashboardCache.setDefaultQuantity(0);
        this.dashboardCache.setEnvironmentalQuantity(0);
        this.dashboardCache.setIndustrialQuantity(0);
    }

}
