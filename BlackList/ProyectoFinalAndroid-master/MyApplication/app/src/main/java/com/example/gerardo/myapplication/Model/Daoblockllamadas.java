package com.example.gerardo.myapplication.Model;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.gerardo.myapplication.Pojos.Blockllamadas;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Gerardo on 29/10/2017.
 */

public class Daoblockllamadas {//

    private Context _contexto;
    private SQLiteDatabase _midb;

    public Daoblockllamadas(Context contexto){
        this._contexto = contexto;
        this._midb = new MiDBOpenHelper(contexto).getWritableDatabase();
    }

    public long Insert(Blockllamadas c){
        ContentValues cv = new ContentValues();
        try {
            cv.put(MiDBOpenHelper.COLUMNS_LLAMADAS_BLOQUEADAS[1], c.getContacto());
            cv.put(MiDBOpenHelper.COLUMNS_LLAMADAS_BLOQUEADAS[2], c.getTelefono());
            cv.put(MiDBOpenHelper.COLUMNS_LLAMADAS_BLOQUEADAS[3], c.getHora());
            cv.put(MiDBOpenHelper.COLUMNS_LLAMADAS_BLOQUEADAS[4], c.getFecha());

        }catch (Exception err){}
        return _midb.insert(MiDBOpenHelper.TABLE_BLOCKLLAMADAS,null,cv) ;

    }


    public int delete(String id){
        return  _midb.delete("blockllamadas","_id='"+id+"'",null);
    }



    public List<Blockllamadas> getllamadas() {
        List<Blockllamadas> notasArrayList = new ArrayList<Blockllamadas>();
        try{
            String selectQuery = "SELECT  * FROM " + "blockllamadas";
            Log.d("", selectQuery);
            SQLiteDatabase db = this._midb;
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    Blockllamadas llamada = new Blockllamadas();
                    llamada.setID(c.getInt(c.getColumnIndex("_id")));
                    llamada.setContacto(c.getString(c.getColumnIndex("Contacto")));
                    llamada.setTelefono(c.getString(c.getColumnIndex("Telefono")));
                    llamada.setHora(c.getString(c.getColumnIndex("Hora")));
                    llamada.setFecha(c.getString(c.getColumnIndex("Fecha")));

                    notasArrayList.add(llamada);
                } while (c.moveToNext());
            }
        }catch (Exception err){
            Toast.makeText(_contexto,"no se pudieron cargar las Llamadas Bloqueadas",Toast.LENGTH_SHORT).show();
        }
        return notasArrayList;
    }





}//termina
