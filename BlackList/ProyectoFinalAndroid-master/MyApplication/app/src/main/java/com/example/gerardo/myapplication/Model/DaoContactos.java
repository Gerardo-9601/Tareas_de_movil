package com.example.gerardo.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.gerardo.myapplication.Pojos.Contacto;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Gerardo on 29/10/2017.
 */

public class DaoContactos {//

    private Context _contexto;
    private SQLiteDatabase _midb;

    public DaoContactos(Context contexto){
        this._contexto = contexto;
        this._midb = new MiDBOpenHelper(contexto).getWritableDatabase();
    }

    public long Insert(Contacto c){
        ContentValues cv = new ContentValues();
        try {
            cv.put(MiDBOpenHelper.COLUMNS_BLACKLIST[1], c.getContacto());
            cv.put(MiDBOpenHelper.COLUMNS_BLACKLIST[2], c.getTelefono());
            cv.put(MiDBOpenHelper.COLUMNS_BLACKLIST[3], c.getBlockLlamadas());
            cv.put(MiDBOpenHelper.COLUMNS_BLACKLIST[4], c.getBlockmensajes());

        }catch (Exception err){}
        return _midb.insert(MiDBOpenHelper.TABLE_BLACKLIST,null,cv) ;

    }

    public long Update(Contacto c){
        ContentValues cv = new ContentValues();
        try{
            cv.put(MiDBOpenHelper.COLUMNS_BLACKLIST[1], c.getContacto());
            cv.put(MiDBOpenHelper.COLUMNS_BLACKLIST[2], c.getTelefono());
            cv.put(MiDBOpenHelper.COLUMNS_BLACKLIST[3], c.getBlockLlamadas());
            cv.put(MiDBOpenHelper.COLUMNS_BLACKLIST[4], c.getBlockmensajes());

        }catch (Exception err){}

        return _midb.update(MiDBOpenHelper.TABLE_BLACKLIST,
                cv,
                "_id=?",
                new String[] { String.valueOf( c.getID())});

    }

      public int delete(String id){
        return  _midb.delete("Listanegra","_id='"+id+"'",null);
      }



    public List<Contacto> getContactos() {
        List<Contacto> notasArrayList = new ArrayList<Contacto>();
        try{
            String selectQuery = "SELECT  * FROM " + "Listanegra";
            Log.d("", selectQuery);
            SQLiteDatabase db = this._midb;
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    Contacto contact = new Contacto();
                    contact.setID(c.getInt(c.getColumnIndex("_id")));
                    contact.setContacto(c.getString(c.getColumnIndex("Contacto")));
                    contact.setTelefono(c.getString(c.getColumnIndex("Telefono")));
                    contact.setBlockLlamadas(c.getString(c.getColumnIndex("Bloquear_Llamadas")));
                    contact.setBlockmensajes(c.getString(c.getColumnIndex("Bloquear_Mensajes")));

                    notasArrayList.add(contact);
                } while (c.moveToNext());
            }
        }catch (Exception err){
            Toast.makeText(_contexto,err.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return notasArrayList;
    }

    public boolean contactobloqueado(String telefono) {
       boolean contacto=false;
        try{
            String selectQuery = "SELECT  * FROM " + "Listanegra where Telefono='"+telefono+"'";
            Log.d("", selectQuery);
            SQLiteDatabase db = this._midb;
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    Contacto contact = new Contacto();
                    contact.setID(c.getInt(c.getColumnIndex("_id")));
                    contact.setContacto(c.getString(c.getColumnIndex("Contacto")));
                    contact.setTelefono(c.getString(c.getColumnIndex("Telefono")));
                    contact.setBlockLlamadas(c.getString(c.getColumnIndex("Bloquear_Llamadas")));
                    contact.setBlockmensajes(c.getString(c.getColumnIndex("Bloquear_Mensajes")));
                   contacto=true;
                } while (c.moveToNext());
            }
        }catch (Exception err){
            Toast.makeText(_contexto,err.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return contacto;
    }





}//termina
