package ru.vapima.butjet.butJet3.controllers.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.vapima.butjet.butJet3.dto.person.PersonDtoAdd;
import ru.vapima.butjet.butJet3.dto.person.PersonDtoResponse;
import ru.vapima.butjet.butJet3.dto.person.PersonDtoUpdate;

import java.util.List;

@Api(tags = "Persons API")
public interface PersonController {

    @ApiOperation("Add new person")
    public PersonDtoResponse save(@RequestBody @Validated PersonDtoAdd person);

    @ApiOperation("Find person by Id")
    public PersonDtoResponse findById(@PathVariable("id") Long id);

    @ApiOperation("Delete person based on primary key")
    public void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all persons")
    public List<PersonDtoResponse> list();

    @ApiOperation("Update one person")
    public PersonDtoResponse update(@RequestBody @Validated PersonDtoUpdate dto, @PathVariable("id") Long id);
}