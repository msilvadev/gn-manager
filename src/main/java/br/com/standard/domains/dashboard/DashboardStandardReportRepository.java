package br.com.standard.domains.dashboard;

import br.com.standard.domains.standard.StandardDto;
import br.com.standard.domains.standard.StandardType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

@Component
public class DashboardStandardReportRepository {

    private final ConcurrentHashMap<Integer, DashboardStandardReportDto> dashboardCache = new ConcurrentHashMap<>();

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

    public void add(StandardType standardType, DashboardStandardReportDto newReport) {
        DashboardStandardReportDto dashboardReportFound = this.getByProcessType(standardType);

        if (Objects.nonNull(dashboardReportFound)) {
            incrementQuantityToProcessStatus
                    .forEach((integer, dashboardReportDtoConsumer) ->
                            dashboardReportDtoConsumer.accept(dashboardReportFound));
        } else {
            dashboardCache.put(standardType.getCode(), newReport);
        }
    }

    public ConcurrentMap<Integer, DashboardStandardReportDto> getAllDashboardReports() {
        return dashboardCache;
    }

    public DashboardStandardReportDto getByProcessType(StandardType standardType) {
        return dashboardCache.get(standardType.getCode());
    }

    public void update(StandardDto standardDto) {
        DashboardStandardReportDto dashboardReportFound =
                this.getByProcessType(StandardType.valueOfCode(standardDto.getStandardType()));

        Integer assistanceType = Integer.valueOf(standardDto.getStandardType());

        if (Objects.nonNull(dashboardReportFound)) {
            incrementQuantityToProcessStatus.get(assistanceType).accept(dashboardReportFound);
        } else {
            DashboardStandardReportDto dashboardStandardReportDto = new DashboardStandardReportDto();

            dashboardCache.put(StandardType.valueOfCode(standardDto.getStandardType()).getCode(),
                    dashboardStandardReportDto);
        }
    }

    public int size() {
        return dashboardCache.size();
    }

    public void clearCache() {
        this.dashboardCache.clear();
    }

}
