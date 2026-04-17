package co.edu.ue.finalproject.domain.usecase;

import co.edu.ue.finalproject.domain.repository.PagosRepository;

public class ObtenerPagosUseCase {//para pedir datos al repositoy
    private PagosRepository repository;
    public ObtenerPagosUseCase(PagosRepository repository){
        this.repository = repository;
    }
    public void ejecutar(PagosRepository.PagosCallback callback){
        repository.obtenerPagos(callback);
    }
}
