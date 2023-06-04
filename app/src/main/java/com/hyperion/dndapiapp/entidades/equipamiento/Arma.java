package com.hyperion.dndapiapp.entidades.equipamiento;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Arma implements Equipamiento {

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

    /* =============== GETTERS & SETTERS =============== */

    @Override
    public String getNombre() {
        return nombre;
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
