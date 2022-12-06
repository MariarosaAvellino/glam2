package bcsoft.it.glam.repository;

import bcsoft.it.glam.model.Post;
import bcsoft.it.glam.model.Utente;
import bcsoft.it.glam.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    Optional<Voto> findTopByPostAndUtenteOrderByVotoIdDesc(Post post, Utente utenteCorrente);
}
