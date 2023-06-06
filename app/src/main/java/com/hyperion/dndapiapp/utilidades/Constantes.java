package com.hyperion.dndapiapp.utilidades;

@SuppressWarnings("unused")
public abstract class Constantes {

    /* ================= Bundles  ================= */
    public static final String CORREO_USUARIO_BUNDLE = "correo";
    public static final String PASS_USUARIO_BUNDLE = "pass";
    public static final String CIERRA_SESION_BUNDLE = "cierraSesion";
    public static final String USUARIO_BUNDLE = "usuario";
    public static final String ENEMIGO_BUNDLE = "enemigo";
    public static final String RASGOS_ENEMIGOS_BUNDLE = "ragosEnemigos";
    public static final String ACCIONES_ENEMIGOS_BUNDLE = "accionesEnemigos";
    public static final String HECHIZOS_BUNDLE = "hechizos";

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

    /* ============== Tipo de item lista ============== */
    public static final int ITEM_ENEMIGO = 0;
    public static final int ITEM_HECHIZO = 1;
    public static final int ITEM_CLASE = 2;
    public static final int ITEM_EQUIPAMIENTO = 3;
    public static final int ITEM_RAZA = 4;
    public static final int ITEM_TRASFONDO = 5;
    public static final int ITEM_COMPENTENCIA = 6;
}
