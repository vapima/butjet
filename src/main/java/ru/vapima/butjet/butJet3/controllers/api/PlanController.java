package ru.vapima.butjet.butJet3.controllers.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoAdd;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoResponse;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoUpdate;

import java.util.List;

@Api(tags = "Plans API")
public interface PlanController {

    @ApiOperation("Add new plan")
    public PlanDtoResponse save(@RequestBody @Validated PlanDtoAdd planDTO, Long idPerson);

    @ApiOperation("Find plan by Id")
    public PlanDtoResponse findById(@PathVariable("id") Long id, Long idPerson);

    @ApiOperation("Delete plan on primary key")
    public void delete(@PathVariable("id") Long id,Long idPerson);

    @ApiOperation("Find all plans")
    public List<PlanDtoResponse> list(Long idPerson);

    @ApiOperation("Update plan")
    public PlanDtoResponse update(@RequestBody @Validated PlanDtoUpdate dto, @PathVariable("id") Long id, Long idPerson);

}
