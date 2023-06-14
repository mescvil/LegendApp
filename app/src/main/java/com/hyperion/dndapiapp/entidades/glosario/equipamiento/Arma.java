package com.hyperion.dndapiapp.entidades.glosario.equipamiento;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.adaptadores.recyclerView.GenericoRecyclerView;

@SuppressWarnings("unused")
public class Arma implements Equipamiento, Parcelable, GenericoRecyclerView {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("danio")
    private String danio;
    @SerializedName("dosManos")
    private boolean dosManos;
    @SerializedName("arrojadiza")
    private boolean arrojadiza;
    @SerializedName("propiedad")
    private String propiedad;
    @SerializedName("precio")
    private String precio;

    /* =============== CONSTRUCTORES =============== */

    /* =============== METODOS =============== */

    protected Arma(Parcel in) {
        nombre = in.readString();
        danio = in.readString();
        dosManos = in.readByte() != 0;
        arrojadiza = in.readByte() != 0;
        propiedad = in.readString();
        precio = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(danio);
        dest.writeByte((byte) (dosManos ? 1 : 0));
        dest.writeByte((byte) (arrojadiza ? 1 : 0));
        dest.writeString(propiedad);
        dest.writeString(precio);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Arma> CREATOR = new Creator<Arma>() {
        @Override
        public Arma createFromParcel(Parcel in) {
            return new Arma(in);
        }

        @Override
        public Arma[] newArray(int size) {
            return new Arma[size];
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

    @Override
    public String getDescripcion() {
        return "Daño: " + danio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDanio() {
        return danio;
    }

    public void setDanio(String danio) {
        this.danio = danio;
    }

    public boolean isDosManos() {
        return dosManos;
    }

    public void setDosManos(boolean dosManos) {
        this.dosManos = dosManos;
    }

    public boolean isArrojadiza() {
        return arrojadiza;
    }

    public void setArrojadiza(boolean arrojadiza) {
        this.arrojadiza = arrojadiza;
    }

    public String getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(String propiedad) {
        this.propiedad = propiedad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
