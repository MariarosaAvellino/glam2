package bcsoft.it.glam.repository;

import bcsoft.it.glam.dto.SubRedditDto;
import bcsoft.it.glam.model.SubReddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubRedditRepository extends JpaRepository<SubReddit, Long> {

    public SubRedditDto getById(long id);

}
