package com.hyperion.dndapiapp.entidades.clases;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.adaptadores.recyclerView.GenericoRecyclerView;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@SuppressWarnings("unused")
public class HabilidadEspecialidad extends RealmObject
        implements Parcelable, GenericoRecyclerView {

    @SerializedName("nombre")
    @PrimaryKey
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;

    /* =============== CONSTRUCTORES =============== */

    public HabilidadEspecialidad() {
    }

    /* =============== METODOS =============== */

    protected HabilidadEspecialidad(Parcel in) {
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

    public static final Creator<HabilidadEspecialidad> CREATOR = new Creator<HabilidadEspecialidad>() {
        @Override
        public HabilidadEspecialidad createFromParcel(Parcel in) {
            return new HabilidadEspecialidad(in);
        }

        @Override
        public HabilidadEspecialidad[] newArray(int size) {
            return new HabilidadEspecialidad[size];
        }
    };

    /* =============== GETTERS & SETTERS =============== */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
