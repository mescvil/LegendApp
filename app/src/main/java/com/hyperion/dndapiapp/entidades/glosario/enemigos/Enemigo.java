package com.hyperion.dndapiapp.entidades.glosario.enemigos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.utilidades.GetNombreInterface;

import java.util.List;

@SuppressWarnings("unused")
public class Enemigo implements GetNombreInterface, Parcelable {
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("imagen")
    private String urlImagen;
    @SerializedName("alineamiento")
    private String alineamiento;
    @SerializedName("tipo")
    private String tipo;
    @SerializedName("tamanio")
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

    protected Enemigo(Parcel in) {
        nombre = in.readString();
        urlImagen = in.readString();
        alineamiento = in.readString();
        tipo = in.readString();
        tamanio = in.readString();
        idiomas = in.readString();
        sentidos = in.readString();
        habilidades = in.readString();
        rasgoEnemigos = in.createTypedArrayList(RasgoEnemigo.CREATOR);
        acciones = in.createTypedArrayList(Accion.CREATOR);
        desafio = in.readFloat();
        claseArmadura = in.readInt();
        puntosGolpe = in.readInt();
        fuerza = in.readInt();
        destreza = in.readInt();
        constitucion = in.readInt();
        inteligencia = in.readInt();
        sabiduria = in.readInt();
        carisma = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(urlImagen);
        dest.writeString(alineamiento);
        dest.writeString(tipo);
        dest.writeString(tamanio);
        dest.writeString(idiomas);
        dest.writeString(sentidos);
        dest.writeString(habilidades);
        dest.writeTypedList(rasgoEnemigos);
        dest.writeTypedList(acciones);
        dest.writeFloat(desafio);
        dest.writeInt(claseArmadura);
        dest.writeInt(puntosGolpe);
        dest.writeInt(fuerza);
        dest.writeInt(destreza);
        dest.writeInt(constitucion);
        dest.writeInt(inteligencia);
        dest.writeInt(sabiduria);
        dest.writeInt(carisma);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Enemigo> CREATOR = new Creator<Enemigo>() {
        @Override
        public Enemigo createFromParcel(Parcel in) {
            return new Enemigo(in);
        }

        @Override
        public Enemigo[] newArray(int size) {
            return new Enemigo[size];
        }
    };

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
