package co.edu.ue.finalproject.domain.usecase;

import co.edu.ue.finalproject.domain.repository.UserRepository;

public class ObtenerUsuariosUseCase {
    private final UserRepository repository;

    public ObtenerUsuariosUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public void ejecutar(UserRepository.UsersCallback callback) {
        // Primero hacemos el login técnico para asegurar el token
        // YWRtaW4= (admin) y QWRtaW4xMjMq (Admin123*)
        repository.login("YWRtaW4=", "QWRtaW4xMjMq", new UserRepository.LoginCallback() {
            @Override
            public void onSuccess(String token) {
                // Una vez obtenido el token, el interceptor lo guardará
                // y ahora pedimos los usuarios
                repository.obtenerUsuarios(callback);
            }

            @Override
            public void onError(String mensaje) {
                callback.onError("Error de autenticación técnica: " + mensaje);
            }
        });
    }
}
