package br.com.standard.domain.standard;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StandardDto implements Serializable {

    private static final long serialVersionUID = 3263614731673211493L;

    private long number;
    private int standardType;
    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime start;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime end;

    public StandardDto(long number, int standardType, String description,
                       LocalDateTime start, LocalDateTime end) {
        this.number = number;
        this.standardType = standardType;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public StandardDto() { }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getStandardType() {
        return standardType;
    }

    public void setStandardType(int standardType) {
        this.standardType = standardType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandardDto that = (StandardDto) o;
        return number == that.number && standardType == that.standardType && Objects.equals(description, that.description) && start.equals(that.start) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, standardType, description, start, end);
    }

    @Override
    public String toString() {
        return "AssistanceDto{" +
                "number=" + number +
                ", assistanceType=" + standardType +
                ", description='" + description + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
