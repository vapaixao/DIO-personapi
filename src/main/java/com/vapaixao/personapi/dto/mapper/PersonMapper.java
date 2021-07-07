package com.vapaixao.personapi.dto.mapper;

import com.vapaixao.personapi.dto.request.PersonDTO;
import com.vapaixao.personapi.entity.Person;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;


@Mapper(componentModel = "Spring")
public interface PersonMapper {

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    public Person toModel(PersonDTO personDTO);

    public PersonDTO toDTO(Person person);
}
