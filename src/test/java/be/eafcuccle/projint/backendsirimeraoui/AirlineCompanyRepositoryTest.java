package be.eafcuccle.projint.backendsirimeraoui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import be.eafcuccle.projint.backendsirimeraoui.Entities.AirlineCompany;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.AirlineCompanyRepository;

@DataJpaTest
public class AirlineCompanyRepositoryTest {
    
    @Autowired
    private AirlineCompanyRepository repository;

    @Autowired
    private TestEntityManager mng;

    @Test
    public void saveAndFind(){
        AirlineCompany companyObj = new AirlineCompany("Brussels Airlines", "You're in good company");
        AirlineCompany saveCompany = repository.save(companyObj);
        mng.flush();

        Long companyId = saveCompany.getId();
        Optional<AirlineCompany> foundCompany = repository.findById(companyId);
        assertTrue(foundCompany.isPresent());
        assertEquals("Brussels Airlines", foundCompany.get().getName());
    }

}
