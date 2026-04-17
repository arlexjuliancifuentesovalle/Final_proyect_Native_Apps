package co.edu.ue.finalproject.data.repository;

import java.util.List;
import co.edu.ue.finalproject.data.model.TurnoDTO;
import co.edu.ue.finalproject.data.remote.TurnosApiService;
import co.edu.ue.finalproject.domain.repository.TurnoRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TurnoRepositoryImpl implements TurnoRepository {
    private final TurnosApiService apiService;

    public TurnoRepositoryImpl(TurnosApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void obtenerTurnos(TurnoCallback callback) {
        apiService.obtenerTurnos().enqueue(new Callback<List<TurnoDTO>>() {
            @Override
            public void onResponse(Call<List<TurnoDTO>> call, Response<List<TurnoDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Error al obtener turnos");
                }
            }

            @Override
            public void onFailure(Call<List<TurnoDTO>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
