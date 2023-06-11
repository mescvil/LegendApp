package com.hyperion.dndapiapp.sqlite;

import android.os.Parcel;
import android.os.Parcelable;

public class Favorito implements Parcelable {

    private final String nombre;
    private final String tipo;

    public Favorito(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Favorito(String nombre) {
        this.nombre = nombre;
        this.tipo = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favorito favorito = (Favorito) o;

        return getNombre().equalsIgnoreCase(favorito.getNombre());
    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }

    protected Favorito(Parcel in) {
        nombre = in.readString();
        tipo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(tipo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Favorito> CREATOR = new Creator<Favorito>() {
        @Override
        public Favorito createFromParcel(Parcel in) {
            return new Favorito(in);
        }

        @Override
        public Favorito[] newArray(int size) {
            return new Favorito[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

}
