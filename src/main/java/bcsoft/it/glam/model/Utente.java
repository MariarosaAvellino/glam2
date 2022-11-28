package bcsoft.it.glam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utente implements Serializable {

    @Column(name = "user_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @NotBlank(message = "Name may not be blank")
    private String username;
    @NotBlank
    @Size(min = 4, max = 32, message = "Password must be between 4 and 32 characters long")
    private String password;
    @Email
    @NotBlank(message = "Email may not be blank")
    private String email;
    private Instant created;
    private boolean enable;


}
