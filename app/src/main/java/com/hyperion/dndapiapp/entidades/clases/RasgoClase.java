package com.hyperion.dndapiapp.entidades.clases;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.adaptadores.recyclerView.GenericoRecyclerView;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@SuppressWarnings("unused")
public class RasgoClase extends RealmObject
        implements Parcelable, GenericoRecyclerView {

    @SerializedName("nombre")
    @PrimaryKey
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;

    /* =============== CONSTRUCTORES =============== */

    public RasgoClase() {
    }

    /* =============== METODOS =============== */

    protected RasgoClase(Parcel in) {
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

    public static final Creator<RasgoClase> CREATOR = new Creator<RasgoClase>() {
        @Override
        public RasgoClase createFromParcel(Parcel in) {
            return new RasgoClase(in);
        }

        @Override
        public RasgoClase[] newArray(int size) {
            return new RasgoClase[size];
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
