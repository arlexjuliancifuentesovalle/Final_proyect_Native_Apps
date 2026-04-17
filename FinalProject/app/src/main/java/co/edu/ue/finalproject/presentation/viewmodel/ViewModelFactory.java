package co.edu.ue.finalproject.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import co.edu.ue.finalproject.domain.usecase.LoginUseCase;
import co.edu.ue.finalproject.domain.usecase.ObtenerPagosUseCase;
import co.edu.ue.finalproject.domain.usecase.ObtenerTurnosUseCase;
import co.edu.ue.finalproject.domain.usecase.ObtenerServiciosUseCase;
import co.edu.ue.finalproject.domain.usecase.ObtenerUsuariosUseCase;
import co.edu.ue.finalproject.domain.usecase.RegisterUseCase;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;
    private final ObtenerPagosUseCase obtenerPagosUseCase;
    private final ObtenerTurnosUseCase obtenerTurnosUseCase;
    private final ObtenerUsuariosUseCase obtenerUsuariosUseCase;
    private final ObtenerServiciosUseCase obtenerServiciosUseCase;

    public ViewModelFactory(LoginUseCase loginUseCase, RegisterUseCase registerUseCase,
                            ObtenerPagosUseCase obtenerPagosUseCase, ObtenerTurnosUseCase obtenerTurnosUseCase,
                            ObtenerUsuariosUseCase obtenerUsuariosUseCase, ObtenerServiciosUseCase obtenerServiciosUseCase) {
        this.loginUseCase = loginUseCase;
        this.registerUseCase = registerUseCase;
        this.obtenerPagosUseCase = obtenerPagosUseCase;
        this.obtenerTurnosUseCase = obtenerTurnosUseCase;
        this.obtenerUsuariosUseCase = obtenerUsuariosUseCase;
        this.obtenerServiciosUseCase = obtenerServiciosUseCase;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(loginUseCase);
        } else if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(registerUseCase);
        } else if (modelClass.isAssignableFrom(PagosViewModel.class)) {
            return (T) new PagosViewModel(obtenerPagosUseCase);
        } else if (modelClass.isAssignableFrom(TurnoViewModel.class)) {
            return (T) new TurnoViewModel(obtenerTurnosUseCase);
        } else if (modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(obtenerUsuariosUseCase);
        } else if (modelClass.isAssignableFrom(ServicioViewModel.class)) {
            return (T) new ServicioViewModel(obtenerServiciosUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}