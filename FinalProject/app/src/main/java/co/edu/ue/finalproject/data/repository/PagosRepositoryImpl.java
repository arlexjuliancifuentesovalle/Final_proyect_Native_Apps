package co.edu.ue.finalproject.data.repository;

import android.os.RemoteCallbackList;

import java.util.List;

import co.edu.ue.finalproject.data.model.PagosDTO;
import co.edu.ue.finalproject.data.remote.PagosApiService;
import co.edu.ue.finalproject.data.remote.RetrofitCliente;
import co.edu.ue.finalproject.domain.repository.PagosRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosRepositoryImpl implements PagosRepository {
    private PagosApiService apiService;
    public PagosRepositoryImpl(PagosApiService apiService) {
        this.apiService = apiService;
    }

    public PagosRepositoryImpl() {

    }

    @Override
    public void obtenerPagos(PagosCallback callback) {
        apiService.obtenerPagos().enqueue(new Callback<List<PagosDTO>>() {
            @Override
            public void onResponse(Call<List<PagosDTO>> call, Response<List<PagosDTO>> response) {
                if(response.isSuccessful() && response.body() !=null){
                    callback.onSuccess(response.body());
                }else {
                    callback.onError("Error en al repsuesta del servidor");
                }

            }

            @Override
            public void onFailure(Call<List<PagosDTO>> call, Throwable t) {
                callback.onError(t.getMessage());

            }
        });
    }
}
