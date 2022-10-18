package be.eafcuccle.projint.backendsirimeraoui.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import be.eafcuccle.projint.backendsirimeraoui.Entities.Airport;

public interface AirportRepository extends JpaRepository<Airport,Long> {
    
}
