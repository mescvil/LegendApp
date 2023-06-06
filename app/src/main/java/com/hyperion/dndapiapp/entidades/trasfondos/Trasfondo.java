package com.hyperion.dndapiapp.entidades.trasfondos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.entidades.competencias.Competencia;
import com.hyperion.dndapiapp.utilidades.OrdenablePorNombre;

import java.util.List;

public class Trasfondo implements OrdenablePorNombre, Parcelable {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("competencias")
    private List<Competencia> competencias;

    /* =============== CONSTRUCTORES =============== */

    /* =============== METODOS =============== */

    protected Trasfondo(Parcel in) {
        nombre = in.readString();
        descripcion = in.readString();
        competencias = in.createTypedArrayList(Competencia.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeTypedList(competencias);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Trasfondo> CREATOR = new Creator<Trasfondo>() {
        @Override
        public Trasfondo createFromParcel(Parcel in) {
            return new Trasfondo(in);
        }

        @Override
        public Trasfondo[] newArray(int size) {
            return new Trasfondo[size];
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<Competencia> competencias) {
        this.competencias = competencias;
    }
}
