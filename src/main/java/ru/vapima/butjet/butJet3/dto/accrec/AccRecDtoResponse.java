package ru.vapima.butjet.butJet3.dto.accrec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccRecDtoResponse {
    private Long id;
    private Integer balance;
    private LocalDateTime changeTime;
}