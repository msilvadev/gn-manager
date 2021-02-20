package br.com.standard.domain.standard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class StandardDtoTest {

    private StandardDto dto;
    private final LocalDateTime localDateTime = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        dto = new StandardDto(1, 3, "Test",
                localDateTime, localDateTime);
    }

    @Test
    void getNumber() {
        assertThat(dto.getNumber()).isEqualTo(1);
    }

    @Test
    void setNumber() {
        dto.setNumber(12);
        assertThat(dto.getNumber()).isEqualTo(12);
    }

    @Test
    void getStandardType() {
        assertThat(dto.getStandardType()).isEqualTo(StandardType.DEFAULT.getCode());
    }

    @Test
    void setStandardType() {
        dto.setStandardType(StandardType.INDUSTRIAL.getCode());
        assertThat(dto.getStandardType()).isEqualTo(StandardType.INDUSTRIAL.getCode());
    }

    @Test
    void getDescription() {
        assertThat(dto.getDescription()).isEqualTo("Test");
    }

    @Test
    void setDescription() {
        dto.setDescription("Mock");
        assertThat(dto.getDescription()).isEqualTo("Mock");
    }

    @Test
    void getStart() {
        assertThat(dto.getStart()).isEqualTo(this.localDateTime);
    }

    @Test
    void setStart() {
        dto.setStart(this.localDateTime);
        assertThat(dto.getStart()).isEqualTo(this.localDateTime);
    }

    @Test
    void getEnd() {
        assertThat(dto.getEnd()).isEqualTo(this.localDateTime);
    }

    @Test
    void setEnd() {
        dto.setEnd(this.localDateTime);
        assertThat(dto.getEnd()).isEqualTo(this.localDateTime);
    }

    @Test
    void testEquals() {
        StandardDto dtoToEqual = new StandardDto(1, 3, "Test",
                localDateTime, localDateTime);

        assertThat(dto.equals(dtoToEqual)).isTrue();
    }

    @Test
    void testHashCode() {
        StandardDto dtoToHash = new StandardDto(1, 3, "Test",
                localDateTime, localDateTime);

        assertThat(dto.hashCode() == dtoToHash.hashCode()).isTrue();
    }
}