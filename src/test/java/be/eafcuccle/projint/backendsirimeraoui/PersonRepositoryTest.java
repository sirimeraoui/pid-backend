package be.eafcuccle.projint.backendsirimeraoui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import be.eafcuccle.projint.backendsirimeraoui.Entities.Person;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.PersonRepository;
@DataJpaTest
public class PersonRepositoryTest {
  
    @Autowired
    private PersonRepository repository;

    @Autowired
    private TestEntityManager mng;

    @Test
    public void saveAndFind(){
        Person personObj = new Person("Jennifer", "Outis");
        Person savePerson = repository.save(personObj);
        mng.flush();

        Long personId = savePerson.getId();
        Optional<Person> foundPerson = repository.findById(personId);
        assertTrue(foundPerson.isPresent());
        assertEquals("Outis", foundPerson.get().getLastName());
    }
}
