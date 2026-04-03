package co.edu.ue.finalproject.domain.usecase;

import co.edu.ue.finalproject.domain.repository.AuthRepository;

/**
 * Un "Caso de Uso" representa una acción específica que el usuario puede hacer.
 * En este caso: "El usuario quiere iniciar sesión".
 * Su trabajo es solo dar la orden al repositorio, sin saber CÓMO se hace.
 */
public class LoginUseCase {
    private final AuthRepository repository; // El encargado de los datos.

    public LoginUseCase(AuthRepository repository) {
        this.repository = repository;
    }

    /**
     * Este método ejecuta la acción de login.
     */
    public void execute(String email, String password, AuthRepository.AuthCallback callback) {
        // Simplemente le dice al repositorio: "Oye, haz el login con esto".
        repository.login(email, password, callback);
    }
}
