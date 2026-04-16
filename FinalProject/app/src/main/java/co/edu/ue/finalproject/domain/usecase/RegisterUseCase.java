package co.edu.ue.finalproject.domain.usecase;

import co.edu.ue.finalproject.domain.repository.AuthRepository;

public class RegisterUseCase {

    private final AuthRepository repository;

    public RegisterUseCase(AuthRepository repository) {
        this.repository = repository;
    }

    public void execute(String userName_Register, String email_Register, String password_Register, AuthRepository.AuthCallback callback){
        repository.register(userName_Register, email_Register, password_Register, callback);
    }
}
