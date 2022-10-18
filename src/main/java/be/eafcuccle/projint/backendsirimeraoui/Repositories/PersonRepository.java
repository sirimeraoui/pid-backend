package be.eafcuccle.projint.backendsirimeraoui.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import be.eafcuccle.projint.backendsirimeraoui.Entities.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {
    
}
