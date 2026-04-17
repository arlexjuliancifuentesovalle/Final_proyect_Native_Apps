package co.edu.ue.finalproject.presentation.ui;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import co.edu.ue.finalproject.data.model.TurnoDTO;

public class TurnoAdapter extends ArrayAdapter<TurnoDTO> {
    public TurnoAdapter(Context context, List<TurnoDTO> turnos) {
        super(context, 0, turnos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TurnoDTO turno = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        TextView text1 = convertView.findViewById(android.R.id.text1);
        TextView text2 = convertView.findViewById(android.R.id.text2);

        if (turno != null) {
            String info = "Fecha: " + turno.getTurFecha() + " - Hora: " + turno.getTurHora();
            text1.setText(info);
            
            if (turno.getUsuario() != null) {
                String user = "Usuario: " + turno.getUsuario().getFullName() + 
                             " (" + turno.getUsuario().getTiposUsuario().getTipuTipoUsuario() + ")";
                text2.setText(user);
            }
        }

        return convertView;
    }

}
