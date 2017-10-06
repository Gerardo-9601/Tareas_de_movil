package com.example.gerardo.permisos;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button btnllamar;
    private static final int SOLICITUD_PERMISO_CALL_PHONE = 1;
    private Intent intentLLamada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       permiso();

    }


    public void permiso(){
        btnllamar = (Button)findViewById(R.id.btnpermiso1);
        btnllamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intentLLamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "4451228560"));
                 ///PERMISO CONCENDIDO
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intentLLamada);
                    Toast.makeText(MainActivity.this, "1 Permiso Concedido", Toast.LENGTH_SHORT).show();

                } else {
                    solicitarPermisoHacerLlamada();
                }

            }
        });
    }

    private void solicitarPermisoHacerLlamada() {
        //Pedimos el permiso o los permisos con un cuadro de dialogo del sistema
        solicitarPermisoHacerLlamada(Manifest.permission.CALL_PHONE, "Sin el permiso" +
                        " no puede realizar llamadas.",
                SOLICITUD_PERMISO_CALL_PHONE, this);

        Toast.makeText(MainActivity.this, "Solicitando Permiso de LLamar", Toast.LENGTH_SHORT).show();
    }

    public static void solicitarPermisoHacerLlamada(final String permiso, String justificacion,
                                        final int requestCode, final Activity actividad) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(actividad,
                permiso)){
            new AlertDialog.Builder(actividad)
                    .setTitle("Solicitud de permiso")
                    .setMessage(justificacion)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            ActivityCompat.requestPermissions(actividad,
                                    new String[]{permiso}, requestCode);
                        }})
                    .show();
        } else {
            ActivityCompat.requestPermissions(actividad,
                    new String[]{permiso}, requestCode);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == SOLICITUD_PERMISO_CALL_PHONE) {

            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Realizamos la accion
                startActivity(intentLLamada);
                Toast.makeText(MainActivity.this, "Permiso Concedido", Toast.LENGTH_SHORT).show();
            } else {
                //1-Seguimos el proceso de ejecucion sin esta accion: Esto lo recomienda Google
                //2-Cancelamos el proceso actual
                //3-Salimos de la aplicacion
                Toast.makeText(MainActivity.this, "Permiso No Concedido", Toast.LENGTH_SHORT).show();
            }
        }
    }




}
