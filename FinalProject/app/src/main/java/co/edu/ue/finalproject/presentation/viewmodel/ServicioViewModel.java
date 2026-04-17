package co.edu.ue.finalproject.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import co.edu.ue.finalproject.data.model.ServicioDTO;
import co.edu.ue.finalproject.domain.repository.ServicioRepository;
import co.edu.ue.finalproject.domain.usecase.ObtenerServiciosUseCase;

public class ServicioViewModel extends ViewModel {
    private final ObtenerServiciosUseCase obtenerServiciosUseCase;
    private final MutableLiveData<List<ServicioDTO>> servicios = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    public ServicioViewModel(ObtenerServiciosUseCase obtenerServiciosUseCase) {
        this.obtenerServiciosUseCase = obtenerServiciosUseCase;
    }

    public LiveData<List<ServicioDTO>> getServicios() {
        return servicios;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void cargarServicios() {
        obtenerServiciosUseCase.execute(new ServicioRepository.ServicioCallback() {
            @Override
            public void onSuccess(List<ServicioDTO> listaServicios) {
                servicios.postValue(listaServicios);
            }

            @Override
            public void onError(String message) {
                error.postValue(message);
            }
        });
    }
}
