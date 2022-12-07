package bcsoft.it.glam.repository;

import bcsoft.it.glam.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    public Optional<Utente> findByUsername(String username);

}
