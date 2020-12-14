package ru.vapima.butjet.butJet3.dto.acc;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccDtoAdd {
    @Size(min=2, max=50, message = "Name from 2 to 50 characters")
    @NotBlank(message = "Please enter a name")
    @ApiModelProperty(example = "Left pocket")
    private String name;
    @NotNull(message = "Please enter balance start balance")
    private Integer balance;
    @NotNull(message = "Please enter isActive")
    private Boolean active;
}