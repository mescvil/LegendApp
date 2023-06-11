package com.hyperion.dndapiapp.entidades.equipamiento;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hyperion.dndapiapp.adaptadores.recyclerView.GenericoRecyclerView;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@SuppressWarnings("unused")
public class Armadura extends RealmObject
        implements Equipamiento, Parcelable, GenericoRecyclerView {

    @SerializedName("nombre")
    @PrimaryKey
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

    public Armadura() {
    }

    /* =============== METODOS =============== */


    protected Armadura(Parcel in) {
        nombre = in.readString();
        tipo = in.readString();
        peso = in.readInt();
        claseArmadura = in.readString();
        precio = in.readString();
        fuerzaRequerida = in.readInt();
        desventajaSigilo = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(tipo);
        dest.writeInt(peso);
        dest.writeString(claseArmadura);
        dest.writeString(precio);
        dest.writeInt(fuerzaRequerida);
        dest.writeByte((byte) (desventajaSigilo ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Armadura> CREATOR = new Creator<Armadura>() {
        @Override
        public Armadura createFromParcel(Parcel in) {
            return new Armadura(in);
        }

        @Override
        public Armadura[] newArray(int size) {
            return new Armadura[size];
        }
    };

    /* =============== GETTERS & SETTERS =============== */

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getDescripcion() {
        return "Clase de armadura: " + claseArmadura;
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
