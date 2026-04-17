package co.edu.ue.finalproject.data.remote;

import java.util.List;

import co.edu.ue.finalproject.data.model.PagosDTO;
import co.edu.ue.finalproject.data.model.TurnoDTO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PagosApiService {
    @GET("api/Tip-pagos")
    Call<List<PagosDTO>> obtenerPagos();
}
