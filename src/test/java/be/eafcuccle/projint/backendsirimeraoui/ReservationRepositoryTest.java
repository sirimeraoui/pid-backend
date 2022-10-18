package be.eafcuccle.projint.backendsirimeraoui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import be.eafcuccle.projint.backendsirimeraoui.Entities.Reservation;
import be.eafcuccle.projint.backendsirimeraoui.Entities.ReservationStatus;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.ReservationRepository;

@DataJpaTest
public class ReservationRepositoryTest {
    @Autowired
    private ReservationRepository repository;

    @Autowired
    private TestEntityManager mng;

    @Test
    public void saveAndFind() {
        Reservation ReservationObj = new Reservation(ReservationStatus.CONFIRMED);
        Reservation saveReservation = repository.save(ReservationObj);
        mng.flush();

        Long reservationId = saveReservation.getId();
        Optional<Reservation> foundReservation = repository.findById(reservationId);
        assertTrue(foundReservation.isPresent());
        assertEquals(ReservationStatus.CONFIRMED, foundReservation.get().getStatus());
    }
}
