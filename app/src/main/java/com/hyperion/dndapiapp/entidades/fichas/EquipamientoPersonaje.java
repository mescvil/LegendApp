package com.hyperion.dndapiapp.entidades.fichas;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.entidades.glosario.clases.Clase;
import com.hyperion.dndapiapp.utilidades.GetNombreInterface;
import com.hyperion.dndapiapp.utilidades.Utils;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class EquipamientoPersonaje implements Parcelable {

    @SerializedName("idEquipamiento")
    private long idEquipamiento;
    @SerializedName("armas")
    private String armas;
    @SerializedName("armaduras")
    private String armaduras;
    @SerializedName("hechizos")
    private String hechizos;

    /* =============== CONSTRUCTORES =============== */

    public EquipamientoPersonaje(String armas, String armaduras, String hechizos) {
        this.armas = armas;
        this.armaduras = armaduras;
        this.hechizos = hechizos;
    }

    public EquipamientoPersonaje(Clase clase) {
        List<GetNombreInterface> listaArmas = new ArrayList<>(clase.getArmas());
        List<GetNombreInterface> listaArmaduras = new ArrayList<>(clase.getArmaduras());
        List<GetNombreInterface> listaHechizos = new ArrayList<>(clase.getHechizos());

        armas = Utils.listaToString(listaArmas);
        armaduras = Utils.listaToString(listaArmaduras);
        hechizos = Utils.listaToString(listaHechizos);
    }

    /* =============== METODOS =============== */

    protected EquipamientoPersonaje(Parcel in) {
        idEquipamiento = in.readLong();
        armas = in.readString();
        armaduras = in.readString();
        hechizos = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idEquipamiento);
        dest.writeString(armas);
        dest.writeString(armaduras);
        dest.writeString(hechizos);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EquipamientoPersonaje> CREATOR = new Creator<EquipamientoPersonaje>() {
        @Override
        public EquipamientoPersonaje createFromParcel(Parcel in) {
            return new EquipamientoPersonaje(in);
        }

        @Override
        public EquipamientoPersonaje[] newArray(int size) {
            return new EquipamientoPersonaje[size];
        }
    };

    /* =============== GETTERS & SETTERS =============== */

    public long getIdEquipamiento() {
        return idEquipamiento;
    }

    public void setIdEquipamiento(long idEquipamiento) {
        this.idEquipamiento = idEquipamiento;
    }

    public String getArmas() {
        return armas;
    }

    public void setArmas(String armas) {
        this.armas = armas;
    }

    public String getArmaduras() {
        return armaduras;
    }

    public void setArmaduras(String armaduras) {
        this.armaduras = armaduras;
    }

    public String getHechizos() {
        return hechizos;
    }

    public void setHechizos(String hechizos) {
        this.hechizos = hechizos;
    }
}
