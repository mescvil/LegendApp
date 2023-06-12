package com.hyperion.dndapiapp.entidades.glosario.razas;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.utilidades.GetNombreInterface;

import java.util.List;

@SuppressWarnings("unused")
public class Raza implements GetNombreInterface, Parcelable {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("edadMaxima")
    private int edadMaxima;
    @SerializedName("alturaMinima")
    private float alturaMinima;
    @SerializedName("alturaMaxima")
    private float alturaMaxima;
    @SerializedName("velocidad")
    private int velocidad;
    @SerializedName("imagenVaron")
    private String imagenVaron;
    @SerializedName("imagenHembra")
    private String imagenHembra;
    @SerializedName("rasgosRaza")
    private List<RasgoRaza> rasgosRaza;

    /* =============== CONSTRUCTORES =============== */

    /* =============== METODOS =============== */

    protected Raza(Parcel in) {
        nombre = in.readString();
        edadMaxima = in.readInt();
        alturaMinima = in.readFloat();
        alturaMaxima = in.readFloat();
        velocidad = in.readInt();
        imagenVaron = in.readString();
        imagenHembra = in.readString();
        rasgosRaza = in.createTypedArrayList(RasgoRaza.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeInt(edadMaxima);
        dest.writeFloat(alturaMinima);
        dest.writeFloat(alturaMaxima);
        dest.writeInt(velocidad);
        dest.writeString(imagenVaron);
        dest.writeString(imagenHembra);
        dest.writeTypedList(rasgosRaza);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Raza> CREATOR = new Creator<Raza>() {
        @Override
        public Raza createFromParcel(Parcel in) {
            return new Raza(in);
        }

        @Override
        public Raza[] newArray(int size) {
            return new Raza[size];
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

    public int getEdadMaxima() {
        return edadMaxima;
    }

    public void setEdadMaxima(int edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    public float getAlturaMinima() {
        return alturaMinima;
    }

    public void setAlturaMinima(float alturaMinima) {
        this.alturaMinima = alturaMinima;
    }

    public float getAlturaMaxima() {
        return alturaMaxima;
    }

    public void setAlturaMaxima(float alturaMaxima) {
        this.alturaMaxima = alturaMaxima;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public String getImagenVaron() {
        return imagenVaron;
    }

    public void setImagenVaron(String imagenVaron) {
        this.imagenVaron = imagenVaron;
    }

    public String getImagenHembra() {
        return imagenHembra;
    }

    public void setImagenHembra(String imagenHembra) {
        this.imagenHembra = imagenHembra;
    }

    public List<RasgoRaza> getRasgosRaza() {
        return rasgosRaza;
    }

    public void setRasgosRaza(List<RasgoRaza> rasgosRaza) {
        this.rasgosRaza = rasgosRaza;
    }
}
