package co.edu.ue.finalproject.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import co.edu.ue.finalproject.domain.usecase.LoginUseCase;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final LoginUseCase loginUseCase;

    public ViewModelFactory(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(loginUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
