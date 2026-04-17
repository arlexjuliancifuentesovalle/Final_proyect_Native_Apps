package co.edu.ue.finalproject.presentation.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import co.edu.ue.finalproject.data.model.PagosDTO;

public class PagosAdapter extends ArrayAdapter<PagosDTO> {
    public PagosAdapter(Context context, List<PagosDTO> pagos) {
        super(context, 0, pagos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //  dato para esta posición
        PagosDTO pago = getItem(position);

        // Se infla la vista si no se está reusando
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        // Busca los textos
        TextView text1 = convertView.findViewById(android.R.id.text1);
        TextView text2 = convertView.findViewById(android.R.id.text2);

        // Seteo de los datos de la API (tippTipoPago y tippDescripcion)
        if (pago != null) {
            text1.setText(pago.getTippTipoPago());
            text2.setText(pago.getTippDescripcion());
        }

        return convertView;
    }
}

