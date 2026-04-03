package co.edu.ue.finalproject.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import co.edu.ue.finalproject.domain.repository.AuthRepository;
import co.edu.ue.finalproject.domain.usecase.LoginUseCase;

public class LoginViewModel extends ViewModel {
    private final LoginUseCase loginUseCase;
    
    private final MutableLiveData<Boolean> _loginSuccess = new MutableLiveData<>();
    public LiveData<Boolean> loginSuccess = _loginSuccess;

    private final MutableLiveData<String> _errorMessage = new MutableLiveData<>();
    public LiveData<String> errorMessage = _errorMessage;

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading = _isLoading;

    public LoginViewModel(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    public void login(String email, String password) {
        _isLoading.setValue(true);
        loginUseCase.execute(email, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess() {
                _isLoading.setValue(false);
                _loginSuccess.setValue(true);
            }

            @Override
            public void onError(String message) {
                _isLoading.setValue(false);
                _errorMessage.setValue(message);
            }
        });
    }
}
