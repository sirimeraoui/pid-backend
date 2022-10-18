package be.eafcuccle.projint.backendsirimeraoui.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import be.eafcuccle.projint.backendsirimeraoui.Entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    
}
