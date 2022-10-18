package be.eafcuccle.projint.backendsirimeraoui.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import be.eafcuccle.projint.backendsirimeraoui.Entities.FlightClass;

public interface FlightClassRepository extends JpaRepository<FlightClass,Long> {
    
}
