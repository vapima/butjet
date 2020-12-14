package ru.vapima.butjet.butJet3.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vapima.butjet.butJet3.dto.person.PersonDtoAdd;
import ru.vapima.butjet.butJet3.dto.person.PersonDtoResponse;
import ru.vapima.butjet.butJet3.dto.person.PersonDtoUpdate;
import ru.vapima.butjet.butJet3.model.Person;
import ru.vapima.butjet.butJet3.model.Role;
import ru.vapima.butjet.butJet3.model.State;
import ru.vapima.butjet.butJet3.repositories.PersonRepository;
import ru.vapima.butjet.butJet3.service.PersonService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService, UserDetailsService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void deleteById(Long id) {
        Person person=personRepository.getOne(id);
        person.setState(State.DELETED);
        personRepository.save(person);
        //personRepository.deleteById(id);
    }

    @Override
    public PersonDtoResponse getById(Long id) {
        Person person=personRepository.getOne(id);
        if (!person.getState().equals(State.ACTIVE)) {throw new IllegalArgumentException("Person not found");}
        return modelMapper.map(person, PersonDtoResponse.class);
    }

    @Override
    public List<PersonDtoResponse> getAll() {
        List<Person> personList = personRepository.findAll();
        List<PersonDtoResponse> dtoList = personList
                .stream()
                .filter(o->o.getState().equals(State.ACTIVE))
                .map(i->modelMapper.map(i, PersonDtoResponse.class)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public PersonDtoResponse addPerson(PersonDtoAdd personDTO) {
        Person personFromDB = personRepository.findByMail(personDTO.getMail());
        if (personFromDB != null) {
            throw new IllegalArgumentException("This email is already registration.");
        }
        Person person=modelMapper.map(personDTO,Person.class);
        person.setRole(Role.ROLE_USER);
        person.setState(State.ACTIVE);
        person.setHashPassword(bCryptPasswordEncoder.encode(person.getHashPassword()));
       return modelMapper.map(personRepository.save(person), PersonDtoResponse.class);
    }

    @Override
    public PersonDtoResponse updatePerson(PersonDtoUpdate personDTO, Long id) {
        Person person=personRepository.findById(id).get();
        modelMapper.map(personDTO,person);
        return modelMapper.map(personRepository.save(person), PersonDtoResponse.class);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = personRepository.findByMail(s);
        if (person == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return person;
    }
}
