package br.com.standard.service;

import br.com.standard.domain.standard.*;
import br.com.standard.service.dashboard.DashboardReportService;
import br.com.standard.service.standard.StandardManagerService;
import br.com.standard.service.standard.StandardManagerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class StandardManagerServiceImplTest {

    @InjectMocks
    private StandardManagerService service;

    private StandardDto dto;
    private Standard process;
    private final LocalDateTime start = LocalDateTime.now();
    private final List<StandardDto> listToSave = new ArrayList<>();
    private final List<Standard> listSaved = new ArrayList<>();

    @BeforeEach
    void setUp() {
        StandardBuilder builder = new StandardBuilder();
        process = builder.withAssistanceType(StandardType.DEFAULT).withDescription("Test").build();
        listSaved.add(process);

        dto = new StandardDto(0, 3, "Test", start, null);
        listToSave.add(dto);

        StandardRepository repository = Mockito.mock(StandardRepository.class);
        DashboardReportService dashboardReportService = Mockito.mock(DashboardReportService.class);

        service = new StandardManagerServiceImpl(repository, dashboardReportService);

        when(repository.save(Mockito.any(Standard.class))).thenReturn(process);
        when(repository.saveAll(Mockito.any(Iterable.class))).thenReturn(listSaved);
        when(repository.findAll()).thenReturn(listSaved);
    }

    @Test
    void listAllProcesses() {
        List<StandardDto> result = service.listAllStandard();

        assertThat(result.size()).isEqualTo(listToSave.size());
        assertThat(result.get(0).getNumber()).isEqualTo(listToSave.get(0).getNumber());
    }

    @Test
    void saveIndustrialProcess() {
        StandardDto result = service.saveStandard(dto);

        assertThat(result.getNumber()).isEqualTo(process.getNumber());
        assertThat(result.getStandardType()).isEqualTo(process.getAssistanceType().getCode());
        assertThat(result.getDescription()).isEqualTo(process.getDescription());
        assertThat(result.getStart()).isEqualTo(process.getStart());
    }

    @Test
    void testSaveIndustrialProcess() {
        List<StandardDto> result = service.saveStandard(listToSave);

        assertThat(result.size()).isEqualTo(listToSave.size());
        assertThat(result.get(0).getNumber()).isEqualTo(listToSave.get(0).getNumber());
    }
}