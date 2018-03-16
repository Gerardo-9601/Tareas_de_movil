package com.example.gerardo.myapplication.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MiDBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "blacklist";
    private static final int DB_VERSION = 1;
    public static final String[] COLUMNS_BLACKLIST= {"_id", "Contacto", "Telefono","Bloquear_Llamadas","Bloquear_Mensajes"};
    public static final String TABLE_BLACKLIST= "Listanegra";

    public static final String[] COLUMNS_LLAMADAS_BLOQUEADAS = {"_id", "Contacto", "Telefono","Hora","Fecha"};
    public static final String TABLE_BLOCKLLAMADAS = "blockllamadas";


    public static final String[] COLUMNS_MENSAJES_BLOQUEADAS = {"_id", "Contacto", "Telefono","Mensaje","Hora","Fecha"};
    public static final String TABLE_BLOCKMENSAJES = "blockmensajes";

    private  final String TABLA_LISTA_NEGRA = "create table Listanegra ("+
            COLUMNS_BLACKLIST[0]+" integer primary key autoincrement, "+
            COLUMNS_BLACKLIST[1]+" varchar(50) not null," +
            COLUMNS_BLACKLIST[2]+" varchar(15) not null,"+
            COLUMNS_BLACKLIST[3]+" char(1) null,"+
            COLUMNS_BLACKLIST[4]+" char(1) null);";

    private  final String TABLA_LLAMADAS_BLOQUEADAS = "create table blockllamadas ("+
            COLUMNS_LLAMADAS_BLOQUEADAS[0]+" integer primary key autoincrement, "+
            COLUMNS_LLAMADAS_BLOQUEADAS[1]+" varchar(50) not null," +
            COLUMNS_LLAMADAS_BLOQUEADAS[2]+" varchar(15) not null,"+
            COLUMNS_LLAMADAS_BLOQUEADAS[3]+" varchar(30) null,"+
            COLUMNS_LLAMADAS_BLOQUEADAS[4]+" varchar(30) null);";


    private  final String TABLA_MENSAJES_BLOQUEADAS = "create table blockmensajes ("+
            COLUMNS_MENSAJES_BLOQUEADAS[0]+" integer primary key autoincrement, "+
            COLUMNS_MENSAJES_BLOQUEADAS[1]+" varchar(50) not null," +
            COLUMNS_MENSAJES_BLOQUEADAS[2]+" varchar(15) not null,"+
            COLUMNS_MENSAJES_BLOQUEADAS[3]+" varchar(140) null,"+
            COLUMNS_MENSAJES_BLOQUEADAS[4]+" varchar(140) null,"+
            COLUMNS_MENSAJES_BLOQUEADAS[5]+" varchar(30) null);";


    public MiDBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(TABLA_LISTA_NEGRA);
            sqLiteDatabase.execSQL(TABLA_LLAMADAS_BLOQUEADAS);
            sqLiteDatabase.execSQL(TABLA_MENSAJES_BLOQUEADAS);
        }catch (Exception err){}
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Contactos");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS blockllamadas");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS blockmensajes");
        onCreate(sqLiteDatabase);
        }catch (Exception err){

        }
    }


}
