package co.edu.ue.finalproject.data.repository;

import java.util.List;
import co.edu.ue.finalproject.data.model.UsuarioDTO;
import co.edu.ue.finalproject.data.remote.AuthInterceptor;
import co.edu.ue.finalproject.data.remote.UserApiService;
import co.edu.ue.finalproject.domain.repository.UserRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository {
    private final UserApiService apiService;

    public UserRepositoryImpl(UserApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void login(String user, String pwd, LoginCallback callback) {
        apiService.login(user, pwd).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body();
                    AuthInterceptor.setToken(token);
                    callback.onSuccess(token);
                } else {
                    callback.onError("Error de autenticación técnica");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void obtenerUsuarios(UsersCallback callback) {
        apiService.obtenerUsuarios().enqueue(new Callback<List<UsuarioDTO>>() {
            @Override
            public void onResponse(Call<List<UsuarioDTO>> call, Response<List<UsuarioDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Error al obtener usuarios. ¿Token válido?");
                }
            }

            @Override
            public void onFailure(Call<List<UsuarioDTO>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
