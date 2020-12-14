package ru.vapima.butjet.butJet3.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoAdd;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoUpdate;
import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoAdd;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoResponse;
import ru.vapima.butjet.butJet3.model.Acc;
import ru.vapima.butjet.butJet3.model.Person;
import ru.vapima.butjet.butJet3.repositories.AccRecRepository;
import ru.vapima.butjet.butJet3.repositories.AccRepository;
import ru.vapima.butjet.butJet3.repositories.PersonRepository;
import ru.vapima.butjet.butJet3.service.AccRecService;
import ru.vapima.butjet.butJet3.service.AccService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccServiceImpl implements AccService {
    @Autowired
    private AccRepository accRepository;
    @Autowired
    private AccRecRepository accRecRepository;
    @Autowired
    private AccRecService accRecService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void deleteById(Long id, Long idPerson) {
        Acc acc = accRepository.getOne(id);
        if (!acc.getPerson().getId().equals(idPerson)) {
            throw new IllegalArgumentException("Do not delete not your Acc.");
        }
        accRepository.deleteById(id);
    }

    @Override
    public AccDtoResponse getById(Long id, Long idPerson) {
        Acc acc = accRepository.getOne(id);
        if (acc.getAccRecList().size() <= 0) {
            acc.setBalance(0);
            acc.setChangeTime(null);
        } else {
            acc.setBalance(acc.getAccRecList().get(acc.getAccRecList().size() - 1).getBalance());
            acc.setChangeTime(acc.getAccRecList().get(acc.getAccRecList().size() - 1).getChangeTime());
        }
        if (!acc.getPerson().getId().equals(idPerson)) {
            throw new IllegalArgumentException("not your Acc.");
        }
        return modelMapper.map(acc, AccDtoResponse.class);
    }

    @Override
    public List<AccDtoResponse> getAll(Long idPerson) {
        List<Acc> accList = accRepository.findAllByPerson_id(idPerson);
        List<AccDtoResponse> dtoList = accList
                .stream()
                .map(o->{o.setAccRecList(accRecRepository.findAllByAcc_Id(o.getId())); return o;})
                .map(o -> {
                    if (o.getAccRecList()==null || o.getAccRecList().size() <= 0) {
                        o.setBalance(0);
                        o.setChangeTime(null);
                    } else {
                        o.setBalance(o.getAccRecList().get(o.getAccRecList().size() - 1).getBalance());
                        o.setChangeTime(o.getAccRecList().get(o.getAccRecList().size() - 1).getChangeTime());
                    }
                    return o;
                })
                .map(i -> modelMapper.map(i, AccDtoResponse.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public AccDtoResponse addAcc(AccDtoAdd accDTO, Long idPerson) {
        Acc acc = modelMapper.map(accDTO, Acc.class);
        Person person = personRepository.getOne(idPerson);
        acc.setPerson(person);
        acc.setChangeTime(LocalDateTime.now());
        Acc accFromSave=accRepository.save(acc);
        AccDtoResponse accDTOoutput=modelMapper.map(accFromSave, AccDtoResponse.class);
        accRecService.addAccRec(modelMapper.map(accDTOoutput, AccRecDtoAdd.class),acc.getId(),idPerson);
        return accDTOoutput;
    }

    @Override
    public AccDtoResponse updateAcc(AccDtoUpdate accDTO, Long id, Long idPerson) {
        Acc acc = accRepository.findById(id).get();
        if (!acc.getPerson().getId().equals(idPerson)) {
            throw new IllegalArgumentException("not your Acc.");
        }
        modelMapper.map(accDTO, acc);
        accRepository.save(acc);
        return modelMapper.map(getById(id,idPerson), AccDtoResponse.class);
    }
}
