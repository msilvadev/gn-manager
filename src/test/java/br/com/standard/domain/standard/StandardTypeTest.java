package br.com.standard.domain.standard;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StandardTypeTest {

    @Test
    void valueOfCode() {
        assertThat(StandardType.valueOfCode(1).getDescription()).isEqualTo(StandardType.INDUSTRIAL.getDescription());
        assertThat(StandardType.valueOfCode(2).getDescription()).isEqualTo(StandardType.ENVIRONMENTAL.getDescription());
        assertThat(StandardType.valueOfCode(3).getDescription()).isEqualTo(StandardType.DEFAULT.getDescription());
    }

    @Test
    void values() {
        assertThat(StandardType.values()).isNotNull();
    }

    @Test
    void valueOf() {
        assertThat(StandardType.valueOf("INDUSTRIAL")).isEqualTo(StandardType.INDUSTRIAL);
        assertThat(StandardType.valueOf("ENVIRONMENTAL")).isEqualTo(StandardType.ENVIRONMENTAL);
        assertThat(StandardType.valueOf("DEFAULT")).isEqualTo(StandardType.DEFAULT);
    }
}