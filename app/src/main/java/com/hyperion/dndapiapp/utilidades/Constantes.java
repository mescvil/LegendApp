package com.hyperion.dndapiapp.utilidades;

@SuppressWarnings("unused")
public abstract class Constantes {

    /* ================= Bundles  ================= */
    public static final String IMAGEN_USUARIO_BUNDLE = "imagenUsuario";
    public static final String CORREO_USUARIO_BUNDLE = "correo";
    public static final String PASS_USUARIO_BUNDLE = "pass";
    public static final String CIERRA_SESION_BUNDLE = "cierraSesion";
    public static final String USUARIO_BUNDLE = "usuario";
    public static final String ENEMIGO_BUNDLE = "enemigo";
    public static final String RASGOS_ENEMIGOS_BUNDLE = "ragosEnemigos";
    public static final String ACCIONES_ENEMIGOS_BUNDLE = "accionesEnemigos";
    public static final String HECHIZOS_BUNDLE = "hechizos";
    public static final String TRASFONDO_BUNDLE = "trasfondos";
    public static final String TRASFONDO_COMPETENCIAS_BUNDLE = "trasfondosComp";
    public static final String ARMA_BUNDLE = "arma";
    public static final String ARMADURA_BUNDLE = "armadura";
    public static final String RAZA_BUNDLE = "raza";
    public static final String RASGOS_RAZA_BUNDLE = "rasgosRaza";
    public static final String RASGOS_CLASE_BUNDLE = "rasgosClase";
    public static final String CLASE_BUNDLE = "clase";

    /* ================= Actividades  ================= */
    public final static int ACTIVIDAD_LOGIN = 1;
    public final static int ACTIVIDAD_MAIN = 2;
    public final static int ACTIVIDAD_REGISTER = 3;
    public final static int ACTIVIDAD_IMAGEN = 3;

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
    public static final String URL_BASE_IMAGEN_RAZAS = "https://raw.githubusercontent.com/mescvil/api-dnd/master/api/razas/";
    public static final String URL_BASE_IMAGEN_CLASES = "https://raw.githubusercontent.com/mescvil/api-dnd/master/api/clases/";

    /* ============== Tipo de item lista ============== */
    public static final int ITEM_ENEMIGO = 0;
    public static final int ITEM_HECHIZO = 1;
    public static final int ITEM_CLASE = 2;
    public static final int ITEM_EQUIPAMIENTO = 3;
    public static final int ITEM_RAZA = 4;
    public static final int ITEM_TRASFONDO = 5;
    public static final int ITEM_COMPENTENCIA = 6;
}
