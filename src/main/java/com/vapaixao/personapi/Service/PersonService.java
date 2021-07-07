package com.vapaixao.personapi.Service;

import com.vapaixao.personapi.exception.PersonNotFoundExeption;
import com.vapaixao.personapi.dto.MessageResponseDTO;
import com.vapaixao.personapi.dto.request.PersonDTO;
import com.vapaixao.personapi.entity.Person;
import com.vapaixao.personapi.mapper.PersonMapper;
import com.vapaixao.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created Person with ID " + savedPerson.getId())
                .build();
    }

    @GetMapping
    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundExeption {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void delete (Long id) throws PersonNotFoundExeption{
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundExeption {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundExeption(id));
    }
}
