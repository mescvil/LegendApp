package com.hyperion.dndapiapp.controladores;

import static com.hyperion.dndapiapp.utilidades.Constantes.DB_NAME;

import android.content.Context;

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
import com.hyperion.dndapiapp.sqlite.FavoritoClase;
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
    private final List<Equipamiento> listaEquipamiento;
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

    public void cargaRecursos() {
        if (primeraCarga) {
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

        } else
            notificaExito();
    }

    /* ================= Favoritos ================= */
    public void iniciaFavoritos(Context context) {
        if (primeraCarga)
            new Thread(() -> {
                sqLiteHelper = SQLiteHelper.getInstance(context, DB_NAME, null, 1);

                sqLiteHelper.iniciaConexion();
                favoritos = sqLiteHelper.selectAll();
                sqLiteHelper.stop();

            }).start();
    }

    public boolean isFavorito(String nombre) {
        return favoritos.contains(new Favorito(nombre));
    }

    public void removeFavorito(Favorito favorito) {
        if (isFavorito(favorito.getNombre())) {
            favoritos.remove(favorito);
            sqLiteHelper.iniciaConexion();
            sqLiteHelper.delete(favorito.getNombre());
            sqLiteHelper.stop();
        }
    }

    public void addFavorito(Favorito favorito) {
        if (!favoritos.contains(favorito)) {
            sqLiteHelper.iniciaConexion();
            sqLiteHelper.insert(favorito);
            favoritos.add(favorito);
            sqLiteHelper.stop();
        }
    }

    public void gestionaFavoritosClase(List<FavoritoClase> favoritosClase) {
        new Thread(() -> {
            sqLiteHelper.iniciaConexion();
            for (FavoritoClase favoritoClase : favoritosClase) {
                Favorito favorito = favoritoClase.getFavorito();

                if (favoritoClase.isFavorito() && !favoritos.contains(favorito)) {
                    sqLiteHelper.insert(favorito);
                    favoritos.add(favorito);

                } else if (!favoritoClase.isFavorito() && isFavorito(favorito.getNombre())) {
                    sqLiteHelper.delete(favorito.getNombre());
                    favoritos.remove(favorito);
                }
            }
            sqLiteHelper.stop();
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

    /* ================= Getters ================= */

    public List<Enemigo> getListaEnemigos() {
        return listaEnemigos;
    }

    public List<Clase> getListaClases() {
        return listaClases;
    }

    public List<Raza> getListaRazas() {
        return listaRazas;
    }

    public List<Hechizo> getListaHechizos() {
        return listaHechizos;
    }

    public List<Equipamiento> getListaEquipamiento() {
        return listaEquipamiento;
    }

    public List<Trasfondo> getListaTrasfondos() {
        return listaTrasfondos;
    }

    public List<Competencia> getListaCompentencias() {
        return listaCompentencias;
    }

    public List<Favorito> getFavoritos() {
        return favoritos;
    }
}
