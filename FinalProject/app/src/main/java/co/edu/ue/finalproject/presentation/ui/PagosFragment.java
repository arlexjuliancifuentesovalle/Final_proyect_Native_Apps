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
import co.edu.ue.finalproject.data.remote.PagosApiService;
import co.edu.ue.finalproject.data.remote.RetrofitCliente;
import co.edu.ue.finalproject.data.repository.PagosRepositoryImpl;
import co.edu.ue.finalproject.domain.repository.PagosRepository;
import co.edu.ue.finalproject.domain.usecase.ObtenerPagosUseCase;
import co.edu.ue.finalproject.presentation.viewmodel.PagosViewModel;
import co.edu.ue.finalproject.presentation.viewmodel.ViewModelFactory;

public class PagosFragment extends Fragment {

    private ListView listViewPagos;
    private PagosAdapter adapter;
    private PagosViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // se infa el diseño que ya tenías para pagos
        View view = inflater.inflate(R.layout.activity_pagos, container, false);

        listViewPagos = view.findViewById(R.id.lvPagos);
        adapter = new PagosAdapter(getContext(), new ArrayList<>());
        listViewPagos.setAdapter(adapter);

        initViewModel();
        setupObservers();

        // Cargar datos
        viewModel.cargarPagos();

        return view;
    }

    private void initViewModel() {
        PagosApiService apiService = RetrofitCliente.getApiService();
        PagosRepository repository = new PagosRepositoryImpl(apiService);
        ObtenerPagosUseCase useCase = new ObtenerPagosUseCase(repository);
        // Agregamos el quinto parámetro null
        ViewModelFactory factory = new ViewModelFactory(null, null, useCase, null, null);
        viewModel = new ViewModelProvider(this, factory).get(PagosViewModel.class);
    }

    private void setupObservers() {
        viewModel.getPagos().observe(getViewLifecycleOwner(), listaDePagos -> {
            if (listaDePagos != null) {
                adapter = new PagosAdapter(getContext(), listaDePagos);
                listViewPagos.setAdapter(adapter);
            } else {
                Toast.makeText(getContext(), "No se encontraron datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
