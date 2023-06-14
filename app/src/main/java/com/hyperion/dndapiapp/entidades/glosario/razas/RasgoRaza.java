package com.hyperion.dndapiapp.entidades.glosario.razas;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.adaptadores.recyclerView.GenericoRecyclerView;
import com.hyperion.dndapiapp.utilidades.GetNombreInterface;

@SuppressWarnings("unused")
public class RasgoRaza implements Parcelable, GenericoRecyclerView, GetNombreInterface {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;

    /* =============== CONSTRUCTORES =============== */

    /* =============== METODOS =============== */
    protected RasgoRaza(Parcel in) {
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

    public static final Creator<RasgoRaza> CREATOR = new Creator<RasgoRaza>() {
        @Override
        public RasgoRaza createFromParcel(Parcel in) {
            return new RasgoRaza(in);
        }

        @Override
        public RasgoRaza[] newArray(int size) {
            return new RasgoRaza[size];
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
