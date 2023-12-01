
package com.pe.mispatitas.mispatitas.dao;

import com.pe.mispatitas.mispatitas.entidad.Mascota;
import java.util.List;

/**
 *
 * @author Luis Santos
 */
public interface DaoMascota {
     List<Mascota> mascotaSel();

    Mascota mascotaGet(Integer id);

    String mascotaIns(Mascota mascota);

    String mascotaUpd(Mascota mascota);

    String mascotaDel(Integer id);

    String getMensaje();
    
}
