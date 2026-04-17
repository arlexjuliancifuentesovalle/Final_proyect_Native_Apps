package co.edu.ue.finalproject.data.remote;

import java.util.List;
import co.edu.ue.finalproject.data.model.ServicioDTO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicioApiService {
    @GET("api/Tip-servicios")
    Call<List<ServicioDTO>> obtenerServicios();
}
