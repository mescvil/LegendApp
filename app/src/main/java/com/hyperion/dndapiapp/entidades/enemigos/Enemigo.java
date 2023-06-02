package com.hyperion.dndapiapp.entidades.enemigos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class Enemigo {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("imagen")
    private String urlImagen;
    @SerializedName("alineamiento")
    private String alineamiento;
    @SerializedName("tamanio")
    private String tipo;
    @SerializedName("tipo")
    private String tamanio;
    @SerializedName("idiomas")
    private String idiomas;
    @SerializedName("sentidos")
    private String sentidos;
    @SerializedName("habilidades")
    private String habilidades;
    @SerializedName("rasgoEnemigos")
    private List<RasgoEnemigo> rasgoEnemigos;
    @SerializedName("acciones")
    private List<Accion> acciones;
    @SerializedName("desafio")
    private float desafio;
    @SerializedName("claseArmadura")
    private int claseArmadura;
    @SerializedName("puntosGolpe")
    private int puntosGolpe;
    @SerializedName("fuerza")
    private int fuerza;
    @SerializedName("destreza")
    private int destreza;
    @SerializedName("constitucion")
    private int constitucion;
    @SerializedName("inteligencia")
    private int inteligencia;
    @SerializedName("sabiduria")
    private int sabiduria;
    @SerializedName("carisma")
    private int carisma;

    /* =============== CONSTRUCTORES =============== */

    /* =============== METODOS =============== */

    /* =============== GETTERS & SETTERS =============== */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getAlineamiento() {
        return alineamiento;
    }

    public void setAlineamiento(String alineamiento) {
        this.alineamiento = alineamiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getSentidos() {
        return sentidos;
    }

    public void setSentidos(String sentidos) {
        this.sentidos = sentidos;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public List<RasgoEnemigo> getRasgoCriaturas() {
        return rasgoEnemigos;
    }

    public void setRasgoCriaturas(List<RasgoEnemigo> rasgoEnemigos) {
        this.rasgoEnemigos = rasgoEnemigos;
    }

    public List<Accion> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<Accion> acciones) {
        this.acciones = acciones;
    }

    public float getDesafio() {
        return desafio;
    }

    public void setDesafio(float desafio) {
        this.desafio = desafio;
    }

    public int getClaseArmadura() {
        return claseArmadura;
    }

    public void setClaseArmadura(int claseArmadura) {
        this.claseArmadura = claseArmadura;
    }

    public int getPuntosGolpe() {
        return puntosGolpe;
    }

    public void setPuntosGolpe(int puntosGolpe) {
        this.puntosGolpe = puntosGolpe;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getConstitucion() {
        return constitucion;
    }

    public void setConstitucion(int constitucion) {
        this.constitucion = constitucion;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getSabiduria() {
        return sabiduria;
    }

    public void setSabiduria(int sabiduria) {
        this.sabiduria = sabiduria;
    }

    public int getCarisma() {
        return carisma;
    }

    public void setCarisma(int carisma) {
        this.carisma = carisma;
    }
}
