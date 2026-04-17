package co.edu.ue.finalproject.data.repository;

import java.util.List;
import co.edu.ue.finalproject.data.model.ServicioDTO;
import co.edu.ue.finalproject.data.remote.ServicioApiService;
import co.edu.ue.finalproject.domain.repository.ServicioRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicioRepositoryImpl implements ServicioRepository {
    private final ServicioApiService apiService;

    public ServicioRepositoryImpl(ServicioApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getServicios(ServicioCallback callback) {
        apiService.obtenerServicios().enqueue(new Callback<List<ServicioDTO>>() {
            @Override
            public void onResponse(Call<List<ServicioDTO>> call, Response<List<ServicioDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Error al obtener servicios");
                }
            }

            @Override
            public void onFailure(Call<List<ServicioDTO>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
