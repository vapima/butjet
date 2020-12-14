package ru.vapima.butjet.butJet3.dto.person;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDtoUpdate {
    @ApiModelProperty(example = "Vasiliy")
    @Size(min=2, max=50, message = "Name from 2 to 50 characters")
    private String name;
    @Email
    @ApiModelProperty(example = "vasya@pupkin-citygrad.cc")
    @Size(min=2, max=50, message = "Name from 2 to 50 characters")
    private String mail;
    @ApiModelProperty(example = "Password123")
    @Size(min=6, max=20, message = "Name from 6 to 20 characters")
    private String hashPassword;
}
