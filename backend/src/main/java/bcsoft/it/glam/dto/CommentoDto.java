package bcsoft.it.glam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentoDto {

    private long id;
    private long postId;
    private String testo;
    private Instant dataCreazione;
    private String username;
}
