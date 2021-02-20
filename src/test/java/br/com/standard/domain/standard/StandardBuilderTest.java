package br.com.standard.domain.standard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class StandardBuilderTest {

    private StandardBuilder builder;
    private final LocalDateTime start = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        builder = new StandardBuilder();
    }

    @Test
    void withNumber() {
        Standard build = builder.withNumber(1).build();
        assertThat(build.getNumber()).isEqualTo(1);
    }

    @Test
    void withProcessType() {
        Standard build = builder.withAssistanceType(StandardType.DEFAULT).build();
        assertThat(build.getAssistanceType()).isEqualTo(StandardType.DEFAULT);
    }

    @Test
    void withDescription() {
        Standard build = builder.withDescription("Test").build();
        assertThat(build.getDescription()).isEqualTo("Test");
    }

    @Test
    void withStart() {
        Standard build = builder.withStart(this.start).build();
        assertThat(build.getStart()).isEqualTo(this.start);
    }

    @Test
    void build() {
        Standard build = builder.withNumber(1)
                .withAssistanceType(StandardType.DEFAULT)
                .withDescription("Test")
                .withStart(this.start)
                .build();

        assertThat(build).isNotNull();
        assertThat(build.getNumber()).isEqualTo(1);
        assertThat(build.getAssistanceType()).isEqualTo(StandardType.DEFAULT);
        assertThat(build.getDescription()).isEqualTo("Test");
        assertThat(build.getStart()).isEqualTo(this.start);
        assertThat(build.getEnd()).isNull();
    }

    @Test
    void shouldBeBuildWithDefaultValuesNumberProcessTypeProcessStatusAndStart() {
        Standard build = builder
                .withAssistanceType(StandardType.DEFAULT)
                .withDescription("Test")
                .build();

        assertThat(build).isNotNull();
        assertThat(build.getNumber()).isZero();
        assertThat(build.getAssistanceType()).isEqualTo(StandardType.DEFAULT);
        assertThat(build.getDescription()).isEqualTo("Test");
        assertThat(build.getStart()).isNotNull();
    }
}