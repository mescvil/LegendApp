package com.hyperion.dndapiapp.entidades.glosario.equipamiento;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.adaptadores.recyclerView.GenericoRecyclerView;
import com.hyperion.dndapiapp.utilidades.GetNombreInterface;

import java.util.Objects;

@SuppressWarnings("unused")
public class Hechizo implements GetNombreInterface, Parcelable, GenericoRecyclerView {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("nivel")
    private int nivel;
    @SerializedName("tiempoLanzamiento")
    private String tiempoLanzamiento;
    @SerializedName("alcance")
    private int alcance;
    @SerializedName("duracion")
    private String duracion;
    @SerializedName("tiradaSalvacion")
    private String tiradaSalvacion;

    /* =============== CONSTRUCTORES =============== */

    public Hechizo() {
    }

    public Hechizo(String nombre) {
        this.nombre = nombre;
    }

    /* =============== METODOS =============== */

    protected Hechizo(Parcel in) {
        nombre = in.readString();
        descripcion = in.readString();
        nivel = in.readInt();
        tiempoLanzamiento = in.readString();
        alcance = in.readInt();
        duracion = in.readString();
        tiradaSalvacion = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeInt(nivel);
        dest.writeString(tiempoLanzamiento);
        dest.writeInt(alcance);
        dest.writeString(duracion);
        dest.writeString(tiradaSalvacion);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Hechizo> CREATOR = new Creator<Hechizo>() {
        @Override
        public Hechizo createFromParcel(Parcel in) {
            return new Hechizo(in);
        }

        @Override
        public Hechizo[] newArray(int size) {
            return new Hechizo[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hechizo hechizo = (Hechizo) o;

        return Objects.equals(nombre, hechizo.nombre);
    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }

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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getTiempoLanzamiento() {
        return tiempoLanzamiento;
    }

    public void setTiempoLanzamiento(String tiempoLanzamiento) {
        this.tiempoLanzamiento = tiempoLanzamiento;
    }

    public int getAlcance() {
        return alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getTiradaSalvacion() {
        return tiradaSalvacion;
    }

    public void setTiradaSalvacion(String tiradaSalvacion) {
        this.tiradaSalvacion = tiradaSalvacion;
    }
}
