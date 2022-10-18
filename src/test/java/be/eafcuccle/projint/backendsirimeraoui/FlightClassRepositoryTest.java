package be.eafcuccle.projint.backendsirimeraoui;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import be.eafcuccle.projint.backendsirimeraoui.Entities.FlightClass;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.FlightClassRepository;

@DataJpaTest
public class FlightClassRepositoryTest {
        
    @Autowired
    private FlightClassRepository repository;

    @Autowired
    private TestEntityManager mng;


    @Test
    public void saveAndFind(){
        FlightClass flightClassObj = new FlightClass("Economy",new BigDecimal(200.51), "class for wallet comfort...");
        FlightClass saveFlightClass = repository.save(flightClassObj);
        mng.flush();

        Long flightClassId = saveFlightClass.getId();
        Optional<FlightClass> foundFlightClass = repository.findById(flightClassId);
        assertTrue(foundFlightClass.isPresent());
        assertEquals("Economy", foundFlightClass.get().getName());
    }
}
