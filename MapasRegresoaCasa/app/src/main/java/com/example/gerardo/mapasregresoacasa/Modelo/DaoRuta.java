package com.example.gerardo.mapasregresoacasa.Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.gerardo.mapasregresoacasa.Pojos.Ruta;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Gerardo on 17/03/2018.
 */

public class DaoRuta {//

    private Context _contexto;
    private SQLiteDatabase _midb;

    public DaoRuta(Context contexto){
        this._contexto = contexto;
        this._midb = new MiDBOpenHelper(contexto).getWritableDatabase();
    }

    public long Insert(Ruta c){
        ContentValues cv = new ContentValues();
        try {
            cv.put(MiDBOpenHelper.COLUMNS_RUTAS[1], c.getOrigen());
            cv.put(MiDBOpenHelper.COLUMNS_RUTAS[2], c.getDestino());


        }catch (Exception err){}
        return _midb.insert(MiDBOpenHelper.TABLE_RUTAS,null,cv) ;

    }


    public int delete(String id){
        return  _midb.delete("Rutas","_id='"+id+"'",null);
    }



    public List<Ruta> getRutas(String dato) {
        List<Ruta> listarutas = new ArrayList<Ruta>();
        try{
            String selectQuery = "SELECT  * FROM " + "Rutas where Origen like '"+dato+"' or Destino like '"+dato+"'";

            Log.d("", selectQuery);
            SQLiteDatabase db = this._midb;
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    Ruta rt = new Ruta();
                    rt.setID(c.getInt(c.getColumnIndex("_id")));
                    rt.setOrigen(c.getString(c.getColumnIndex("Origen")));
                    rt.setDestino(c.getString(c.getColumnIndex("Destino")));


                    listarutas.add(rt);
                } while (c.moveToNext());
            }
        }catch (Exception err){
            Toast.makeText(_contexto,"no se pudieron cargar las Rutas",Toast.LENGTH_SHORT).show();
        }
        return listarutas;
    }





}//termina