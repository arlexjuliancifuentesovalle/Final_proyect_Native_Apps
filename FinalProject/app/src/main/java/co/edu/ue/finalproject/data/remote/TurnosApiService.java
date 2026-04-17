package co.edu.ue.finalproject.data.remote;

import java.util.List;
import co.edu.ue.finalproject.data.model.TurnoDTO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TurnosApiService {
    @GET("api/shift")
    Call<List<TurnoDTO>> obtenerTurnos();
}
