package co.edu.ue.finalproject.domain.usecase;

import com.google.firebase.auth.FirebaseUser;
import co.edu.ue.finalproject.domain.repository.AuthRepository;

public class CheckSessionUseCase {
    private final AuthRepository repository;

    public CheckSessionUseCase(AuthRepository repository) {
        this.repository = repository;
    }

    public FirebaseUser execute() {
        return repository.getCurrentUser();
    }
}
