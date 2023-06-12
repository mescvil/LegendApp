package com.hyperion.dndapiapp.entidades.fichas;

import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings("unused")
public class ClasePersonaje implements Parcelable {

    private long idClase;
    private String nombre;
    private String dadosGolpe;
    private String descripcion;
    private String caracteristicaPrincipal;
    private String tiradasSalvacion;
    private String rasgos;
    private String especialidad;

    /* =============== CONSTRUCTORES =============== */

    /* =============== METODOS =============== */

    protected ClasePersonaje(Parcel in) {
        idClase = in.readLong();
        nombre = in.readString();
        dadosGolpe = in.readString();
        descripcion = in.readString();
        caracteristicaPrincipal = in.readString();
        tiradasSalvacion = in.readString();
        rasgos = in.readString();
        especialidad = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idClase);
        dest.writeString(nombre);
        dest.writeString(dadosGolpe);
        dest.writeString(descripcion);
        dest.writeString(caracteristicaPrincipal);
        dest.writeString(tiradasSalvacion);
        dest.writeString(rasgos);
        dest.writeString(especialidad);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ClasePersonaje> CREATOR = new Creator<ClasePersonaje>() {
        @Override
        public ClasePersonaje createFromParcel(Parcel in) {
            return new ClasePersonaje(in);
        }

        @Override
        public ClasePersonaje[] newArray(int size) {
            return new ClasePersonaje[size];
        }
    };

    /* =============== GETTERS & SETTERS =============== */

    public long getIdClase() {
        return idClase;
    }

    public void setIdClase(long idClase) {
        this.idClase = idClase;
    }

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

    public String getRasgos() {
        return rasgos;
    }

    public void setRasgos(String rasgos) {
        this.rasgos = rasgos;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
