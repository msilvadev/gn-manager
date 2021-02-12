package br.com.standard.domains.standard;

import java.time.LocalDateTime;

public class StandardBuilder {

    private long number;
    private StandardType standardType;
    private String description;
    private LocalDateTime start;

    public StandardBuilder() {
        this.number = 0;
        this.standardType = StandardType.DEFAULT;
        this.start = LocalDateTime.now();
    }

    public StandardBuilder withNumber(long number) {
        this.number = number;
        return this;
    }

    public StandardBuilder withAssistanceType(StandardType standardType) {
        this.standardType = standardType;
        return this;
    }

    public StandardBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public StandardBuilder withStart(LocalDateTime start) {
        this.start = start;
        return this;
    }

    public Standard build() {
        return new Standard(
                this.number,
                this.standardType,
                this.description,
                this.start
        );
    }
}
