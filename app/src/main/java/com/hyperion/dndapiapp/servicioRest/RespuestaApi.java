package com.hyperion.dndapiapp.servicioRest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class RespuestaApi<T> {

    @SerializedName("elementos")
    private int nElementos;
    @SerializedName("paginas")
    private int paginas;
    @SerializedName("siguiente")
    private int siguiente;
    @SerializedName("anterior")
    private int anterior;
    @SerializedName("resultado")
    private List<T> resultado;

    public int getnElementos() {
        return nElementos;
    }

    public void setnElementos(int nElementos) {
        this.nElementos = nElementos;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(int siguiente) {
        this.siguiente = siguiente;
    }

    public int getAnterior() {
        return anterior;
    }

    public void setAnterior(int anterior) {
        this.anterior = anterior;
    }

    public List<T> getResultado() {
        return resultado;
    }

    public void setResultado(List<T> resultado) {
        this.resultado = resultado;
    }
}
