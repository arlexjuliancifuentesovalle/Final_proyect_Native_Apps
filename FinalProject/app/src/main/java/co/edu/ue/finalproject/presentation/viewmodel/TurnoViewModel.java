package co.edu.ue.finalproject.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import co.edu.ue.finalproject.data.model.TurnoDTO;
import co.edu.ue.finalproject.domain.repository.TurnoRepository;
import co.edu.ue.finalproject.domain.usecase.ObtenerTurnosUseCase;

public class TurnoViewModel extends ViewModel {
    private final MutableLiveData<List<TurnoDTO>> turnosLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private final ObtenerTurnosUseCase obtenerTurnosUseCase;

    public TurnoViewModel(ObtenerTurnosUseCase obtenerTurnosUseCase) {
        this.obtenerTurnosUseCase = obtenerTurnosUseCase;
    }

    public LiveData<List<TurnoDTO>> getTurnos() {
        return turnosLiveData;
    }

    public void cargarTurnos() {
        obtenerTurnosUseCase.ejecutar(new TurnoRepository.TurnoCallback() {
            @Override
            public void onSuccess(List<TurnoDTO> lista) {
                turnosLiveData.postValue(lista);
            }

            @Override
            public void onError(String mensaje) {
                errorLiveData.postValue(mensaje);
            }
        });
    }
}
