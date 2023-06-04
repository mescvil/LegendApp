package com.hyperion.dndapiapp.entidades.equipamiento;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Armadura implements Equipamiento {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("tipo")
    private String tipo;
    @SerializedName("peso")
    private int peso;
    @SerializedName("claseArmadura")
    private String claseArmadura;
    @SerializedName("precio")
    private String precio;
    @SerializedName("fuerzaRequerida")
    private int fuerzaRequerida;
    @SerializedName("desventajaSigilo")
    private boolean desventajaSigilo;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getClaseArmadura() {
        return claseArmadura;
    }

    public void setClaseArmadura(String claseArmadura) {
        this.claseArmadura = claseArmadura;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getFuerzaRequerida() {
        return fuerzaRequerida;
    }

    public void setFuerzaRequerida(int fuerzaRequerida) {
        this.fuerzaRequerida = fuerzaRequerida;
    }

    public boolean isDesventajaSigilo() {
        return desventajaSigilo;
    }

    public void setDesventajaSigilo(boolean desventajaSigilo) {
        this.desventajaSigilo = desventajaSigilo;
    }
}
