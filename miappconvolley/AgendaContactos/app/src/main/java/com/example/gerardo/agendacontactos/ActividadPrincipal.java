package com.example.gerardo.agendacontactos;


import android.app.Dialog;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class ActividadPrincipal extends AppCompatActivity {


    Spinner tipoimagen;
    Spinner categoria;
    String[] tiposdeimagen = {"Imagen a color","Imagen blanco y negro"};
    String[] categorias = {"abstract","animals","business","cats","city","food","nightlife","fashion","people","nature","sports","technics","transport"};

    int tipodeimagenseleccionada=0;
    int categoriaseleccionada=0;


    RequestQueue request;

    Button btn;

    SeekBar seekancho;
    TextView lbancho;
    SeekBar seekalto;
    TextView lbalto;

    EditText txttexto;


    int alto=0,ancho =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);



        tipoimagen = (Spinner) findViewById(R.id.spinnertipo);
        categoria = (Spinner) findViewById(R.id.spinnercategoria);

        ArrayAdapter<String> adaptador1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tiposdeimagen);
        tipoimagen.setAdapter(adaptador1);
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categorias);
        categoria.setAdapter(adaptador2);




       categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               categoriaseleccionada = i;
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

        tipoimagen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipodeimagenseleccionada = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        seekancho = (SeekBar) findViewById(R.id.seekbarancho);
         lbancho = (TextView)findViewById(R.id.lbancho);
         seekalto = (SeekBar) findViewById(R.id.seekBar2alto);
         lbalto = (TextView)findViewById(R.id.lbalto);


        seekancho.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                lbancho.setText("Ancho:"+String.valueOf(progress));
                ancho = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });


        seekalto.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                lbalto.setText("Alto:"+String.valueOf(progress));
                alto = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });






        btn = (Button)findViewById(R.id.btnimagen);
      btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             // Toast.makeText(getBaseContext(),"Entro",Toast.LENGTH_LONG).show();
              mostrarimagen();
          }
      });




      txttexto = (EditText)findViewById(R.id.txttexto);



    }

    private String url = "";
    public void mostrarimagen(){
        final Dialog dialog = new Dialog(ActividadPrincipal.this);
        dialog.setContentView(R.layout.activity_actividadimagen);


        final ImageView img = dialog.findViewById(R.id.imagen);

        try {

            request = Volley.newRequestQueue(getApplicationContext());

            url = "http://lorempixel.com/";

            if(tipodeimagenseleccionada==1){
                url+="g/";
            }

            url+=ancho+"/"+alto+"/";

            url+=categorias[categoriaseleccionada];

            if(txttexto.getText().length()>0){
                url+="/"+txttexto.getText()+"/";
            }




            ImageRequest request = new ImageRequest(url,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            //Toast.makeText(getBaseContext(), "Entro", Toast.LENGTH_LONG).show();
                            Toast.makeText(getBaseContext(),url,Toast.LENGTH_LONG).show();
                            img.setImageBitmap(response);
                        }
                    }, 0, 0, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            //imagen.setImageResource(R.drawable.image_load_error);
                            Toast.makeText(getBaseContext(), "Error al Cargar la Imagen", Toast.LENGTH_LONG).show();
                        }
                    });

            this.request.add(request); // a;adimos la peticion a la cola

        }catch (Exception err){
            Toast.makeText(getBaseContext(), err.getMessage(), Toast.LENGTH_LONG).show();
        }





        dialog.show();
    }






}
