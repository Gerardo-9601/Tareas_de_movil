package com.example.gerardo.myapplication;


import android.content.Context;
import android.net.Uri;
import android.provider.CallLog;
import android.widget.Toast;



/**
 * Created by Gerardo on 14/03/2018.
 */

public class CallLogHelper {

public void eliminar(Context contexto){
    try {
        Uri CALLLOG_URI = Uri.parse("content://call_log/calls");
       // contexto.getApplicationContext().getContentResolver().delete(CALLLOG_URI, CallLog.Calls.NUMBER + "=?", new String[]{"4451455067"});
        //Toast.makeText(contexto.getApplicationContext(),contexto.getApplicationContext().getContentResolver().delete(CALLLOG_URI, CallLog.Calls.NUMBER + "=?", new String[]{"4451455067"}),Toast.LENGTH_LONG).show();
    }catch (Exception err){
        Toast.makeText(contexto.getApplicationContext(),err.getMessage(),Toast.LENGTH_LONG).show();
    }
    }










}
