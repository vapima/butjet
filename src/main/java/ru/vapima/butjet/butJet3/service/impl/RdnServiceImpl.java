package ru.vapima.butjet.butJet3.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoResponse;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoAdd;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoResponse;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoUpdate;
import ru.vapima.butjet.butJet3.dto.rdn.RdnDtoResponse;
import ru.vapima.butjet.butJet3.model.Acc;
import ru.vapima.butjet.butJet3.model.Person;
import ru.vapima.butjet.butJet3.model.Plan;
import ru.vapima.butjet.butJet3.repositories.PersonRepository;
import ru.vapima.butjet.butJet3.repositories.PlanRepository;
import ru.vapima.butjet.butJet3.service.AccService;
import ru.vapima.butjet.butJet3.service.PlanService;
import ru.vapima.butjet.butJet3.service.RdnService;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RdnServiceImpl implements RdnService {
    @Autowired
    private AccService accService;
    @Autowired
    private PlanService planService;

    private Integer totalAcc(List<AccDtoResponse> accs) {
        Integer total = 0;
        for (AccDtoResponse a : accs) {
            total += a.getBalance();
        }
        return total;
    }

    private Integer totalAccActive(List<AccDtoResponse> accs) {
        Integer total = 0;
        for (AccDtoResponse a : accs) {
            if (a.getActive()) {
                total += a.getBalance();
            }
        }
        return total;
    }

    private Integer totalPlan(List<PlanDtoResponse> plans) {
        Integer totalPlans = 0;
        for (PlanDtoResponse p : plans) {
            totalPlans += p.getBalance();
        }
        return totalPlans;
    }

    public Integer daysLeftMonth() {
        return Period.between(LocalDate.now(), LocalDate.now().plusMonths(1).withDayOfMonth(1)).getDays();
    }

    public Integer getRdn(List<PlanDtoResponse> planList,List<AccDtoResponse> accList) {
        Integer rdn=(totalAccActive(accList) + getAllPlansInThisMounth(planList)) / daysLeftMonth();
        return rdn;
    }

    public Integer getAllPlansInThisMounth(List<PlanDtoResponse> planList){
        return planList.stream()
                .filter(p->p.getDateExpiration().isBefore(LocalDate.now().plusMonths(1).withDayOfMonth(1)))
                .mapToInt(PlanDtoResponse::getBalance)
                .sum();
    }

    @Override
    public RdnDtoResponse calculateRdn(Long idPerson) {
        List<PlanDtoResponse> planList=planService.getAll(idPerson);
        List<AccDtoResponse> accList=accService.getAll(idPerson);
        RdnDtoResponse rdnDtoResponse= RdnDtoResponse.builder()
                .rdn(getRdn(planList,accList))
                .allAccounts(totalAcc(accList))
                .allPlans(totalPlan(planList))
                .allActiveAccounts(totalAccActive(accList))
                .allPlansInThisMounth(getAllPlansInThisMounth(planList))
                .build();
        return rdnDtoResponse;
    }
}
