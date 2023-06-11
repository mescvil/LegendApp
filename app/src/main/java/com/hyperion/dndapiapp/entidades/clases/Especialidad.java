package com.hyperion.dndapiapp.entidades.clases;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.utilidades.GetNombreInterface;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@SuppressWarnings("unused")
public class Especialidad extends RealmObject
        implements Parcelable, GetNombreInterface {

    @SerializedName("nombre")
    @PrimaryKey
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("habilidades")
    private RealmList<HabilidadEspecialidad> habilidades;

    /* =============== CONSTRUCTORES =============== */

    public Especialidad() {
    }

    /* =============== METODOS =============== */

    protected Especialidad(Parcel in) {
        nombre = in.readString();
        descripcion = in.readString();

        habilidades = new RealmList<>();
        habilidades.addAll(in.createTypedArrayList(HabilidadEspecialidad.CREATOR));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeTypedList(habilidades);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Especialidad> CREATOR = new Creator<Especialidad>() {
        @Override
        public Especialidad createFromParcel(Parcel in) {
            return new Especialidad(in);
        }

        @Override
        public Especialidad[] newArray(int size) {
            return new Especialidad[size];
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

    public List<HabilidadEspecialidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(RealmList<HabilidadEspecialidad> habilidades) {
        this.habilidades = habilidades;
    }
}
