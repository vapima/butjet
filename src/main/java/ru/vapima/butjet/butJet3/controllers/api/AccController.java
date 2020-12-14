package ru.vapima.butjet.butJet3.controllers.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoAdd;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoResponse;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoUpdate;

import java.util.List;

@Api(tags = "Accounts API")
public interface AccController {

    @ApiOperation("Add new account")
    public AccDtoResponse save(@RequestBody @Validated AccDtoAdd accDTO, Long idPerson);

    @ApiOperation("Find account by Id")
    public AccDtoResponse findById(@PathVariable("id") Long id, Long idPerson);

    @ApiOperation("Delete account on primary key")
    public void delete(@PathVariable("id") Long id,Long idPerson);

    @ApiOperation("Find all accounts")
    public List<AccDtoResponse> list(Long idPerson);

    @ApiOperation("Update account")
    public AccDtoResponse update(@RequestBody @Validated AccDtoUpdate dto, @PathVariable("id") Long id, Long idPerson);

}
