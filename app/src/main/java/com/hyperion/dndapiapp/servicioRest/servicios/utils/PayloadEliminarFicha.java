package com.hyperion.dndapiapp.servicioRest.servicios.utils;

@SuppressWarnings("unused")
public class PayloadEliminarFicha {
    private String correo;
    private long idFicha;

    public PayloadEliminarFicha(String correo, long idFicha) {
        this.correo = correo;
        this.idFicha = idFicha;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(long idFicha) {
        this.idFicha = idFicha;
    }
}
