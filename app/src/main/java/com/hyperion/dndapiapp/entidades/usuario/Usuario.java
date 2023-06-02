package com.hyperion.dndapiapp.entidades.usuario;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Usuario {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("correo")
    private String correo;
    @SerializedName("contrasenia")
    private String contrasenia;

    /* =============== CONSTRUCTORES =============== */

    public Usuario(String correo, String contrasenia) {
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public Usuario(String nombre, String correo, String contrasenia) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    /* =============== METODOS =============== */

    /* =============== GETTERS & SETTERS =============== */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
