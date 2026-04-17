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
import co.edu.ue.finalproject.data.remote.UserApiService;
import co.edu.ue.finalproject.data.repository.UserRepositoryImpl;
import co.edu.ue.finalproject.domain.repository.UserRepository;
import co.edu.ue.finalproject.domain.usecase.ObtenerUsuariosUseCase;
import co.edu.ue.finalproject.presentation.viewmodel.UserViewModel;
import co.edu.ue.finalproject.presentation.viewmodel.ViewModelFactory;

public class ClientesFragment extends Fragment {

    private ListView listViewClientes;
    private ClienteAdapter adapter;
    private UserViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_clientes, container, false);

        listViewClientes = view.findViewById(R.id.lvClientes);
        adapter = new ClienteAdapter(getContext(), new ArrayList<>());
        listViewClientes.setAdapter(adapter);

        initViewModel();
        setupObservers();

        viewModel.cargarUsuarios();

        return view;
    }

    private void initViewModel() {
        UserApiService apiService = RetrofitCliente.getUserApiService();
        UserRepository repository = new UserRepositoryImpl(apiService);
        ObtenerUsuariosUseCase useCase = new ObtenerUsuariosUseCase(repository);
        
        ViewModelFactory factory = new ViewModelFactory(null, null, null, null, useCase, null);
        viewModel = new ViewModelProvider(this, factory).get(UserViewModel.class);
    }

    private void setupObservers() {
        viewModel.getUsers().observe(getViewLifecycleOwner(), lista -> {
            if (lista != null) {
                adapter = new ClienteAdapter(getContext(), lista);
                listViewClientes.setAdapter(adapter);
            }
        });
    }
}
