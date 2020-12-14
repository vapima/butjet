package ru.vapima.butjet.butJet3.controllers.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoAdd;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoResponse;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoUpdate;
import ru.vapima.butjet.butJet3.dto.rdn.RdnDtoResponse;

import java.util.List;

@Api(tags = "Rdn API", description = "Calculation of the daily rate for a month")
public interface RdnController {

    @ApiOperation("Get Rdn Calculate")
    public RdnDtoResponse get(Long idPerson);

}
