package co.edu.ue.finalproject.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import co.edu.ue.finalproject.data.model.UsuarioDTO;
import co.edu.ue.finalproject.domain.repository.UserRepository;
import co.edu.ue.finalproject.domain.usecase.ObtenerUsuariosUseCase;

public class UserViewModel extends ViewModel {
    private final MutableLiveData<List<UsuarioDTO>> usersLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private final ObtenerUsuariosUseCase obtenerUsuariosUseCase;

    public UserViewModel(ObtenerUsuariosUseCase obtenerUsuariosUseCase) {
        this.obtenerUsuariosUseCase = obtenerUsuariosUseCase;
    }

    public LiveData<List<UsuarioDTO>> getUsers() {
        return usersLiveData;
    }

    public void cargarUsuarios() {
        obtenerUsuariosUseCase.ejecutar(new UserRepository.UsersCallback() {
            @Override
            public void onSuccess(List<UsuarioDTO> usuarios) {
                usersLiveData.postValue(usuarios);
            }

            @Override
            public void onError(String mensaje) {
                errorLiveData.postValue(mensaje);
            }
        });
    }
}
