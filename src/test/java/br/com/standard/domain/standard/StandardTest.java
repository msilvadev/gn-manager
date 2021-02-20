package br.com.standard.domain.standard;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StandardTest {

    @Test
    void constructor() {
        Standard standard = new Standard();
        assertThat(standard instanceof Standard).isTrue();
    }

    @Test
    void getEnd() {
        StandardBuilder builder = new StandardBuilder();
        Standard build = builder
                .withDescription("Test")
                .build();

        LocalDateTime end = LocalDateTime.now();
        build.setEnd(end);

        assertThat(build.getEnd()).isEqualTo(end);
    }

    @Test
    void industrialProcessToString() {
        StandardBuilder builder = new StandardBuilder();
        Standard build = builder
                .withDescription("Test")
                .build();

        assertThat(build.toString()).isNotBlank();
    }
}