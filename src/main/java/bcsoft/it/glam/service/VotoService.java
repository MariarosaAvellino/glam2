package bcsoft.it.glam.service;

import bcsoft.it.glam.model.TipoVoto;
import org.springframework.stereotype.Service;

@Service
public class VotoService {
    //continuare
    public int countVoto(TipoVoto tipoVoto){
        return tipoVoto.getDirection();
    }
}
