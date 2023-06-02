package com.hyperion.dndapiapp.servicioRest.callbacks;

public interface CallbackCustom<T> {

    void exito(T resultado);

    void fallo(String mensaje);
}
