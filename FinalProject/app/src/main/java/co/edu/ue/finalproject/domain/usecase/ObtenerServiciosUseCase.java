package co.edu.ue.finalproject.domain.usecase;

import co.edu.ue.finalproject.domain.repository.ServicioRepository;

public class ObtenerServiciosUseCase {
    private final ServicioRepository repository;

    public ObtenerServiciosUseCase(ServicioRepository repository) {
        this.repository = repository;
    }

    public void execute(ServicioRepository.ServicioCallback callback) {
        repository.getServicios(callback);
    }
}
