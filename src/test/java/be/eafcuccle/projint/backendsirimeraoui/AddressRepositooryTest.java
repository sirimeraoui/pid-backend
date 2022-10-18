package be.eafcuccle.projint.backendsirimeraoui;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import be.eafcuccle.projint.backendsirimeraoui.Entities.Address;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.AddressRepository;

@DataJpaTest(showSql = true)
public class AddressRepositooryTest {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private TestEntityManager mng;

    @Test
    public void saveAndFind(){
        Address adrObj = new Address("Uccle",1180L, "Brussels", "Belgium");
        Address savedAdr = repository.save(adrObj);
        mng.flush();

        Long adrId = savedAdr.getId();
        Optional <Address> foundAdr = repository.findById(adrId);

        assertTrue(foundAdr.isPresent());
        assertEquals(1180L, foundAdr.get().getPostalCode());

    }
    
}
