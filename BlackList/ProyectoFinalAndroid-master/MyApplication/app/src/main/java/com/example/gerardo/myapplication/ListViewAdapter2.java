package com.example.gerardo.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gerardo.myapplication.Pojos.Blockllamadas;
import com.example.gerardo.myapplication.Pojos.Contacto;

import java.util.List;


public class ListViewAdapter2 extends ArrayAdapter<Blockllamadas> {
    public ListViewAdapter2(Context context, int resource, List<Blockllamadas> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(null == v) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item2, null);
        }
        final Blockllamadas llamada = getItem(position);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        TextView txttelefono = (TextView) v.findViewById(R.id.lbnumero);
        TextView txtfecha = (TextView) v.findViewById(R.id.lbfecha);
        TextView txthora = (TextView) v.findViewById(R.id.lbhora);

        txttelefono.setText("Telefono:"+llamada.getTelefono());
        txtfecha.setText("Fecha:"+llamada.getFecha());
        txthora.setText("Hora:"+llamada.getHora());

        return v;
    }
}
