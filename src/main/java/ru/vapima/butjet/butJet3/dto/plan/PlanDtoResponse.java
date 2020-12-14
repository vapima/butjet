package ru.vapima.butjet.butJet3.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PlanDtoResponse {
    private Long id;
    private String name;
    private Integer balance;
    private LocalDate dateExpiration;
}