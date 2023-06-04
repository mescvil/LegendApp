package com.hyperion.dndapiapp.entidades.clases;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.entidades.competencias.Competencia;
import com.hyperion.dndapiapp.entidades.equipamiento.Arma;
import com.hyperion.dndapiapp.entidades.equipamiento.Armadura;
import com.hyperion.dndapiapp.entidades.equipamiento.Hechizo;
import com.hyperion.dndapiapp.utilidades.OrdenablePorNombre;

import java.util.List;

@SuppressWarnings("unused")
public class Clase implements OrdenablePorNombre {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("dadosGolpe")
    private String dadosGolpe;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("caracteristicaPrincipal")
    private String caracteristicaPrincipal;
    @SerializedName("tiradasSalvacion")
    private String tiradasSalvacion;
    @SerializedName("rasgosClase")
    private List<RasgoClase> rasgosClase;
    @SerializedName("armas")
    private List<Arma> armas;
    @SerializedName("armaduras")
    private List<Armadura> armaduras;
    @SerializedName("hechizos")
    private List<Hechizo> hechizos;
    @SerializedName("especialidades")
    private List<Especialidad> especialidades;
    @SerializedName("competencias")
    private List<Competencia> competencias;

    /* =============== CONSTRUCTORES =============== */

    /* =============== METODOS =============== */

    /* =============== GETTERS & SETTERS =============== */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDadosGolpe() {
        return dadosGolpe;
    }

    public void setDadosGolpe(String dadosGolpe) {
        this.dadosGolpe = dadosGolpe;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristicaPrincipal() {
        return caracteristicaPrincipal;
    }

    public void setCaracteristicaPrincipal(String caracteristicaPrincipal) {
        this.caracteristicaPrincipal = caracteristicaPrincipal;
    }

    public String getTiradasSalvacion() {
        return tiradasSalvacion;
    }

    public void setTiradasSalvacion(String tiradasSalvacion) {
        this.tiradasSalvacion = tiradasSalvacion;
    }

    public List<RasgoClase> getRasgosClase() {
        return rasgosClase;
    }

    public void setRasgosClase(List<RasgoClase> rasgosClase) {
        this.rasgosClase = rasgosClase;
    }

    public List<Arma> getArmas() {
        return armas;
    }

    public void setArmas(List<Arma> armas) {
        this.armas = armas;
    }

    public List<Armadura> getArmaduras() {
        return armaduras;
    }

    public void setArmaduras(List<Armadura> armaduras) {
        this.armaduras = armaduras;
    }

    public List<Hechizo> getHechizos() {
        return hechizos;
    }

    public void setHechizos(List<Hechizo> hechizos) {
        this.hechizos = hechizos;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public List<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<Competencia> competencias) {
        this.competencias = competencias;
    }
}
