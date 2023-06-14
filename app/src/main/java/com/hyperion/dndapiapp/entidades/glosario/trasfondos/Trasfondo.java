package com.hyperion.dndapiapp.entidades.glosario.trasfondos;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.entidades.glosario.competencias.Competencia;
import com.hyperion.dndapiapp.utilidades.GetNombreInterface;

import java.util.List;

@SuppressWarnings("unused")
public class Trasfondo implements GetNombreInterface, Parcelable {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("idiomas")
    private List<Idioma> idiomas;
    @SerializedName("competencias")
    private List<Competencia> competencias;

    /* =============== CONSTRUCTORES =============== */

    /* =============== METODOS =============== */

    protected Trasfondo(Parcel in) {
        nombre = in.readString();
        descripcion = in.readString();
        competencias = in.createTypedArrayList(Competencia.CREATOR);
        idiomas = in.createTypedArrayList(Idioma.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeTypedList(competencias);
        dest.writeTypedList(idiomas);
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

    @NonNull
    @Override
    public String toString() {
        return getNombre();
    }

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

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }
}
