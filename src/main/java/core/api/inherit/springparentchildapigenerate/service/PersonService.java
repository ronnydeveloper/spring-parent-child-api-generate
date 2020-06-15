package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.PersonDTO;
import core.api.inherit.springparentchildapigenerate.entity.Person;
import core.api.inherit.springparentchildapigenerate.util.response.ApiResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

public interface PersonService {
    Person createOrUpdateAccount(Person person);

    void deleteAccountById(Long id) throws EntityNotFoundException;

    Person getAccountById(Long id) throws EntityNotFoundException;

    Set<PersonDTO> getAllSiblings(Long id) throws EntityNotFoundException;

    PersonDTO getAllDetails(Long id) throws EntityNotFoundException;

    ApiResponse<List<PersonDTO>> doView();

    ApiResponse doAdd(Person person);

    ApiResponse doEdit(Person person);

    ApiResponse doDelete(List<Person> personList);

    ApiResponse doPreview(PersonDTO personDTO);
}
