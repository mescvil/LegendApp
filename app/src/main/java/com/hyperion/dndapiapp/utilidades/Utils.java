package com.hyperion.dndapiapp.utilidades;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    private final static String EXPRESION_CONTRASENIA = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
    private final static String EXPRESION_CORREO = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean verifcaContrasenia(String pass) {
        return pass.matches(EXPRESION_CONTRASENIA);
    }

    public static boolean verificaCorreo(String correo) {
        return correo.matches(EXPRESION_CORREO);
    }

    public static List<OrdenablePorNombre> ordenaListaPorNombre(List<OrdenablePorNombre> listaRecibida) {
        List<OrdenablePorNombre> listaOrdenada = new ArrayList<>(listaRecibida);
        listaOrdenada.sort((ordenablePorNombre, t1) -> ordenablePorNombre.getNombre().compareToIgnoreCase(t1.getNombre()));
        return listaOrdenada;
    }
}
