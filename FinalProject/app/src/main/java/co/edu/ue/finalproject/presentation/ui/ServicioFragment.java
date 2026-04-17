package co.edu.ue.finalproject.presentation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import co.edu.ue.finalproject.R;
import co.edu.ue.finalproject.data.remote.RetrofitCliente;
import co.edu.ue.finalproject.data.remote.ServicioApiService;
import co.edu.ue.finalproject.data.repository.ServicioRepositoryImpl;
import co.edu.ue.finalproject.domain.repository.ServicioRepository;
import co.edu.ue.finalproject.domain.usecase.ObtenerServiciosUseCase;
import co.edu.ue.finalproject.presentation.viewmodel.ServicioViewModel;
import co.edu.ue.finalproject.presentation.viewmodel.ViewModelFactory;

public class ServicioFragment extends Fragment {

    private ListView listViewServicios;
    private ServicioAdapter adapter;
    private ServicioViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_servicios, container, false);

        listViewServicios = view.findViewById(R.id.lvServicios);
        adapter = new ServicioAdapter(getContext(), new ArrayList<>());
        listViewServicios.setAdapter(adapter);

        initViewModel();
        setupObservers();

        viewModel.cargarServicios();

        return view;
    }

    private void initViewModel() {
        ServicioApiService apiService = RetrofitCliente.getServicioApiService();
        ServicioRepository repository = new ServicioRepositoryImpl(apiService);
        ObtenerServiciosUseCase useCase = new ObtenerServiciosUseCase(repository);
        
        // Pasamos null en los campos que no corresponden a Servicios
        ViewModelFactory factory = new ViewModelFactory(null, null, null, null, null, useCase);
        viewModel = new ViewModelProvider(this, factory).get(ServicioViewModel.class);
    }

    private void setupObservers() {
        viewModel.getServicios().observe(getViewLifecycleOwner(), listaDeServicios -> {
            if (listaDeServicios != null) {
                adapter = new ServicioAdapter(getContext(), listaDeServicios);
                listViewServicios.setAdapter(adapter);
            }
        });

        viewModel.getError().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
