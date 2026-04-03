package co.edu.ue.finalproject.domain.repository;

import com.google.firebase.auth.FirebaseUser;

/**
 * Esta es una "Interfaz". Imaginala como un CONTRATO o una LISTA DE TAREAS.
 * Aquí no decimos CÓMO se hacen las cosas, solo QUÉ cosas se pueden hacer
 * relacionadas con la autenticación (entrar, salir, ver usuario).
 */
public interface AuthRepository {
    
    /**
     * Este es un mensajero (Callback). 
     * Nos sirve para avisar si la tarea terminó bien o si hubo un problema.
     */
    interface AuthCallback {
        void onSuccess(); // Se llama si todo salió bien.
        void onError(String message); // Se llama si hubo un error (y nos dice cuál).
    }

    // Tarea: Intentar entrar con correo y clave.
    void login(String email, String password, AuthCallback callback);
    
    // Tarea: Dime quién es el usuario que está conectado ahora mismo.
    FirebaseUser getCurrentUser();
    
    // Tarea: Cerrar la sesión.
    void logout();
}
