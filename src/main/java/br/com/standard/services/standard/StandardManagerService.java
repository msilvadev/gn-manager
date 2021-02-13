package br.com.standard.services.standard;

import br.com.standard.domains.standard.StandardDto;

import java.util.List;

public interface StandardManagerService {

    List<StandardDto> listAllStandard();

    StandardDto saveStandard(StandardDto standardDto);

    List<StandardDto> saveStandard(List<StandardDto> standardDtoList);

}
