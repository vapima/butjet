package ru.vapima.butjet.butJet3.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoAdd;
import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoResponse;
import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoUpdate;
import ru.vapima.butjet.butJet3.model.Acc;
import ru.vapima.butjet.butJet3.model.AccRec;
import ru.vapima.butjet.butJet3.model.Person;
import ru.vapima.butjet.butJet3.repositories.AccRecRepository;
import ru.vapima.butjet.butJet3.repositories.AccRepository;
import ru.vapima.butjet.butJet3.repositories.PersonRepository;
import ru.vapima.butjet.butJet3.service.AccRecService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class AccRecServiceImpl implements AccRecService {
    @Autowired
    private AccRecRepository accRecRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AccRepository accRepository;


    @Override
    public void deleteById(Long id,Long idAcc,Long idPerson) {
        Person person =personRepository.getOne(idPerson);
        Acc acc=accRepository.getOne(idAcc);
        if (!acc.getPerson().equals(person)) {throw new IllegalArgumentException("Person dont have this account");}
        accRecRepository.deleteById(id);
    }

    @Override
    public AccRecDtoResponse getById(Long id, Long idAcc, Long idPerson) {
        Person person =personRepository.getOne(idPerson);
        Acc acc=accRepository.getOne(idAcc);
        if (!acc.getPerson().equals(person)) {throw new IllegalArgumentException("Person dont have this account");}
        return modelMapper.map(accRecRepository.getOne(id), AccRecDtoResponse.class);
    }

    @Override
    public List<AccRecDtoResponse> getAll(Long idAcc, Long idPerson) {
        Person person =personRepository.getOne(idPerson);
        Acc acc=accRepository.getOne(idAcc);
        if (!acc.getPerson().equals(person)) {throw new IllegalArgumentException("Person dont have this account");}
        List<AccRec> accRecList = accRecRepository.findAllByAcc_Id(idAcc);
        return accRecList
                .stream()
                .map(i->modelMapper.map(i, AccRecDtoResponse.class)).collect(Collectors.toList());
    }

    @Override
    public AccRecDtoResponse addAccRec(AccRecDtoAdd accRecDTO, Long idAcc, Long idPerson) {
        AccRec accRec=modelMapper.map(accRecDTO,AccRec.class);
        Person person =personRepository.getOne(idPerson);
        Acc acc=accRepository.getOne(idAcc);
        if (!acc.getPerson().equals(person)) {throw new IllegalArgumentException("Person dont have this account");}
        accRec.setAcc(acc);
        accRec.setChangeTime(LocalDateTime.now());
        AccRecDtoResponse accRecDtoResponse=modelMapper.map(accRecRepository.save(accRec), AccRecDtoResponse.class);
        return accRecDtoResponse;
    }

    @Override
    public AccRecDtoResponse updateAccRec(AccRecDtoUpdate accRecDTO, Long id, Long idAcc, Long idPerson) {
        AccRec accRec=accRecRepository.findById(id).get();
        modelMapper.map(accRecDTO,accRec);
        return modelMapper.map(accRecRepository.save(accRec), AccRecDtoResponse.class);
    }
}
