package bcsoft.it.glam.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDiNotifica implements Serializable {

    private String Soggetto;
    private String recipiente;
    private String corpo;
}
