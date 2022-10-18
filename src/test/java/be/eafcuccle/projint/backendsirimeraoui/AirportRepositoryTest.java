package be.eafcuccle.projint.backendsirimeraoui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import be.eafcuccle.projint.backendsirimeraoui.Entities.Airport;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.AirportRepository;

@DataJpaTest
public class AirportRepositoryTest {
    
    @Autowired
    private AirportRepository repository;

    @Autowired
    private TestEntityManager mng;

    @Test
    public void saveAndFind(){
        Airport airportObj = new Airport("Brussels Airport", "Zaventem", "Belgium",50.9013466,4.4853555);
        Airport saveAirport = repository.save(airportObj);
        mng.flush();

        Long airportId = saveAirport.getId();
        Optional<Airport> foundAirport = repository.findById(airportId);
        assertTrue(foundAirport.isPresent());
        assertEquals("Brussels Airport", foundAirport.get().getName());
    }
}







    

 


