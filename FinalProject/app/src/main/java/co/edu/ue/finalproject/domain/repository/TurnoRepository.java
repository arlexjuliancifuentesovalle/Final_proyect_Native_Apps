package co.edu.ue.finalproject.domain.repository;

import java.util.List;
import co.edu.ue.finalproject.data.model.TurnoDTO;

public interface TurnoRepository {
    void obtenerTurnos(TurnoCallback callback);

    interface TurnoCallback {
        void onSuccess(List<TurnoDTO> lista);
        void onError(String mensaje);
    }
}
