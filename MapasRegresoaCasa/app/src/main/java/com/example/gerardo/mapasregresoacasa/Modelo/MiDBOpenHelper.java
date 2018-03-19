package com.example.gerardo.mapasregresoacasa.Modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MiDBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Direcciones";
    private static final int DB_VERSION = 1;
    public static final String[] COLUMNS_RUTAS= {"_id", "Origen", "Destino"};
    public static final String TABLE_RUTAS= "Rutas";


    private  final String TABLA_LISTA_RUTAS = "create table Rutas ("+
            COLUMNS_RUTAS[0]+" integer primary key autoincrement, "+
            COLUMNS_RUTAS[1]+" varchar(80) not null," +
            COLUMNS_RUTAS[2]+" varchar(80) not null);";

    public MiDBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(TABLA_LISTA_RUTAS);
        }catch (Exception err){}
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Rutas");
        onCreate(sqLiteDatabase);
        }catch (Exception err){

        }
    }


}
