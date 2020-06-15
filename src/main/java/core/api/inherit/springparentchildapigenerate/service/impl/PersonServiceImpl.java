package core.api.inherit.springparentchildapigenerate.service.impl;

import core.api.inherit.springparentchildapigenerate.dto.PersonDTO;
import core.api.inherit.springparentchildapigenerate.entity.Person;
import core.api.inherit.springparentchildapigenerate.repository.AccountRepo;
import core.api.inherit.springparentchildapigenerate.repository.PersonRepo;
import core.api.inherit.springparentchildapigenerate.service.PersonService;
import core.api.inherit.springparentchildapigenerate.util.response.ApiResponse;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    static final Logger logger = Logger.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonRepo personRepository;

    private ModelMapper modelMapper = null;

    @Override
    public Person createOrUpdateAccount(Person person) {
        return null;
    }

    @Override
    public void deleteAccountById(Long id) throws EntityNotFoundException {

    }

    @Override
    public Person getAccountById(Long id) throws EntityNotFoundException {
        return null;
    }

    @Override
    public Set<PersonDTO> getAllSiblings(Long id) throws EntityNotFoundException {
        return personRepository.findById(id).map(findSiblings).get();
    }

    @Override
    public PersonDTO getAllDetails(Long id) throws EntityNotFoundException {
        return personRepository.findById(id).map(mapToPersonDTO).get();
    }

    @Override
    public ApiResponse<List<PersonDTO>> doView() {
        return null;
    }

    @Override
    public ApiResponse doAdd(Person person) {
        return null;
    }

    @Override
    public ApiResponse doEdit(Person person) {
        return null;
    }

    @Override
    public ApiResponse doDelete(List<Person> personList) {
        return null;
    }

    @Override
    public ApiResponse doPreview(PersonDTO personDTO) {
        return null;
    }

    private Function<Person, PersonDTO> mapToPersonDTO = p -> PersonDTO.builder()
            .id(p.getId())
            .fullName(p.getFullName())
            .parent(p.getParent())
            .companyID(p.getCompanyID())
            .accountID(p.getAccountID())
            .children(p.getChildren()).build();

    private Function<Person, Set<PersonDTO>> findSiblings = person -> person.getParent().getChildren().stream()
            .map(p -> PersonDTO.builder()
                    .id(p.getId())
                    .fullName(p.getFullName())
                    .companyID(p.getCompanyID())
                    .accountID(p.getAccountID()).build()).collect(Collectors.toSet());

}
