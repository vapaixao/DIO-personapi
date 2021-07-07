package com.vapaixao.personapi.mapper;

import com.vapaixao.personapi.dto.request.PersonDTO;
import com.vapaixao.personapi.entity.Person;
import org.springframework.web.bind.annotation.Mapping;

public class PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
