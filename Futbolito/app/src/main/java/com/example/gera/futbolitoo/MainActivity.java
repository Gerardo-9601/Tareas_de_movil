package com.example.dios.futbolitoo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dios.futbolito.R;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    int a=0;


    float xinicial=0;
    float yinicial=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView baloncito =  (ImageView)findViewById(R.id.baloncito);
      //  final ImageView porteria =  (ImageView)findViewById(R.id.porteria);

        xinicial=baloncito.getX();
        yinicial=baloncito.getY();

        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor==null){
            finish();
        }

        sensorEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                Display display = getWindowManager().getDefaultDisplay();
                int width = display.getWidth();  // obsoleto (deprecated)
                int height = display.getHeight();  // obsoleto (deprecated)


                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];


                int avanzar=5;
                gol(width);

                if (x<-1){
                    do {
                        if(baloncito.getX()<=(width-baloncito.getWidth()-avanzar)) {
                            baloncito.setX(baloncito.getX() + avanzar);
                            gol(width);
                        }
                    }while (baloncito.getX()<100);


                }else if(x>1){
                    do {
                        if( baloncito.getX()>4) {
                            baloncito.setX(baloncito.getX() - avanzar);
                            gol(width);
                        }
                    }while (baloncito.getX()>150);

                }

                // eje y

                if(y<-1){
                    do{
                        if(baloncito.getY()>avanzar) {
                            baloncito.setY(baloncito.getY() - avanzar);
                            gol(width);
                        }
                }while (baloncito.getY()>100);

                }else if (y>1){
                    do {
                        if(baloncito.getY()<=(height-baloncito.getHeight()-160)) {
                            baloncito.setY(baloncito.getY() + (avanzar));
                            gol(width);
                        }

                     }while (baloncito.getY()<150);
                }


                gol(width);


            }


            public void gol(int width){

                if ((baloncito.getX()>=295&&baloncito.getX()<=299) && baloncito.getY()<35){
                    Toast.makeText(MainActivity.this, "x:"+baloncito.getX()+" y:"+baloncito.getY(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "gol", Toast.LENGTH_SHORT).show();
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
