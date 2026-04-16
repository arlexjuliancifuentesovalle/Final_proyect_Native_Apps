package co.edu.ue.finalproject.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import co.edu.ue.finalproject.domain.repository.AuthRepository;
import co.edu.ue.finalproject.domain.usecase.RegisterUseCase;

public class RegisterViewModel extends ViewModel {
    private final RegisterUseCase registerUseCase;

    // Estados observables para la UI
    private final MutableLiveData<Boolean> _registerSuccess = new MutableLiveData<>();
    public LiveData<Boolean> registerSuccess = _registerSuccess;

    private final MutableLiveData<String> _errorMessage = new MutableLiveData<>();
    public LiveData<String> errorMessage = _errorMessage;

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading = _isLoading;

    public RegisterViewModel(RegisterUseCase registerUseCase) {
        this.registerUseCase = registerUseCase;
    }

    public void register(String name, String email, String password) {
        _isLoading.setValue(true);

        registerUseCase.execute(name, email, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess() {
                _isLoading.setValue(false);
                _registerSuccess.setValue(true);
            }

            @Override
            public void onError(String message) {
                _isLoading.setValue(false);
                _errorMessage.setValue(message);
                if (message.contains("El email ya fue usado prueba con otro") ||
                        message.contains("email esta en uso")) {
                    _errorMessage.setValue("Este correo electrónico ya está registrado.");
                } else {
                    _errorMessage.setValue(message);
                }
            }
        });
    }
}