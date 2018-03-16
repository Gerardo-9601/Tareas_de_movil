package com.example.gerardo.myapplication;

import android.app.NotificationManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.CallLog;
import android.support.v7.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.example.gerardo.myapplication.Model.DaoContactos;
import com.example.gerardo.myapplication.Model.Daoblockllamadas;
import com.example.gerardo.myapplication.Pojos.Blockllamadas;

import java.lang.reflect.Method;
import java.util.Calendar;


public class PhoneCallReceiver extends BroadcastReceiver {


    int a = 0;

    @Override
    public void onReceive(Context context, final Intent intent) {


        try {
            if (intent.getAction().equals("android.intent.action.PHONE_STATE")) {

                DaoContactos dao = new DaoContactos(context);

                String numberCall = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

                // Toast.makeText(context,numberCall,Toast.LENGTH_LONG).show();
                if (dao.contactobloqueado(numberCall) == true) {
                    disconnectPhoneItelephony(context);
                    Daoblockllamadas llamadasbloqueadas = new Daoblockllamadas(context);

                    if (a == 0) {
                        a++;
                        Blockllamadas llamada = new Blockllamadas("x", numberCall, horasistema(), fecha());
                        llamadasbloqueadas.Insert(llamada);
                    }


                    CallLogHelper call = new CallLogHelper();
                    call.eliminar(context);




                    //notificacion
                    notificacion(context,"Bloqueo","se ha bloqueado una llamada");

                }


    }

           }catch (Exception err){
                  Toast.makeText(context,err.getMessage(),Toast.LENGTH_LONG).show();
            }finally {
               a=0;
            }

    }






    // Keep this method as it is
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void disconnectPhoneItelephony(Context context) {
        try {

            String serviceManagerName = "android.os.ServiceManager";
            String serviceManagerNativeName = "android.os.ServiceManagerNative";
            String telephonyName = "com.android.internal.telephony.ITelephony";
            Class<?> telephonyClass;
            Class<?> telephonyStubClass;
            Class<?> serviceManagerClass;
            Class<?> serviceManagerNativeClass;
            Method telephonyEndCall;
            Object telephonyObject;
            Object serviceManagerObject;
            telephonyClass = Class.forName(telephonyName);
            telephonyStubClass = telephonyClass.getClasses()[0];
            serviceManagerClass = Class.forName(serviceManagerName);
            serviceManagerNativeClass = Class.forName(serviceManagerNativeName);
            Method getService = // getDefaults[29];
                    serviceManagerClass.getMethod("getService", String.class);
            Method tempInterfaceMethod = serviceManagerNativeClass.getMethod("asInterface", IBinder.class);
            Binder tmpBinder = new Binder();
            tmpBinder.attachInterface(null, "fake");
            serviceManagerObject = tempInterfaceMethod.invoke(null, tmpBinder);
            IBinder retbinder = (IBinder) getService.invoke(serviceManagerObject, "phone");
            Method serviceMethod = telephonyStubClass.getMethod("asInterface", IBinder.class);
            telephonyObject = serviceMethod.invoke(null, retbinder);
            telephonyEndCall = telephonyClass.getMethod("endCall");
            telephonyEndCall.invoke(telephonyObject);



        } catch (Exception e) {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


    public void notificacion(Context context,String titulo,String descripcion){
        NotificationManager nManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle(titulo)
                .setContentText(descripcion)
                .setWhen(System.currentTimeMillis());


                    /*Intent targetIntent = new Intent(context, null);
                    PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                            targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(contentIntent);
                    */

        builder.setAutoCancel(true);

        nManager.notify(123456, builder.build());
    }
    public String fecha(){
        final Calendar c= Calendar.getInstance();
        int dia=c.get(Calendar.DAY_OF_MONTH);
        int mes=c.get(Calendar.MONTH)+1;
        int year=c.get(Calendar.YEAR);
        return year+"/"+mes+"/"+dia;
    }
    public String horasistema(){
        final Calendar c= Calendar.getInstance();
        int h=c.get(Calendar.HOUR_OF_DAY);
        int minutos=c.get(Calendar.MINUTE);
        return h+":"+minutos;
    }




}