package bcsoft.it.glam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubRedditDto {

    private long id;
    private String nome;
    private String descrizione;
    private Integer numeroPost;

}
