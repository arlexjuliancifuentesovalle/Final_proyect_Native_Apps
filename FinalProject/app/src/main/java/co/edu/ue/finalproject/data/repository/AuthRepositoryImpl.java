package co.edu.ue.finalproject.data.repository;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import co.edu.ue.finalproject.data.DataBaseHelper;
import co.edu.ue.finalproject.data.remote.FireBaseService;
import co.edu.ue.finalproject.domain.repository.AuthRepository;



/**
 * Esta es la implementación real de nuestro "Contrato" (AuthRepository).
 * Aquí es donde realmente escribimos el código que habla con Firebase 
 * para loguear usuarios. Es el "Obrero" que hace el trabajo físico.
 */
public class AuthRepositoryImpl implements AuthRepository {
    private final FireBaseService firebaseService; // Nuestra conexión a Firebase.
    private final DataBaseHelper dataBaseHelper; //Nuestra conexion a SqLite
    private final Context context;

    public AuthRepositoryImpl(FireBaseService firebaseService, Context context) {
        this.firebaseService = firebaseService;
        this.context = context;
        this.dataBaseHelper = new DataBaseHelper(context);
    }


    @Override
    public void register(String userName_Register, String email_Register, String password_Register, AuthCallback callback) {

        // Validar datos vacíos
        if (userName_Register.isEmpty() || email_Register.isEmpty() || password_Register.isEmpty()) {
            callback.onError("Tienes que completar todos los datos para poder continuar");
            return;
        }

        // Registrar en Firebase
        firebaseService.getAuth().createUserWithEmailAndPassword(email_Register, password_Register)
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {

                        FirebaseUser user = firebaseService.getAuth().getCurrentUser();

                        if (user != null) {

                            UserProfileChangeRequest profileUpdates =
                                    new UserProfileChangeRequest.Builder()
                                            .setDisplayName(userName_Register)
                                            .build();

                            user.updateProfile(profileUpdates).addOnCompleteListener(taskProfile -> {

                                if (taskProfile.isSuccessful()) {

                                    // Guardar tambien en SqLite
                                    boolean inserted = dataBaseHelper.insertUsers(
                                            userName_Register,
                                            email_Register,
                                            password_Register
                                    );
                                    if (inserted) {
                                        callback.onSuccess();
                                    } else {
                                        callback.onError("Usuario creado en Firebase pero falló guardarlo en SQLite");
                                    }
                                } else {
                                    callback.onError("Usuario creado pero falló guardar el nombre.");
                                }
                            });
                        }
                    } else {
                        Exception e = task.getException();
                        String errorMessage = "Error al registrarse";
                        if (e instanceof com.google.firebase.auth.FirebaseAuthUserCollisionException) {
                            errorMessage = "El email ya fue registrado";
                        } else if (e != null) {
                            errorMessage = e.getMessage();
                        }
                        callback.onError(errorMessage);
                    }
                });
    }

    @Override
    public void login(String email, String password, AuthCallback callback) {
        // Primero revisamos que no nos hayan pasado datos vacíos.
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            callback.onError("El correo y la clave no pueden estar vacíos");
            return;
        }

        // Le pedimos a Firebase que intente iniciar sesión con el correo y clave.
        firebaseService.getAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    // Cuando Firebase termina, revisamos si le fue bien o mal.
                    if (task.isSuccessful()) {
                        // ¡Éxito! Avisamos al mensajero.
                        callback.onSuccess();
                    } else {
                        // Error. Sacamos el mensaje de por qué falló (ej: clave incorrecta).
                        String errorMessage = task.getException() != null ? 
                                task.getException().getMessage() : "Fallo en la autenticación";
                        callback.onError(errorMessage);
                    }
                });
    }

    @Override
    public FirebaseUser getCurrentUser() {
        // Le preguntamos a Firebase: "¿Hay alguien conectado ahora?"
        return firebaseService.getAuth().getCurrentUser();
    }

    @Override
    public void logout() {
        // Le decimos a Firebase que cierre la sesión del usuario.
        firebaseService.getAuth().signOut();
    }
}
