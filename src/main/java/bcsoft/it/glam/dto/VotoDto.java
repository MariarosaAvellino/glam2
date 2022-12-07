package bcsoft.it.glam.dto;

import bcsoft.it.glam.model.TipoVoto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotoDto {

    private Long postId;
    private TipoVoto tipoVoto;

}