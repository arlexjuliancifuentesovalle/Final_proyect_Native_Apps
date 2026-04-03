package co.edu.ue.finalproject.presentation.ui;

// Estas son las "herramientas" que importamos para que nuestra app funcione.
// Por ejemplo, para usar botones, textos, o conectarnos con Firebase.
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import co.edu.ue.finalproject.R;
import co.edu.ue.finalproject.data.remote.FireBaseService;
import co.edu.ue.finalproject.data.repository.AuthRepositoryImpl;
import co.edu.ue.finalproject.domain.repository.AuthRepository;
import co.edu.ue.finalproject.domain.usecase.LoginUseCase;
import co.edu.ue.finalproject.presentation.viewmodel.LoginViewModel;
import co.edu.ue.finalproject.presentation.viewmodel.ViewModelFactory;

// Esta es la clase principal de nuestra pantalla de Inicio (Login).
// AppCompatActivity es como una plantilla de Google para crear pantallas.
public class MainActivity extends AppCompatActivity {

    // Declaramos las variables para los elementos que el usuario verá y tocará.
    private EditText etEmail, etPassword; // Campos para escribir correo y clave.
    private Button btnLogin, btnRegister; // Botones para entrar o registrarse.
    private LoginViewModel viewModel;     // El "Cerebro" de la pantalla que maneja los datos.

    // El método onCreate es lo primero que se ejecuta cuando se abre la app.
    // Es como el "encendido" de la pantalla.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Esto permite que la app use toda la pantalla (incluyendo la barra de estado).
        EdgeToEdge.enable(this);
        
        // Aquí le decimos a la app cuál es su diseño visual (el archivo XML).
        setContentView(R.layout.activity_main);
        
        // Llamamos a nuestros métodos organizados para que todo se configure en orden.
        setupViewInsets();    // Ajusta los márgenes para no tapar la hora o batería.
        initDependencies();   // Prepara las conexiones (Firebase, Casos de Uso).
        initViews();          // Conecta el código con los botones del diseño.
        setupObservers();     // Se queda "escuchando" si el login funciona o falla.
        setupListeners();     // Configura qué pasa cuando el usuario hace clic.
    }

    // Este método ajusta el diseño para que el contenido no quede debajo de la barra de notificaciones.
    private void setupViewInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Aquí preparamos la "maquinaria" interna. 
    // Siguiendo el orden: Servicio de Firebase -> Repositorio -> Caso de Uso -> ViewModel.
    private void initDependencies() {
        FireBaseService fireBaseService = new FireBaseService();
        AuthRepository repository = new AuthRepositoryImpl(fireBaseService);
        LoginUseCase loginUseCase = new LoginUseCase(repository);
        
        // Creamos una fábrica para poder darle el "Caso de Uso" a nuestro ViewModel.
        ViewModelFactory factory = new ViewModelFactory(loginUseCase);
        
        // Obtenemos el ViewModel, que es el encargado de procesar el login.
        viewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);
    }

    // Aquí enlazamos las variables de Java con los elementos que dibujamos en el XML (layout).
    private void initViews() {
        etEmail = findViewById(R.id.eteEmailLogin);   // El cuadro de texto del correo.
        etPassword = findViewById(R.id.etpPasswordLogin); // El cuadro de la contraseña.
        btnLogin = findViewById(R.id.btnLogin);       // El botón de entrar.
        btnRegister = findViewById(R.id.btnRegister); // El botón de ir a registro.
    }

    // Los Observadores son como "centinelas". Se quedan esperando a que algo cambie en el ViewModel.
    private void setupObservers() {
        
        // Si el login fue exitoso (loginSuccess cambia a true), avisamos y cambiamos de pantalla.
        viewModel.loginSuccess.observe(this, success -> {
            if (success) {
                Toast.makeText(this, "¡Bienvenido! Datos correctos.", Toast.LENGTH_SHORT).show();
                navigateToHome(); // Función para ir a la pantalla principal.
            }
        });

        // Si ocurre un error, mostramos el mensaje que nos devuelva Firebase.
        viewModel.errorMessage.observe(this, message -> {
            if (message != null) {
                Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show();
            }
        });

        // Mientras la app está cargando (esperando a Firebase), desactivamos el botón para evitar múltiples clics.
        viewModel.isLoading.observe(this, isLoading -> {
            btnLogin.setEnabled(!isLoading);
        });
    }

    // Este método se encargará de llevarnos a la pantalla de Inicio después del login.
    private void navigateToHome() {
        // Por ahora solo muestra un mensaje, pero aquí pondrás el código para saltar de pantalla.
        // Intent intent = new Intent(this, HomeActivity.class);
        // startActivity(intent);
        // finish(); // Cerramos el login para que no pueda volver atrás.
        Toast.makeText(this, "Cambiando a la pantalla principal...", Toast.LENGTH_SHORT).show();
    }

    // Este método nos llevará a la pantalla de creación de cuenta.
    private void navigateToRegister() {
        // Cuando crees tu pantalla de registro, aquí pondrás el código para abrirla.
        Toast.makeText(this, "Abriendo formulario de registro...", Toast.LENGTH_SHORT).show();
    }

    // Aquí configuramos las acciones de los botones.
    private void setupListeners() {
        
        // Cuando el usuario toca el botón de Login:
        btnLogin.setOnClickListener(v -> {
            // Sacamos el texto que escribió el usuario en los cuadros.
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            
            // Le pedimos al "Cerebro" (ViewModel) que intente hacer el login.
            viewModel.login(email, password);
        });

        // Cuando el usuario toca el botón de Registro:
        btnRegister.setOnClickListener(v -> {
            navigateToRegister(); // Llamamos a la función de navegar.
        });
    }
}
