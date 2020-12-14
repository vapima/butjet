package ru.vapima.butjet.butJet3.dto.plan;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PlanDtoAdd {
    @Size(min=2, max=50, message = "Name from 2 to 50 characters")
    @NotBlank(message = "Please enter a name")
    @ApiModelProperty(example = "To pay the bill for jumping")
    private String name;
    @NotNull(message = "Enter balance of your plan")
    private Integer balance;
    @Future
    @NotNull(message = "Enter date expirance of your plan")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateExpiration;
}