package bcsoft.it.glam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VerificationToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_token")
    private Long id;
    private String token;
    @Column(name = "data_scadenza")
    private Instant dataScadenza;

    @OneToOne
    @JoinColumn(name="userId", referencedColumnName = "user_id")
    private Utente utente;

}
