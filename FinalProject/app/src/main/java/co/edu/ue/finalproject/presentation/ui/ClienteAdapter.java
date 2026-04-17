package co.edu.ue.finalproject.presentation.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import co.edu.ue.finalproject.R;
import co.edu.ue.finalproject.data.model.UsuarioDTO;

public class ClienteAdapter extends ArrayAdapter<UsuarioDTO> {
    public ClienteAdapter(@NonNull Context context, @NonNull List<UsuarioDTO> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_cliente, parent, false);
        }

        UsuarioDTO usuario = getItem(position);

        TextView tvNombre = convertView.findViewById(R.id.tvNombreCliente);
        TextView tvTelefono = convertView.findViewById(R.id.tvTelefonoCliente);
        TextView tvTipo = convertView.findViewById(R.id.tvTipoUsuario);

        if (usuario != null) {
            tvNombre.setText(usuario.getFullName());
            tvTelefono.setText("Tel: " + usuario.getUsuTelefonoMovil());
            if (usuario.getTiposUsuario() != null) {
                tvTipo.setText("Rol: " + usuario.getTiposUsuario().getTipuTipoUsuario());
            }
        }

        return convertView;
    }
}
