package bcsoft.it.glam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Commento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;
    @NotEmpty
    private String testo;
    private Instant dataCreazione;

    @ManyToOne
    @JoinColumn(name="postId", referencedColumnName = "post_id")
    private Post post;
    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "user_id")
    private Utente utente;


}