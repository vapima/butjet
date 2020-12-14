package ru.vapima.butjet.butJet3.controllers.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoAdd;
import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoResponse;
import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoUpdate;

import java.util.List;

@Api(tags = "Account Records API")
public interface AccRecController {

    @ApiOperation("Add new account record")
    public AccRecDtoResponse save(@RequestBody @Validated AccRecDtoAdd accRecDTO, @PathVariable("id_acc") Long idAcc, @PathVariable("id_person") Long idPerson);

    @ApiOperation("Find account record by Id")
    public AccRecDtoResponse findById(@PathVariable("id") Long id, @PathVariable("id_acc") Long idAcc, @PathVariable("id_person") Long idPerson);

    @ApiOperation("Delete account record on primary key")
    public void delete(@PathVariable("id") Long id, @PathVariable("id_acc") Long idAcc, @PathVariable("id_person") Long idPerson);

    @ApiOperation("Find all account records")
    public List<AccRecDtoResponse> list(@PathVariable("id_acc") Long idAcc, @PathVariable("id_person") Long idPerson);

    @ApiOperation("Update account record")
    public AccRecDtoResponse update(@RequestBody AccRecDtoUpdate accRecDTO, @PathVariable("id") Long id, @PathVariable("id_acc") Long idAcc, @PathVariable("id_person") Long idPerson);

}
