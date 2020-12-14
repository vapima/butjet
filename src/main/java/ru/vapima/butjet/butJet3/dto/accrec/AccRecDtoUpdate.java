package ru.vapima.butjet.butJet3.dto.accrec;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccRecDtoUpdate {
    private Integer balance;
    @ApiModelProperty(example = "2020-11-25T20:23:53")
    private LocalDateTime changeTime;
}