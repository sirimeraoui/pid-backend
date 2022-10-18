package be.eafcuccle.projint.backendsirimeraoui.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import be.eafcuccle.projint.backendsirimeraoui.Entities.Flight;

public interface FlightRepository extends JpaRepository<Flight,Long>{
    
}
