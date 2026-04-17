package co.edu.ue.finalproject.domain.usecase;

import co.edu.ue.finalproject.domain.repository.TurnoRepository;

public class ObtenerTurnosUseCase {
    private final TurnoRepository repository;

    public ObtenerTurnosUseCase(TurnoRepository repository) {
        this.repository = repository;
    }

    public void ejecutar(TurnoRepository.TurnoCallback callback) {
        repository.obtenerTurnos(callback);
    }
}
