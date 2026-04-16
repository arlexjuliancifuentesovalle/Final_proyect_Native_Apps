package co.edu.ue.finalproject.data.remote;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Esta clase es como nuestra "Antena" o "Cable" directo con Google Firebase.
 * Su única responsabilidad es darnos la conexión oficial para que la app 
 * pueda hablar con los servidores de Firebase.
 */
public class FireBaseService {
    // Esta es la herramienta oficial de Firebase para manejar usuarios.
    private final FirebaseAuth mAuth;

    public FireBaseService() {
        // Aquí "encendemos" la conexión.
        this.mAuth = FirebaseAuth.getInstance();
    }

    /**
     * Este método nos entrega la conexión lista para ser usada.
     */
    public FirebaseAuth getAuth() {
        return mAuth;
    }
}
