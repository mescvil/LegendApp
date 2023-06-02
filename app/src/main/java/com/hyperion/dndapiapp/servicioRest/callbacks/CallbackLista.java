package com.hyperion.dndapiapp.servicioRest.callbacks;

import java.util.List;

public interface CallbackLista<T> {

    void exito(List<T> listaResultado);

    void fallo();
}
