package co.edu.ue.finalproject.domain.repository;

import java.util.List;

import co.edu.ue.finalproject.data.model.PagosDTO;

public interface PagosRepository {
    void obtenerPagos(PagosCallback callback);

    interface PagosCallback{
        void onSuccess(List<PagosDTO> lista);
        void onError(String mensaje);

    }


}
