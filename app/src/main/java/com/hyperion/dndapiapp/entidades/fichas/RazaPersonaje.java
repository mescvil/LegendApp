package com.hyperion.dndapiapp.entidades.fichas;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class RazaPersonaje implements Parcelable {

    @SerializedName("idRaza")
    private long idRaza;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("rasgos")
    private String rasgos;

    /* =============== CONSTRUCTORES =============== */

    /* =============== METODOS =============== */

    protected RazaPersonaje(Parcel in) {
        idRaza = in.readLong();
        nombre = in.readString();
        rasgos = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idRaza);
        dest.writeString(nombre);
        dest.writeString(rasgos);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RazaPersonaje> CREATOR = new Creator<RazaPersonaje>() {
        @Override
        public RazaPersonaje createFromParcel(Parcel in) {
            return new RazaPersonaje(in);
        }

        @Override
        public RazaPersonaje[] newArray(int size) {
            return new RazaPersonaje[size];
        }
    };

    /* =============== GETTERS & SETTERS =============== */

    public long getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(long idRaza) {
        this.idRaza = idRaza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRasgos() {
        return rasgos;
    }

    public void setRasgos(String rasgos) {
        this.rasgos = rasgos;
    }
}
