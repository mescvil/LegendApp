package com.hyperion.dndapiapp.entidades.glosario.trasfondos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.utilidades.GetNombreInterface;

@SuppressWarnings("unused")
public class Idioma implements Parcelable, GetNombreInterface {

    @SerializedName("nombre")
    private String nombre;

    public Idioma() {

    }

    protected Idioma(Parcel in) {
        nombre = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Idioma> CREATOR = new Creator<Idioma>() {
        @Override
        public Idioma createFromParcel(Parcel in) {
            return new Idioma(in);
        }

        @Override
        public Idioma[] newArray(int size) {
            return new Idioma[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
