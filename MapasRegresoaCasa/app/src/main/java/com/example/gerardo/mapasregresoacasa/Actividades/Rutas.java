package com.example.gerardo.mapasregresoacasa.Actividades;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gerardo.mapasregresoacasa.Adaptadores.ListViewAdapter;
import com.example.gerardo.mapasregresoacasa.Modelo.DaoRuta;
import com.example.gerardo.mapasregresoacasa.Pojos.Ruta;
import com.example.gerardo.mapasregresoacasa.R;

import java.util.ArrayList;
import java.util.List;

public class Rutas extends AppCompatActivity {

    ListView lista;
    List<Ruta> listarutas;
    private boolean bandera=false;
    private int indice=0;

    String operaciones[] =
            new String[]
                    {"Eliminar"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas);


        lista = findViewById(R.id.mylista);

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                indice=i;
                bandera=true;
                btnList_click();
                return false;
            }
        });



        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mapa = new Intent(getApplicationContext(),Mapa.class);
                indice = i;
                if (bandera == false) {
                    mapa.putExtra("Origen", listarutas.get(i).getOrigen());
                    mapa.putExtra("Destino", listarutas.get(i).getDestino());
                    startActivity(mapa);
                }
                bandera=false;
            }
        });



        /*
        listarutas = new ArrayList<Ruta>();
        listarutas.add(new Ruta("origen","destino"));
        listarutas.add(new Ruta("origen","destino"));
        listarutas.add(new Ruta("origen","destino"));
        listarutas.add(new Ruta("origen","destino"));
        listarutas.add(new Ruta("origen","destino"));
        listarutas.add(new Ruta("origen","destino"));
        listarutas.add(new Ruta("origen","destino"));
        listarutas.add(new Ruta("origen","destino"));
        listarutas.add(new Ruta("origen","destino"));
      */
        //DaoRuta dao = new DaoRuta(getBaseContext());
        //dao.Insert(new Ruta("20.119018, -101.196048","20.139418, -101.150785"));
        cargardatos("%");

    }

    @Override
    public void onPause(){
        super.onPause();
        cargardatos("%");
    }

    @Override
    public void onResume(){
        super.onResume();
        cargardatos("%");
    }

    private void cargardatos(String dato) {
        DaoRuta datorutas = new DaoRuta(getApplication());
        listarutas = new ArrayList<>();
        listarutas = datorutas.getRutas(dato);

        ListViewAdapter datos = new ListViewAdapter(getBaseContext(),R.layout.itemlistview ,listarutas);
        lista.setAdapter(datos);


    }



    public void  btnList_click(){
        AlertDialog dialog =
                new AlertDialog.Builder(this)
                        .setTitle("Cuadro de dialogo")
                        .setIcon(android.R.drawable.stat_sys_warning)
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



    public void confirmacion(){

        AlertDialog dialog =
                new AlertDialog.Builder(this)
                        .setTitle("Eliminar")
                        .setIcon(android.R.drawable.ic_delete)
                        .setMessage("Esta seguro de Eliminar la Ruta?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                DaoRuta dao = new DaoRuta(getBaseContext());
                                dao.delete(listarutas.get(indice).getID()+"");
                                cargardatos("%");
                                Toast.makeText(getApplicationContext(),"Dato Eliminado",Toast.LENGTH_SHORT).show();

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


    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent mapa = new Intent(getApplicationContext(),Mapa.class);
                startActivity(mapa);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu,menu);

        final MenuItem searchItem = menu.findItem(R.id.buscarRuta);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                 cargardatos("%"+newText+"%");
                return false;
            }
        });

        return true;
    }





}
