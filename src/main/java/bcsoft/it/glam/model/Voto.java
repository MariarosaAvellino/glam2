package bcsoft.it.glam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voto implements Serializable {

    @Id
    @Column(name = "id_voto")
    private Long idVoto;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "user_id")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name="postId", referencedColumnName = "post_id")
    private Post post;

}
