package co.edu.ue.finalproject.domain.repository;

import java.util.List;
import co.edu.ue.finalproject.data.model.ServicioDTO;

public interface ServicioRepository {
    interface ServicioCallback {
        void onSuccess(List<ServicioDTO> servicios);
        void onError(String message);
    }
    void getServicios(ServicioCallback callback);
}
