package be.eafcuccle.projint.backendsirimeraoui.api;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import be.eafcuccle.projint.backendsirimeraoui.Entities.Person;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.PersonRepository;
import be.eafcuccle.projint.backendsirimeraoui.api.EntitiesTO.PersonTO;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public List<PersonTO> people() {
       List<Person> people = personRepository.findAll();
       return people.stream().map(PersonController::convertToTO).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PersonTO> getPersonById(@PathVariable("id") long id) {
      java.util.Optional<Person> person = personRepository.findById(id);
      if (person.isPresent()) {
         PersonTO personTO = convertToTO(person.get());
         return ResponseEntity.ok().eTag(Long.toString(person.get().getVersion())).body(personTO); 
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody PersonTO personTO, UriComponentsBuilder uriBuilder) {
      Long id = personRepository.save(convertToOriginal(personTO)).getId();
      URI newPersonUri = uriBuilder.path("{id}").build(id);
      return ResponseEntity.created(newPersonUri).build();
    }


    //converters
    private static Person convertToOriginal(PersonTO personTO){

          return new Person(personTO.getFirstName(),personTO.getLastName());
    }
    private static PersonTO convertToTO(Person person){
        return new PersonTO(person.getId(),person.getFirstName(),person.getLastName(),person.getVersion());
      }

}
