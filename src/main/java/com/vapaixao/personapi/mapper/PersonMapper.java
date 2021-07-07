package com.vapaixao.personapi.mapper;

import com.vapaixao.personapi.dto.request.PersonDTO;
import com.vapaixao.personapi.entity.Person;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.Mapping;

public class PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    public Person toModel(PersonDTO personDTO);

    public PersonDTO toDTO(Person person);
}
