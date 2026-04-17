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
import co.edu.ue.finalproject.data.remote.TurnosApiService;
import co.edu.ue.finalproject.data.repository.TurnoRepositoryImpl;
import co.edu.ue.finalproject.domain.repository.TurnoRepository;
import co.edu.ue.finalproject.domain.usecase.ObtenerPagosUseCase;
import co.edu.ue.finalproject.domain.usecase.ObtenerTurnosUseCase;
import co.edu.ue.finalproject.presentation.viewmodel.TurnoViewModel;
import co.edu.ue.finalproject.presentation.viewmodel.ViewModelFactory;

public class TurnoFragment extends Fragment {

    private ListView listViewTurnos;
    private TurnoAdapter adapter;
    private TurnoViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_turnos, container, false);

        listViewTurnos = view.findViewById(R.id.lvTurnos);
        adapter = new TurnoAdapter(getContext(), new ArrayList<>());
        listViewTurnos.setAdapter(adapter);

        initViewModel();
        setupObservers();

        viewModel.cargarTurnos();

        return view;
    }

    private void initViewModel() {
        TurnosApiService apiService = RetrofitCliente.getTurnosApiService();
        TurnoRepository repository = new TurnoRepositoryImpl(apiService);
        ObtenerTurnosUseCase useCaseTurnos = new ObtenerTurnosUseCase(repository);
        // Agregamos el sexto parámetro null
        ViewModelFactory factory = new ViewModelFactory(null, null, null, useCaseTurnos, null, null);
        viewModel = new ViewModelProvider(this, factory).get(TurnoViewModel.class);
    }

    private void setupObservers() {
        viewModel.getTurnos().observe(getViewLifecycleOwner(), lista -> {
            if (lista != null) {
                adapter = new TurnoAdapter(getContext(), lista);
                listViewTurnos.setAdapter(adapter);
            } else {
                Toast.makeText(getContext(), "Error al cargar turnos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
