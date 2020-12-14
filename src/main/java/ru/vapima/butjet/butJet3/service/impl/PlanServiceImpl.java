package ru.vapima.butjet.butJet3.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoAdd;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoResponse;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoUpdate;
import ru.vapima.butjet.butJet3.model.Person;
import ru.vapima.butjet.butJet3.model.Plan;
import ru.vapima.butjet.butJet3.repositories.PersonRepository;
import ru.vapima.butjet.butJet3.repositories.PlanRepository;
import ru.vapima.butjet.butJet3.service.PlanService;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void deleteById(Long id,Long idPerson) {
        Plan plan=planRepository.getOne(id);
        if(!plan.getPerson().getId().equals(idPerson)){throw new IllegalArgumentException("Do not delete not your Plan.");}
        planRepository.deleteById(id);
    }

    @Override
    public PlanDtoResponse getById(Long id, Long idPerson) {
        Plan plan=planRepository.getOne(id);
        if(!plan.getPerson().getId().equals(idPerson)){throw new IllegalArgumentException("not your Plan.");}
        return modelMapper.map(plan, PlanDtoResponse.class);
    }

    @Override
    public List<PlanDtoResponse> getAll(Long idPerson) {
        List<Plan> planList = planRepository.findAllByPerson_id(idPerson);
        List<PlanDtoResponse> dtoList = planList
                .stream()
                .map(i->modelMapper.map(i, PlanDtoResponse.class)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public PlanDtoResponse addPlan(PlanDtoAdd planDTO, Long idPerson) {
        Plan plan=modelMapper.map(planDTO,Plan.class);
        Person person =personRepository.getOne(idPerson);
        plan.setPerson(person);
        return modelMapper.map(planRepository.save(plan), PlanDtoResponse.class);
    }

    @Override
    public PlanDtoResponse updatePlan(PlanDtoUpdate planDTO, Long id, Long idPerson) {
        Plan plan=planRepository.findById(id).get();
        if(!plan.getPerson().getId().equals(idPerson)){throw new IllegalArgumentException("not your Plan.");}
        modelMapper.map(planDTO,plan);
        return modelMapper.map(planRepository.save(plan), PlanDtoResponse.class);
    }
}
