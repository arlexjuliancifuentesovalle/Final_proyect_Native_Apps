package co.edu.ue.finalproject.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import co.edu.ue.finalproject.domain.usecase.LoginUseCase;
import co.edu.ue.finalproject.domain.usecase.ObtenerPagosUseCase;
import co.edu.ue.finalproject.domain.usecase.RegisterUseCase;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;
    private final ObtenerPagosUseCase obtenerPagosUseCase;


    public ViewModelFactory(LoginUseCase loginUseCase, RegisterUseCase registerUseCase, ObtenerPagosUseCase obtenerPagosUseCase) {
        this.loginUseCase = loginUseCase;
        this.registerUseCase = registerUseCase;
        this.obtenerPagosUseCase = obtenerPagosUseCase;
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
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}