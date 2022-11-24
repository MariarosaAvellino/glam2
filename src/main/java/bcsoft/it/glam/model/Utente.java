package bcsoft.it.glam.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
public class Utente {
    @Column(name = "user_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @NotBlank(message = "Name may not be blank")
    private String username;
    @NotBlank
    @Size(min = 4, max = 32, message = "Name must be between 4 and 32 characters long")
    private String password;
    @Email
    @NotBlank(message = "Name may not be blank")
    private String email;
    private Instant created;
}
