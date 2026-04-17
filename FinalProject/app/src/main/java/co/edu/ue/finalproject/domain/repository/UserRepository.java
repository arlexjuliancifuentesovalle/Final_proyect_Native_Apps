package co.edu.ue.finalproject.domain.repository;

import java.util.List;
import co.edu.ue.finalproject.data.model.UsuarioDTO;

public interface UserRepository {
    void login(String user, String pwd, LoginCallback callback);
    void obtenerUsuarios(UsersCallback callback);

    interface LoginCallback {
        void onSuccess(String token);
        void onError(String mensaje);
    }

    interface UsersCallback {
        void onSuccess(List<UsuarioDTO> usuarios);
        void onError(String mensaje);
    }
}
