package ru.vapima.butjet.butJet3.dto.acc;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccDtoResponse {
    private Long id;
    private String name;
    private Integer balance;
    private LocalDateTime changeTime;
    private Boolean active;
}