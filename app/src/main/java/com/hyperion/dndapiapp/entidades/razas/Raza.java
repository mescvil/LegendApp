package com.hyperion.dndapiapp.entidades.razas;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.utilidades.OrdenablePorNombre;

@SuppressWarnings("unused")
public class Raza implements OrdenablePorNombre {

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
}
