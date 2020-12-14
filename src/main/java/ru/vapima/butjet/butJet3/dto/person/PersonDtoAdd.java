package ru.vapima.butjet.butJet3.dto.person;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDtoAdd {
    @ApiModelProperty(example = "Vasiliy")
    @Size(min=2, max=50, message = "Name from 2 to 50 characters")
    @NotBlank(message = "Please enter a name")
    private String name;
    @Email
    @NotBlank(message = "Please enter a Email")
    @ApiModelProperty(example = "vasya@pupkin-citygrad.cc")
    @Size(min=2, max=50, message = "Email from 2 to 50 characters")
    private String mail;
    @ApiModelProperty(example = "Password123")
    @Size(min=7, max=20, message = "Password from 7 to 20 characters")
    @NotBlank(message = "Please enter your password")
    private String hashPassword;
    @ApiModelProperty(example = "Password123")
    @Size(min=7, max=20, message = "Password from 7 to 20 characters")
    @NotBlank(message = "Please enter your confirm password")
    private String passwordConfirm;
}
