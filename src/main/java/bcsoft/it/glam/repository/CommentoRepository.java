package bcsoft.it.glam.repository;

import bcsoft.it.glam.model.Commento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentoRepository extends JpaRepository <Commento, Long> {
}
