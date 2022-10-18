package be.eafcuccle.projint.backendsirimeraoui.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.eafcuccle.projint.backendsirimeraoui.Entities.AirlineCompany;

public interface AirlineCompanyRepository extends JpaRepository<AirlineCompany,Long> {
    
}
