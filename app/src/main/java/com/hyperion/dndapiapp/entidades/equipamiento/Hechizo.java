package com.hyperion.dndapiapp.entidades.equipamiento;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.utilidades.OrdenablePorNombre;

@SuppressWarnings("unused")
public class Hechizo implements OrdenablePorNombre {

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

    /* =============== METODOS =============== */

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
