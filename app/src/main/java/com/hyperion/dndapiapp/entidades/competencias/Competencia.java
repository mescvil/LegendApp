package com.hyperion.dndapiapp.entidades.competencias;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.adaptadores.recyclerView.GenericoRecyclerView;
import com.hyperion.dndapiapp.utilidades.GetNombreInterface;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Competencia extends RealmObject
        implements GetNombreInterface, GenericoRecyclerView, Parcelable {

    @SerializedName("nombre")
    @PrimaryKey
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;

    /* =============== CONSTRUCTORES =============== */

    public Competencia() {
    }

    /* =============== METODOS =============== */

    protected Competencia(Parcel in) {
        nombre = in.readString();
        descripcion = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(descripcion);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Competencia> CREATOR = new Creator<Competencia>() {
        @Override
        public Competencia createFromParcel(Parcel in) {
            return new Competencia(in);
        }

        @Override
        public Competencia[] newArray(int size) {
            return new Competencia[size];
        }
    };

    /* =============== GETTERS & SETTERS =============== */

    @Override
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
