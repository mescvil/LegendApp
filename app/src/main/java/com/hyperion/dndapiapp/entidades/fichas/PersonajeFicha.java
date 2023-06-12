package com.hyperion.dndapiapp.entidades.fichas;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PersonajeFicha implements Parcelable {

    @SerializedName("id")
    private long id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("edad")
    private int edad;
    @SerializedName("alineamiento")
    private String alineamiento;
    @SerializedName("tamanio")
    private float tamanio;
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
    @SerializedName("claseArmadura")
    private int claseArmadura;
    @SerializedName("raza")
    private RazaPersonaje raza;
    @SerializedName("clase")
    private ClasePersonaje clase;
    @SerializedName("equipamiento")
    private EquipamientoPersonaje equipamiento;
    @SerializedName("historia")
    private HistoriaPersonaje historia;

    /* =============== CONSTRUCTORES =============== */

    /* =============== METODOS =============== */

    protected PersonajeFicha(Parcel in) {
        id = in.readLong();
        nombre = in.readString();
        edad = in.readInt();
        alineamiento = in.readString();
        tamanio = in.readFloat();
        fuerza = in.readInt();
        destreza = in.readInt();
        constitucion = in.readInt();
        inteligencia = in.readInt();
        sabiduria = in.readInt();
        carisma = in.readInt();
        claseArmadura = in.readInt();
        raza = in.readParcelable(RazaPersonaje.class.getClassLoader());
        clase = in.readParcelable(ClasePersonaje.class.getClassLoader());
        equipamiento = in.readParcelable(EquipamientoPersonaje.class.getClassLoader());
        historia = in.readParcelable(HistoriaPersonaje.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nombre);
        dest.writeInt(edad);
        dest.writeString(alineamiento);
        dest.writeFloat(tamanio);
        dest.writeInt(fuerza);
        dest.writeInt(destreza);
        dest.writeInt(constitucion);
        dest.writeInt(inteligencia);
        dest.writeInt(sabiduria);
        dest.writeInt(carisma);
        dest.writeInt(claseArmadura);
        dest.writeParcelable(raza, flags);
        dest.writeParcelable(clase, flags);
        dest.writeParcelable(equipamiento, flags);
        dest.writeParcelable(historia, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PersonajeFicha> CREATOR = new Creator<PersonajeFicha>() {
        @Override
        public PersonajeFicha createFromParcel(Parcel in) {
            return new PersonajeFicha(in);
        }

        @Override
        public PersonajeFicha[] newArray(int size) {
            return new PersonajeFicha[size];
        }
    };

    /* =============== GETTERS & SETTERS =============== */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAlineamiento() {
        return alineamiento;
    }

    public void setAlineamiento(String alineamiento) {
        this.alineamiento = alineamiento;
    }

    public float getTamanio() {
        return tamanio;
    }

    public void setTamanio(float tamanio) {
        this.tamanio = tamanio;
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

    public int getClaseArmadura() {
        return claseArmadura;
    }

    public void setClaseArmadura(int claseArmadura) {
        this.claseArmadura = claseArmadura;
    }

    public RazaPersonaje getRaza() {
        return raza;
    }

    public void setRaza(RazaPersonaje raza) {
        this.raza = raza;
    }

    public ClasePersonaje getClase() {
        return clase;
    }

    public void setClase(ClasePersonaje clase) {
        this.clase = clase;
    }

    public EquipamientoPersonaje getEquipamiento() {
        return equipamiento;
    }

    public void setEquipamiento(EquipamientoPersonaje equipamiento) {
        this.equipamiento = equipamiento;
    }

    public HistoriaPersonaje getHistoria() {
        return historia;
    }

    public void setHistoria(HistoriaPersonaje historia) {
        this.historia = historia;
    }
}
