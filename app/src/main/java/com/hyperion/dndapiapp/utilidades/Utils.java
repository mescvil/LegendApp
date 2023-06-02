package com.hyperion.dndapiapp.utilidades;

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
}
