package com.hyperion.dndapiapp.servicioRest.servicios.utils;

import com.hyperion.dndapiapp.entidades.fichas.PersonajeFicha;

public class PayloadFicha {

    private final String correo;
    private final PersonajeFicha ficha;

    public PayloadFicha(String correo, PersonajeFicha ficha) {
        this.correo = correo;
        this.ficha = ficha;
    }

    public String getCorreo() {
        return correo;
    }

    public PersonajeFicha getPersonajeFicha() {
        return ficha;
    }
}
