package com.hyperion.dndapiapp.sqlite;

import android.os.Parcel;
import android.os.Parcelable;

public class FavoritoClase implements Parcelable {

    private final String nombre;
    private final String tipo;
    private final boolean isFavorito;

    public FavoritoClase(String nombre, String tipo, boolean isFavorito) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.isFavorito = isFavorito;
    }

    protected FavoritoClase(Parcel in) {
        nombre = in.readString();
        tipo = in.readString();
        isFavorito = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(tipo);
        dest.writeByte((byte) (isFavorito ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FavoritoClase> CREATOR = new Creator<FavoritoClase>() {
        @Override
        public FavoritoClase createFromParcel(Parcel in) {
            return new FavoritoClase(in);
        }

        @Override
        public FavoritoClase[] newArray(int size) {
            return new FavoritoClase[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public boolean isFavorito() {
        return isFavorito;
    }

    public Favorito getFavorito() {
        return new Favorito(nombre, tipo);
    }
}
