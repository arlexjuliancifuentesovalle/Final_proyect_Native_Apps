package co.edu.ue.finalproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import co.edu.ue.finalproject.domain.usecase.LoginUseCase;
import co.edu.ue.finalproject.domain.usecase.RegisterUseCase;
import co.edu.ue.finalproject.presentation.viewmodel.LoginViewModel;
import co.edu.ue.finalproject.presentation.viewmodel.ViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar los casos de uso necesarios para esta pantalla
        // implementaciones de los repositorios aquí
        LoginUseCase loginUseCase = new LoginUseCase(null);
        RegisterUseCase registerUseCase = new RegisterUseCase(null);

        // null como tercer parámetro
        // porque MainActivity no necesita el caso de uso de Pagos
        ViewModelFactory factory = new ViewModelFactory(loginUseCase, registerUseCase, null);

        // Obtener el ViewModel
        loginViewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);
    }
}