package com.example.dios.futbolito;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    int a=0;

    int jugador1=0;
    int jugador2=0;

    int width = 0;
    int height=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView baloncito =  (ImageView)findViewById(R.id.baloncito);
      //  final ImageView porteria =  (ImageView)findViewById(R.id.porteria);




        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor==null){
            finish();
        }

        sensorEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                Display display = getWindowManager().getDefaultDisplay();
                 width = display.getWidth();  // obsoleto (deprecated)
                 height = display.getHeight();  // obsoleto (deprecated)


                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];


                int avanzar=5;


                if (x<-1){
                    do {
                        if(baloncito.getX()<=(width-baloncito.getWidth()-avanzar)) {
                            baloncito.setX(baloncito.getX() + avanzar);
                            gol();
                        }
                    }while (baloncito.getX()<100);


                }else if(x>1){
                    do {
                        if( baloncito.getX()>4) {
                            baloncito.setX(baloncito.getX() - avanzar);
                            gol();
                        }
                    }while (baloncito.getX()>150);

                }

                // eje y

                if(y<-1){
                    do{
                        if(baloncito.getY()>avanzar) {
                            baloncito.setY(baloncito.getY() - avanzar);
                            gol();
                        }
                }while (baloncito.getY()>100);

                }else if (y>1){
                    do {
                        if(baloncito.getY()<=(height-baloncito.getHeight()-160)) {
                            baloncito.setY(baloncito.getY() + (avanzar));
                            gol();
                        }

                     }while (baloncito.getY()<150);
                }





            }


            public void gol(){

                if ((baloncito.getX()>=291 && baloncito.getX()<=302) && baloncito.getY()<=35){
                   //Toast.makeText(MainActivity.this, "x:"+baloncito.getX()+" y:"+baloncito.getY(), Toast.LENGTH_SHORT).show();
                   Toast.makeText(MainActivity.this, "gol del barcelona", Toast.LENGTH_SHORT).show();

                  baloncito.setX((width/2)-50);
                  baloncito.setY((height/2)-50);

                  jugador1++;
                  setTitle("Barcelona:"+jugador1+"Madrid:"+jugador2);
                }

                if ((baloncito.getX()>=291 && baloncito.getX()<=302 ) && baloncito.getY()>=(height-baloncito.getHeight()-160)){
                    // Toast.makeText(MainActivity.this, "x:"+baloncito.getX()+" y:"+baloncito.getY(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "golazo del Real Marid", Toast.LENGTH_SHORT).show();

                    baloncito.setX((width/2)-50);
                    baloncito.setY((height/2)-50);

                    jugador2++;
                    setTitle("Barcelona:"+jugador1+"Madrid:"+jugador2);

                }

            }





            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        start();
    }

    private  void start(){
        sensorManager.registerListener(sensorEventListener,sensor,sensorManager.SENSOR_DELAY_FASTEST);
    }

    private  void stop(){
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        stop();
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        start();
        super.onResume();
    }
}
