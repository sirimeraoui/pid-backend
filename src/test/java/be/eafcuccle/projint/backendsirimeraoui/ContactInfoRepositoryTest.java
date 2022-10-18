package be.eafcuccle.projint.backendsirimeraoui;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import be.eafcuccle.projint.backendsirimeraoui.Entities.ContactInfo;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.ContactInfoRepository;



@DataJpaTest(showSql = true)
public class ContactInfoRepositoryTest {

    @Autowired
    private ContactInfoRepository repository;


    @Autowired
    private TestEntityManager mng;

    @Test
    public void saveAndFind(){
        ContactInfo contactObj = new ContactInfo(023321166L, "uccle@gmail.com");
        ContactInfo savedContact = repository.save(contactObj);
        mng.flush();

        Long contactId = savedContact.getId();
        Optional<ContactInfo> foundContact = repository.findById(contactId);

        assertTrue(foundContact.isPresent());
        assertEquals("uccle@gmail.com", foundContact.get().getEmailAddress());

    }
    
}
