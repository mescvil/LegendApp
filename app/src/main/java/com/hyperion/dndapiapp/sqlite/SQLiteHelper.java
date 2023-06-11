package com.hyperion.dndapiapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public final class SQLiteHelper extends SQLiteOpenHelper {

    private static SQLiteHelper sqLiteHelper;
    private static SQLiteDatabase db;
    private final static String CREATE_DB = "create table Favoritos" +
            " (nombre text, tipo text, unique(nombre))";

    private static final String DROP_TABLE = "drop table if exists Favoritos";

    private SQLiteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    public static SQLiteHelper getInstance(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        if (sqLiteHelper == null) {
            sqLiteHelper = new SQLiteHelper(contexto, nombre, factory, version);
        }
        return sqLiteHelper;
    }

    public void iniciaConexion() {
        Log.d("SQLite", "Conexion iniciada con la db");
        db = getWritableDatabase();
    }

    public void insert(Favorito favorito) {

        ContentValues valores = new ContentValues();
        valores.put("nombre", favorito.getNombre());
        valores.put("tipo", favorito.getTipo());
        db.insertWithOnConflict("Favoritos", null, valores, SQLiteDatabase.CONFLICT_IGNORE);

        Log.d("SQLite", "Favorito insertado");
    }

    public List<Favorito> selectAll() {
        List<Favorito> lista = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Favoritos", null);

        while (cursor.moveToNext()) {
            String nombre = cursor.getString(0);
            String tipo = cursor.getString(1);
            lista.add(new Favorito(nombre, tipo));
        }

        cursor.close();
        Log.d("SQLite", "Favoritos cargados");
        return lista;
    }

    public Favorito findByPk(String nombreABuscar) {
        Favorito favorito = null;
        Cursor cursor = db.rawQuery("select * from Favoritos where nombre=" + nombreABuscar, null);

        while (cursor.moveToNext()) {
            String nombre = cursor.getString(0);
            String tipo = cursor.getString(1);
            favorito = new Favorito(nombre, tipo);
        }
        cursor.close();
        return favorito;
    }

    public void delete(String nombre) {
        db.delete("Favoritos", "nombre=?", new String[]{nombre});
    }

    public void deleteAll() {
        db.execSQL("delete from Favoritos");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        db.execSQL(CREATE_DB);
    }

    public void stop() {
        Log.d("SQLite", "Conexion terminada con la db");
        db.close();
    }
}