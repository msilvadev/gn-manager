package br.com.standard.domains.standard;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "STANDARD")
public class Standard implements Serializable {

    private static final long serialVersionUID = 7434897361855297228L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long number;

    @Enumerated(EnumType.STRING)
    @Column(name = "STANDARD_TYPE")
    private StandardType standardType;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "START_DATE", columnDefinition = "TIMESTAMP")
    private LocalDateTime start;

    @Column(name = "END_DATE_TIME", columnDefinition = "TIMESTAMP")
    private LocalDateTime end;

    public Standard(long number, StandardType standardType,
                    String description, LocalDateTime start) {
        this.number = number;
        this.standardType = standardType;
        this.description = description;
        this.start = start;
    }

    public Standard() {
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public StandardType getAssistanceType() {
        return standardType;
    }

    public void setAssistanceType(StandardType standardType) {
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
    public String toString() {
        return "Assistance{" +
                "number=" + number +
                ", assistanceType=" + standardType +
                ", description='" + description + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
