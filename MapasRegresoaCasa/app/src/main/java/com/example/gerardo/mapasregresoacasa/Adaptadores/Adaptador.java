package com.example.gerardo.mapasregresoacasa.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gerardo.mapasregresoacasa.Pojos.Ruta;
import com.example.gerardo.mapasregresoacasa.R;

import java.util.List;


/**
 * Created by Gerardo on 17/03/2018.
 */

public class Adaptador extends BaseAdapter {

    private Context contexto;
    private List<Ruta> listarutas;

    public Adaptador(Context contexto, List<Ruta> listarutas){
        this.contexto=contexto;
        this.listarutas = listarutas;
    }

    @Override
    public int getCount() {
        return listarutas.size();
    }

    @Override
    public Object getItem(int i) {
        return listarutas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listarutas.get(i).getID();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {


        View v = convertView;

        if(null == v) {
            LayoutInflater inflater = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.itemlistview, null);
        }

        ImageView imagen = v.findViewById(R.id.lbimagen);
        TextView textvieworigen = v.findViewById(R.id.lborigen);
        TextView textviewdestino = v.findViewById(R.id.lbdestino);

        imagen.setImageResource(R.mipmap.googlemaps);
        textvieworigen.setText(listarutas.get(i).getOrigen());
        textviewdestino.setText(listarutas.get(i).getDestino());

        return v;
    }
}
