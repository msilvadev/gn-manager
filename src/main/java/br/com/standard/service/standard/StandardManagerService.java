package br.com.standard.service.standard;

import br.com.standard.domain.standard.StandardDto;

import java.util.List;

public interface StandardManagerService {

    List<StandardDto> listAllStandard();

    StandardDto saveStandard(StandardDto standardDto);

    List<StandardDto> saveStandard(List<StandardDto> standardDtoList);

}
