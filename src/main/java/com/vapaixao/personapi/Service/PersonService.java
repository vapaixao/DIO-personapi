package com.vapaixao.personapi.Service;

import com.vapaixao.personapi.dto.MessageResponseDTO;
import com.vapaixao.personapi.dto.request.PersonDTO;
import com.vapaixao.personapi.entity.Person;
import com.vapaixao.personapi.exception.PersonNotFoundExeption;
import com.vapaixao.personapi.dto.mapper.PersonMapper;
import com.vapaixao.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public MessageResponseDTO create(PersonDTO personDTO){
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);
        MessageResponseDTO createMessage = createMessageResponse("Person created with ID ", savedPerson.getId());
        return createMessage;
    }

    @GetMapping
    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundExeption {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundExeption(id));
        return personMapper.toDTO(person);
    }

    public void delete (Long id) throws PersonNotFoundExeption{
        personRepository.findById(id)
            .orElseThrow(() -> new PersonNotFoundExeption(id));

        personRepository.deleteById(id);
    }

    public MessageResponseDTO update(Long id, PersonDTO personDTO) throws PersonNotFoundExeption {
        personRepository.findById(id)
        .orElseThrow(() -> new PersonNotFoundExeption(id));

        Person UpdatePerson = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(UpdatePerson);
        MessageResponseDTO messageResponse = createMessageResponse("Updated Person!", savedPerson.getId());
        return messageResponse;
    }

    private MessageResponseDTO createMessageResponse(String message, Long id2) {
        return MessageResponseDTO
                .builder()
                .message(message + id2)
                .build();
    }
}
