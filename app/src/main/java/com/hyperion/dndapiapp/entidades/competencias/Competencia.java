package com.hyperion.dndapiapp.entidades.competencias;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.utilidades.OrdenablePorNombre;

public class Competencia implements OrdenablePorNombre {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
