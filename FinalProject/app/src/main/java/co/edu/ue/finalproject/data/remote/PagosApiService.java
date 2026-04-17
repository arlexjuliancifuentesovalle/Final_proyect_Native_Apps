package co.edu.ue.finalproject.data.remote;

import java.util.List;

import co.edu.ue.finalproject.data.model.PagosDTO;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Esta clase define los endpoints, la implemeta la clase RETROcLI.. para hacer las llamadas HTTP
 * contrato
 */

public interface PagosApiService {
    @GET("api/Tip-pagos")
    Call<List<PagosDTO>> obtenerPagos();
}
