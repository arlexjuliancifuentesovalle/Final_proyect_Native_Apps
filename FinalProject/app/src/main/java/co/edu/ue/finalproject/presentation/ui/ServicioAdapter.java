package co.edu.ue.finalproject.presentation.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import co.edu.ue.finalproject.data.model.ServicioDTO;

public class ServicioAdapter extends ArrayAdapter<ServicioDTO> {
    public ServicioAdapter(Context context, List<ServicioDTO> servicios) {
        super(context, 0, servicios);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ServicioDTO servicio = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        TextView text1 = convertView.findViewById(android.R.id.text1);
        TextView text2 = convertView.findViewById(android.R.id.text2);

        if (servicio != null) {
            text1.setText(servicio.getTipsNombreServicio());
            text2.setText(servicio.getTipsDescripcion());
        }

        return convertView;
    }
}
