package com.hyperion.dndapiapp.entidades.glosario.clases;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.entidades.glosario.competencias.Competencia;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Arma;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Armadura;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Hechizo;
import com.hyperion.dndapiapp.utilidades.GetNombreInterface;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Clase implements GetNombreInterface, Parcelable {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("imagen")
    private String imagen;
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

    protected Clase(Parcel in) {
        nombre = in.readString();
        imagen = in.readString();
        dadosGolpe = in.readString();
        descripcion = in.readString();
        caracteristicaPrincipal = in.readString();
        tiradasSalvacion = in.readString();
        rasgosClase = in.createTypedArrayList(RasgoClase.CREATOR);
        armas = in.createTypedArrayList(Arma.CREATOR);
        armaduras = in.createTypedArrayList(Armadura.CREATOR);
        hechizos = in.createTypedArrayList(Hechizo.CREATOR);
        competencias = in.createTypedArrayList(Competencia.CREATOR);
        especialidades = in.createTypedArrayList(Especialidad.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(imagen);
        dest.writeString(dadosGolpe);
        dest.writeString(descripcion);
        dest.writeString(caracteristicaPrincipal);
        dest.writeString(tiradasSalvacion);
        dest.writeTypedList(rasgosClase);
        dest.writeTypedList(armas);
        dest.writeTypedList(armaduras);
        dest.writeTypedList(hechizos);
        dest.writeTypedList(competencias);
        dest.writeTypedList(especialidades);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Clase> CREATOR = new Creator<Clase>() {
        @Override
        public Clase createFromParcel(Parcel in) {
            return new Clase(in);
        }

        @Override
        public Clase[] newArray(int size) {
            return new Clase[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return getNombre();
    }

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

    public List<Armadura> getArmadurasSinEscudo() {
        List<Armadura> lista = new ArrayList<>();
        for (Armadura armadura : armaduras) {
            if (!armadura.getNombre().equalsIgnoreCase("escudo"))
                lista.add(armadura);
        }

        return lista;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
