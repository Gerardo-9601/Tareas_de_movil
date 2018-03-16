package com.example.gerardo.myapplication;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gerardo.myapplication.Model.Daoblockllamadas;
import com.example.gerardo.myapplication.Pojos.Blockllamadas;

import java.util.ArrayList;
import java.util.List;

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";

    private ViewStub stubList;
    private ListView listView;
    private ListViewAdapter2 ListViewAdapter;
    private List<Blockllamadas> listallamadas;

    private Blockllamadas llamada;

    private int operacion = 0;
    private int indice = 0;

    String operaciones[] =
            new String[]
                    {"Borrar"};

    public void  btnList_click(){
        AlertDialog dialog =
                new AlertDialog.Builder(getContext())
                        .setTitle("Cuadro de dialogo")
                        .setIcon(R.mipmap.ic_launcher)
                        .setItems(operaciones, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(operaciones[which].equalsIgnoreCase(operaciones[0])){
                                    confirmacion();
                                }

                                dialog.dismiss();
                            }
                        })
                        .create();

        dialog.show();
    }


    private void botonflotante(View view){
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAdapters();
            }
        });

    }

    public void confirmacion(){

        AlertDialog dialog =
                new AlertDialog.Builder(getContext())
                        .setTitle("Borrar")
                        .setIcon(android.R.drawable.ic_delete)
                        .setMessage("Esta seguro de Borrar el Registro?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                               eliminar(listallamadas.get(indice).getID()+"");

                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Toast.makeText(getContext(),"Cancelar",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();

        dialog.show();

    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment, container, false);


        stubList = (ViewStub) view.findViewById(R.id.stub_list1);


        //inflar ViewStub antes de obtener la vista
        stubList.inflate();


        listView = (ListView) view.findViewById(R.id.mylistview);


        clic();


        setAdapters();
        botonflotante(view);

        return  view;
    }

    public void clic(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                indice = i;
                btnList_click();
            }
        });
    }

    private void setAdapters() {
        Daoblockllamadas daollamadas = new Daoblockllamadas(getContext());
        listallamadas = new ArrayList<>();
        listallamadas = daollamadas.getllamadas();

        ListViewAdapter = new ListViewAdapter2(getContext(), R.layout.list_item, daollamadas.getllamadas());
        listView.setAdapter(ListViewAdapter);

    }

    public void eliminar(String id){
        Daoblockllamadas dao = new Daoblockllamadas(getContext());
        if(dao.delete(id)>1){
            //  Toast.makeText(getContext(),getString(R.string.notanoeliminada)+"",Toast.LENGTH_LONG).show();
        }else{
            setAdapters();
            // Toast.makeText(getContext(),getString(R.string.notaeliminada)+"",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        setAdapters();
    }

    @Override
    public void onPause(){
        super.onPause();
        setAdapters();
    }



}
