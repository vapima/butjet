package ru.vapima.butjet.butJet3.service;

import ru.vapima.butjet.butJet3.dto.plan.PlanDtoAdd;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoResponse;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoUpdate;

import java.util.List;

public interface PlanService {
   void deleteById(Long id,Long idPerson);
   PlanDtoResponse getById(Long id, Long idPerson);
   List<PlanDtoResponse> getAll(Long idPerson);
   PlanDtoResponse addPlan(PlanDtoAdd planDto, Long idPerson);
   PlanDtoResponse updatePlan(PlanDtoUpdate planDTO, Long id, Long idPerson);
}
