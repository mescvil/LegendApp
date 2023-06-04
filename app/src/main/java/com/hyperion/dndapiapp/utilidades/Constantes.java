package com.hyperion.dndapiapp.utilidades;

@SuppressWarnings("unused")
public abstract class Constantes {

    /* ================= Bundles  ================= */
    public static final String CORREO_USUARIO = "correo";
    public static final String PASS_USUARIO = "pass";
    public static final String CIERRA_SESION = "cierraSesion";

    /* ================= Actividades  ================= */
    public final static int ACTIVIDAD_LOGIN = 1;
    public final static int ACTIVIDAD_MAIN = 2;
    public final static int ACTIVIDAD_REGISTER = 3;

    /* ============== Filtros busquedas ============== */
    public static final String[] filtros = new String[]{"Enemigos", "Clases", "Razas", "Hechizos", "Equipamiento", "Trasfondos", "Competencias"};
    public static final int POSICION_ENEMIGOS = 0;
    public static final int POSICION_CLASES = 1;
    public static final int POSICION_RAZAS = 2;
    public static final int POSICION_HECHIZOS = 3;
    public static final int POSICION_EQUIPAMIENTO = 4;
    public static final int POSICION_TRASFONDOS = 5;
    public static final int POSICION_COMPETENCIAS = 6;

    /* ============== Url imagenes ============== */
    public static final String URL_BASE_IMAGEN_CRIATURAS = "https://raw.githubusercontent.com/mescvil/api-dnd/master/api/criaturas/";
}
