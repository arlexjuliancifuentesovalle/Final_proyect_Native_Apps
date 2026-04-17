package co.edu.ue.finalproject.data.remote;

import java.util.List;
import co.edu.ue.finalproject.data.model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApiService {
    @POST("login")
    Call<String> login(@Query("user") String user, @Query("pwd") String pwd);

    @GET("api/users")
    Call<List<UsuarioDTO>> obtenerUsuarios();
}
