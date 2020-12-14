package ru.vapima.butjet.butJet3.dto.acc;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccDtoUpdate {
    @Size(min=2, max=50, message = "Name from 2 to 50 characters")
    @ApiModelProperty(example = "To pay the bill for jumping")
    private String name;
    private Boolean active;
}