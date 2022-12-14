package bcsoft.it.glam.repository;

import bcsoft.it.glam.model.Commento;
import bcsoft.it.glam.model.Post;
import bcsoft.it.glam.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentoRepository extends JpaRepository <Commento, Long> {

    public List<Commento> findAllByPost(Post post);
    public List<Commento> findAllByUtente(Utente utente);

}
