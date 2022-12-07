package bcsoft.it.glam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private long postId;
    @NotBlank(message = "Nome Post may not be blank")
    private String nomePost;
    @Nullable
    private String url;
    @NotNull
    @Lob
    private String description;
    private int contatoreVoti=0;
    private Instant dataCreazione;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "user_id")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "id")
    private SubReddit subReddit;

}
