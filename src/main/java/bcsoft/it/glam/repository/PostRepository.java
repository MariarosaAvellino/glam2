package bcsoft.it.glam.repository;

import bcsoft.it.glam.model.Post;
import bcsoft.it.glam.model.SubReddit;
import bcsoft.it.glam.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository <Post, Long> {
    List<Post> findAllBySubReddit(SubReddit subreddit);
    List<Post> findByUtente(Utente user);
}




