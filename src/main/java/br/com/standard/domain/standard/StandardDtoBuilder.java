package br.com.standard.domain.standard;

public class StandardDtoBuilder {

    private StandardDtoBuilder(){}

    public static StandardDto build(Standard standard) {
        return new StandardDto(
                standard.getNumber(),
                standard.getAssistanceType().getCode(),
                standard.getDescription(),
                standard.getStart(),
                standard.getEnd() != null ? standard.getEnd() : null
        );
    }

    public static Standard buildDtoToIndustrialProcess(StandardDto dto) {
        StandardBuilder builder = new StandardBuilder();

        return builder
                .withNumber(dto.getNumber())
                .withDescription(dto.getDescription())
                .withAssistanceType(StandardType.valueOfCode(dto.getStandardType()))
                .build();
    }

}
