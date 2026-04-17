package co.edu.ue.finalproject.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import co.edu.ue.finalproject.data.model.PagosDTO;
import co.edu.ue.finalproject.domain.repository.PagosRepository;
import co.edu.ue.finalproject.domain.usecase.ObtenerPagosUseCase;

public class PagosViewModel extends ViewModel {
    private final MutableLiveData<List<PagosDTO>> pagosLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private final ObtenerPagosUseCase obtenerPagosUseCase;

    public PagosViewModel(ObtenerPagosUseCase obtenerPagosUseCase) {
        this.obtenerPagosUseCase = obtenerPagosUseCase;
    }

    public LiveData<List<PagosDTO>> getPagos() {
        return pagosLiveData;
    }

    public void cargarPagos() {
        obtenerPagosUseCase.ejecutar(new PagosRepository.PagosCallback() {
            @Override
            public void onSuccess(List<PagosDTO> lista) {
                pagosLiveData.postValue(lista);
            }

            @Override
            public void onError(String mensaje) {
                errorLiveData.postValue(mensaje);
            }
        });
    }
}