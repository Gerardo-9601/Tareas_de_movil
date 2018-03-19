package com.example.gerardo.mapasregresoacasa.Adaptadores;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gerardo.mapasregresoacasa.Pojos.Ruta;
import com.example.gerardo.mapasregresoacasa.R;

import java.util.List;

/**
 * Created by Gerardo on 17/03/2018.
 */

public class ListViewAdapter extends ArrayAdapter<Ruta> {
    public ListViewAdapter(Context context, int resource, List<Ruta> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(null == v) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.itemlistview, null);
        }
        final Ruta ruta = getItem(position);
       // ImageView imagen = v.findViewById(R.id.lbimagen);
        TextView textvieworigen = v.findViewById(R.id.lborigen);
        TextView textviewdestino = v.findViewById(R.id.lbdestino);

       // imagen.setImageResource(R.mipmap.googlemaps);
        textvieworigen.setText("Origen:"+ruta.getOrigen());
        textviewdestino.setText("Destino:"+ruta.getDestino());

        return v;
    }

}
