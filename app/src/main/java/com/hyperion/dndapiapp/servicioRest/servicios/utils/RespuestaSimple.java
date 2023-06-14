package com.hyperion.dndapiapp.servicioRest.servicios.utils;

@SuppressWarnings("unused")
public class RespuestaSimple {

    private final String codigo;
    private final String mensaje;

    public RespuestaSimple(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }
}
