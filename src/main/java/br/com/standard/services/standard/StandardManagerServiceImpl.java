package br.com.standard.services.standard;

import br.com.standard.domains.standard.Standard;
import br.com.standard.domains.standard.StandardDto;
import br.com.standard.domains.standard.StandardDtoBuilder;
import br.com.standard.domains.standard.StandardRepository;
import br.com.standard.services.dashboard.DashboardReportService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StandardManagerServiceImpl implements StandardManagerService {

    private final StandardRepository repository;
    private final DashboardReportService dashboardReportService;

    public StandardManagerServiceImpl(StandardRepository repository,
                                      DashboardReportService dashboardReportService) {
        this.repository = repository;
        this.dashboardReportService = dashboardReportService;
    }

    @Override
    public List<StandardDto> listAllStandard() {
        List<Standard> all = repository.findAll();

        return all.stream()
                .map(StandardDtoBuilder::build)
                .collect(Collectors.toList());
    }

    @Override
    public synchronized StandardDto saveStandard(StandardDto standardDto) {
        StandardDto saved = StandardDtoBuilder.build(repository.save(StandardDtoBuilder
                .buildDtoToIndustrialProcess(standardDto)));

        dashboardReportService.updateCacheDashboarReport(saved);

        return saved;
    }

    @Override
    @Transactional
    public synchronized List<StandardDto> saveStandard(List<StandardDto> standardDtoList) {
        List<Standard> industrialProcessesSaved = repository.saveAll(standardDtoList.stream()
                .map(StandardDtoBuilder::buildDtoToIndustrialProcess)
                .collect(Collectors.toList()));

        return industrialProcessesSaved.stream()
                .map(StandardDtoBuilder::build)
                .collect(Collectors.toList());
    }

}
