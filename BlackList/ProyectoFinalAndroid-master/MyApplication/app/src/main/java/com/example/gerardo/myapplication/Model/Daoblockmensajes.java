package com.example.gerardo.myapplication.Model;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import com.example.gerardo.myapplication.Pojos.Blockmensajes;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by Gerardo on 29/10/2017.
 */

public class Daoblockmensajes {//

    private Context _contexto;
    private SQLiteDatabase _midb;

    public Daoblockmensajes(Context contexto){
        this._contexto = contexto;
        this._midb = new MiDBOpenHelper(contexto).getWritableDatabase();
    }

    public long Insert(Blockmensajes c){
        ContentValues cv = new ContentValues();
        try {
            cv.put(MiDBOpenHelper.COLUMNS_MENSAJES_BLOQUEADAS[1], c.getContacto());
            cv.put(MiDBOpenHelper.COLUMNS_MENSAJES_BLOQUEADAS[2], c.getTelefono());
            cv.put(MiDBOpenHelper.COLUMNS_MENSAJES_BLOQUEADAS[3], c.getMensaje());
            cv.put(MiDBOpenHelper.COLUMNS_MENSAJES_BLOQUEADAS[4], c.getHora());
            cv.put(MiDBOpenHelper.COLUMNS_MENSAJES_BLOQUEADAS[5], c.getFecha());

        }catch (Exception err){}
        return _midb.insert(MiDBOpenHelper.TABLE_BLOCKMENSAJES,null,cv) ;

    }


    public int delete(String id){
        return  _midb.delete("blockmensajes","_id='"+id+"'",null);
    }



    public List<Blockmensajes> getContactos() {
        List<Blockmensajes> notasArrayList = new ArrayList<Blockmensajes>();
        try{
            String selectQuery = "SELECT  * FROM " + "blockmensajes";
            Log.d("", selectQuery);
            SQLiteDatabase db = this._midb;
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    Blockmensajes mensaje = new Blockmensajes();
                    mensaje.setID(c.getInt(c.getColumnIndex("_id")));
                    mensaje.setContacto(c.getString(c.getColumnIndex("Contacto")));
                    mensaje.setTelefono(c.getString(c.getColumnIndex("Telefono")));
                    mensaje.setTelefono(c.getString(c.getColumnIndex("Mensaje")));
                    mensaje.setHora(c.getString(c.getColumnIndex("Hora")));
                    mensaje.setFecha(c.getString(c.getColumnIndex("Fecha")));

                    notasArrayList.add(mensaje);
                } while (c.moveToNext());
            }
        }catch (Exception err){
            Toast.makeText(_contexto,"no se pudieron cargar los Mensajes Bloqueados",Toast.LENGTH_SHORT).show();
        }
        return notasArrayList;
    }





}//termina
