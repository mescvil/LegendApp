package com.hyperion.dndapiapp.controladores;

import com.hyperion.dndapiapp.entidades.clases.Clase;
import com.hyperion.dndapiapp.entidades.competencias.Competencia;
import com.hyperion.dndapiapp.entidades.enemigos.Enemigo;
import com.hyperion.dndapiapp.entidades.equipamiento.Equipamiento;
import com.hyperion.dndapiapp.entidades.equipamiento.Hechizo;
import com.hyperion.dndapiapp.entidades.razas.Raza;
import com.hyperion.dndapiapp.entidades.trasfondos.Trasfondo;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioClases;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioCompetencias;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioEnemigos;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioEquipamiento;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioRazas;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioTrasfondos;
import com.hyperion.dndapiapp.sqlite.Favorito;
import com.hyperion.dndapiapp.sqlite.SQLiteHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controlador {

    private static Controlador controlador;
    private boolean primeraCarga;

    /* Observadores */
    List<ObservadorDatos> observadoresDatos;

    /* Servicios Rest */
    private final ServicioEnemigos servicioEnemigos;
    private final ServicioEquipamiento servicioEquipamiento;
    private final ServicioClases servicioClases;
    private final ServicioRazas servicioRazas;
    private final ServicioTrasfondos servicioTrasfondos;
    private final ServicioCompetencias servicioCompetencias;

    /* Lista de elementos */
    private List<Enemigo> listaEnemigos;
    private List<Clase> listaClases;
    private List<Raza> listaRazas;
    private List<Hechizo> listaHechizos;
    private List<Equipamiento> listaEquipamiento;
    private List<Trasfondo> listaTrasfondos;
    private List<Competencia> listaCompentencias;

    /* Favoritos */
    private SQLiteHelper sqLiteHelper;
    private List<Favorito> favoritos;

    private Controlador() {
        primeraCarga = true;
        observadoresDatos = new ArrayList<>();

        servicioEnemigos = ServicioEnemigos.getInstance();
        servicioClases = ServicioClases.getInstance();
        servicioEquipamiento = ServicioEquipamiento.getInstance();
        servicioRazas = ServicioRazas.getInstance();
        servicioTrasfondos = ServicioTrasfondos.getInstance();
        servicioCompetencias = ServicioCompetencias.getInstance();

        listaEnemigos = new ArrayList<>();
        listaClases = new ArrayList<>();
        listaRazas = new ArrayList<>();
        listaHechizos = new ArrayList<>();
        listaEquipamiento = new ArrayList<>();
        listaTrasfondos = new ArrayList<>();
        listaCompentencias = new ArrayList<>();
    }

    public static Controlador getInstance() {
        if (controlador == null)
            controlador = new Controlador();
        return controlador;
    }

    public void cargaRecurosApi() {
        if (primeraCarga)
            new Thread(() -> {
                try {
                    listaEnemigos = servicioEnemigos.getAllEnemigosSync();
                    listaClases = servicioClases.getAllClasesSync();
                    listaRazas = servicioRazas.getAllRazasSync();
                    listaHechizos = servicioEquipamiento.getAllHechizosSync();
                    listaEquipamiento.addAll(servicioEquipamiento.getAllArmadurasSync());
                    listaEquipamiento.addAll(servicioEquipamiento.getAllArmasSync());
                    listaTrasfondos = servicioTrasfondos.getAllTrasfondosSync();
                    listaCompentencias = servicioCompetencias.getAllCompetenciasSync();

                    primeraCarga = false;
                    notificaExito();

                } catch (IOException e) {
                    notificaError();
                }
            }).start();
    }

    /* ================= Observadores ================= */

    public void suscribirse(ObservadorDatos observador) {
        observadoresDatos.add(observador);
    }

    private void notificaExito() {
        for (ObservadorDatos observador : observadoresDatos) {
            observador.exitoObteniendoDatos();
        }
    }

    private void notificaError() {
        for (ObservadorDatos observador : observadoresDatos) {
            observador.falloObteniendoDatos();
        }
    }
}
