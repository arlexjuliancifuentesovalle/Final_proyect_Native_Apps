package co.edu.ue.finalproject.presentation.ui;

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
import co.edu.ue.finalproject.domain.usecase.RegisterUseCase;
import co.edu.ue.finalproject.presentation.viewmodel.RegisterViewModel;
import co.edu.ue.finalproject.presentation.viewmodel.ViewModelFactory;

public class RegisterActivity extends AppCompatActivity {

    //declare attributes
    private EditText etUserName_Register;
    private EditText etEmail_Register;
    private EditText etPassword_Register;
    private Button btnRegister;
    private Button btnVolver;
    private RegisterViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setupViewInsets();
        initDependencies();
        initViews();
        setupObservers();
        setupListeners();
    }

    //Este método ajusta el diseño para que el contenido no quede debajo de la barra de notificaciones.
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
        AuthRepository repository = new AuthRepositoryImpl(fireBaseService, this);
        LoginUseCase loginUseCase = new LoginUseCase(repository);
        RegisterUseCase registerUseCase = new RegisterUseCase(repository);

        // Creamos una fábrica para poder darle el "Caso de Uso" a nuestro ViewModel.
        ViewModelFactory factory = new ViewModelFactory(loginUseCase, registerUseCase);

        // Obtenemos el ViewModel, que es el encargado de procesar el login.
        viewModel = new ViewModelProvider(this, factory).get(RegisterViewModel.class);
    }

    private void initViews(){
        etUserName_Register = findViewById(R.id.etUserName);
        etEmail_Register = findViewById(R.id.etEmail);
        etPassword_Register = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister_Register);
        btnVolver = findViewById(R.id.btnVolver_Register);
    }

    private void setupObservers() {

        // if register is exit change login
        viewModel.registerSuccess.observe(this, success -> {
            if (success) {
                Intent login = new Intent(this, MainActivity.class);
                startActivity(login);
                Toast.makeText(this, "¡Bienvenido! ahora puedes iniciar sesión.", Toast.LENGTH_SHORT).show();
            }
        });


        viewModel.errorMessage.observe(this, message -> {
            if (message != null && !message.isEmpty()) {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        });


    }

    private void setupListeners(){
        btnRegister.setOnClickListener(v -> {
            //Get data type user
            String username = etUserName_Register.getText().toString().trim();
            String email = etEmail_Register.getText().toString().trim();
            String password = etPassword_Register.getText().toString().trim();

            viewModel.register(username, email, password);
        });

        btnVolver.setOnClickListener(v -> finish());
    }



}