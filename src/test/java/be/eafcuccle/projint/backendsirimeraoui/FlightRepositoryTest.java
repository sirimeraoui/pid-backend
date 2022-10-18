package be.eafcuccle.projint.backendsirimeraoui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import be.eafcuccle.projint.backendsirimeraoui.Entities.Flight;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.FlightRepository;
@DataJpaTest
public class FlightRepositoryTest {
    @Autowired
    private FlightRepository repository;

    @Autowired
    private TestEntityManager mng;

    @Test
    public void saveAndFind(){
        Flight flightObj = new Flight(1205, LocalDate.of(2022, 5,12), LocalTime.of(15,30), LocalTime.of(19, 45));
        Flight saveFlight = repository.save(flightObj);
        mng.flush();

        Long flightId = saveFlight.getId();
        Optional<Flight> foundFlight = repository.findById(flightId);
        assertTrue(foundFlight.isPresent());
        assertEquals(1205, foundFlight.get().getCode());
    }
}
