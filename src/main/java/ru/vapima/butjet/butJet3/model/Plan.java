package ru.vapima.butjet.butJet3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Integer balance;
    private LocalDate dateExpiration;
    @ManyToOne//(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name="person_id", nullable=false)
    private Person person;
}
