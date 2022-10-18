package be.eafcuccle.projint.backendsirimeraoui;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import be.eafcuccle.projint.backendsirimeraoui.Entities.Role;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.RoleRepository;


@DataJpaTest(showSql = true)
public class RoleRepositoryTest {
    @Autowired
    private RoleRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void saveAndFind() {
        Role createdRole = new Role("Client", "Can book a flight for one or many passengers");
        Long RoleId = repository.save(createdRole).getId();
        em.flush(); // Make sure the entity is actually saved to the database.
        java.util.Optional<Role> foundRole = repository.findById(RoleId);

        assertTrue(foundRole.isPresent());
        assertEquals("Client", foundRole.get().getTitle());
}
}
