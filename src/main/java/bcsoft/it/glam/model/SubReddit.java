package bcsoft.it.glam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SubReddit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Name may not be blank")
    private String nome;
    @NotBlank(message = "Description may not be blank")
    private String descrizione;
    private Instant dataCreazione;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "user_id")
    private Utente utente;
}
