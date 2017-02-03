package com.example.a2dam.ad_actividad_4a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by 2dam on 12/12/2016.
 */

public class MyDBAdapter {
    private static final String DATABASE_NAME = "db4a.db";
    public static final String DATABASE_ESTUDIANTE = "estudiantes";
    public static final String DATABASE_PROFESOR = "profesor";
    private static final int DATABASE_VERSION = 1;

    public static final String ID="_id";
    public static final String NOMBRE = "nombre";
    private static final String EDAD = "edad";
    public static final String CICLO_Y_CURSO = "ciclo_y_curso";
    private static final String NOTA_MEDIA = "nota_media";
    private static final String TUTOR = "tutor";
    private static final String DESPACHO = "despacho";

    private static final String DATABASE_CREATE_ESTUDIANTE = "CREATE TABLE "+DATABASE_ESTUDIANTE+" (_id integer primary key autoincrement, nombre text, edad integer, ciclo_y_curso text, nota_media integer);";
    private static final String DATABASE_DROP_ESTUDIANTE = "DROP TABLE IF EXISTS "+DATABASE_ESTUDIANTE+";";
    private static final String DATABASE_CREATE_PROFESOR = "CREATE TABLE "+DATABASE_PROFESOR+" (_id integer primary key autoincrement, nombre text, edad integer, ciclo_y_curso text, tutor text, despacho text);";
    private static final String DATABASE_DROP_PROFESOR = "DROP TABLE IF EXISTS "+DATABASE_PROFESOR+";";

    private static String DATABASE_PATH;

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public MyDBAdapter(Context c) {
        DATABASE_PATH=c.getDatabasePath(DATABASE_NAME).getPath();
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open(){
        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }
    }

    public void seleccionar(ArrayList<Elemento>arrayElementos, String valor, String columna, String tabla){
        String selectQuery = "SELECT * FROM "+tabla+" WHERE "+columna+" LIKE '"+valor+"';";
        Cursor cursor= db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do {
                //Se crea un objeto 'Elemento' con los datos de la DB recogidos por el cursor
                arrayElementos.add(new Elemento(cursor.getInt(0),cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void seleccionarPorLetra(ArrayList<Elemento>arrayElementos, String valor, String columna, String tabla){
        String selectQuery = "SELECT * FROM "+tabla+" WHERE "+columna+" LIKE '"+valor+"';";
        Cursor cursor= db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                //Se crea un objeto 'Elemento' con los datos de la DB recogidos por el cursor
                arrayElementos.add(new Elemento(cursor.getInt(0),cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void insertarEstudiante(String n, int e, String cc, float nm){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE,n);
        newValues.put(EDAD,e);
        newValues.put(CICLO_Y_CURSO,cc);
        newValues.put(NOTA_MEDIA,nm);
        db.insert(DATABASE_ESTUDIANTE,null,newValues);
    }

    public void insertarProfesor(String n, int e, String cc, String ccTutor, String d){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE,n);
        newValues.put(EDAD,e);
        newValues.put(CICLO_Y_CURSO,cc);
        newValues.put(TUTOR,ccTutor);
        newValues.put(DESPACHO,d);
        db.insert(DATABASE_PROFESOR,null,newValues);
    }

    public void eliminarEstudiante(String id){
        ContentValues newValues = new ContentValues();
        int n=db.delete(DATABASE_ESTUDIANTE,"_id=?",new String[]{id});
        Toast.makeText(this.context,n+" registros eliminados",Toast.LENGTH_SHORT).show();
    }

    public void eliminarProfesor(String id){
        ContentValues newValues = new ContentValues();
        int n=db.delete(DATABASE_PROFESOR,"_id=?",new String[]{id});
        Toast.makeText(this.context,n+" registros eliminados",Toast.LENGTH_SHORT).show();
    }

    public void eliminarDB(){
        //Se elimina la base de datos
        if(SQLiteDatabase.deleteDatabase(new File(DATABASE_PATH)))
            Toast.makeText(context,"La base de datos ha sido eliminada correctamente",Toast.LENGTH_SHORT).show();
    }

    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_ESTUDIANTE);
            db.execSQL(DATABASE_CREATE_PROFESOR);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP_ESTUDIANTE);
            db.execSQL(DATABASE_DROP_PROFESOR);
            onCreate(db);
        }
    }
}
