package be.eafcuccle.projint.backendsirimeraoui.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.eafcuccle.projint.backendsirimeraoui.Entities.ContactInfo;

public interface ContactInfoRepository extends JpaRepository<ContactInfo,Long>{

}