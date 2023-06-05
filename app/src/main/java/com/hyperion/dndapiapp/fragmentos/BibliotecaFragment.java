package com.hyperion.dndapiapp.fragmentos;

import static com.hyperion.dndapiapp.utilidades.Constantes.POSICION_CLASES;
import static com.hyperion.dndapiapp.utilidades.Constantes.POSICION_COMPETENCIAS;
import static com.hyperion.dndapiapp.utilidades.Constantes.POSICION_ENEMIGOS;
import static com.hyperion.dndapiapp.utilidades.Constantes.POSICION_EQUIPAMIENTO;
import static com.hyperion.dndapiapp.utilidades.Constantes.POSICION_HECHIZOS;
import static com.hyperion.dndapiapp.utilidades.Constantes.POSICION_RAZAS;
import static com.hyperion.dndapiapp.utilidades.Constantes.POSICION_TRASFONDOS;
import static com.hyperion.dndapiapp.utilidades.Constantes.filtros;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.AdaptadorMix;
import com.hyperion.dndapiapp.databinding.FragmentBibliotecaBinding;
import com.hyperion.dndapiapp.dialogos.LoadingDialog;
import com.hyperion.dndapiapp.entidades.clases.Clase;
import com.hyperion.dndapiapp.entidades.competencias.Competencia;
import com.hyperion.dndapiapp.entidades.enemigos.Enemigo;
import com.hyperion.dndapiapp.entidades.equipamiento.Arma;
import com.hyperion.dndapiapp.entidades.equipamiento.Armadura;
import com.hyperion.dndapiapp.entidades.equipamiento.Equipamiento;
import com.hyperion.dndapiapp.entidades.equipamiento.Hechizo;
import com.hyperion.dndapiapp.entidades.razas.Raza;
import com.hyperion.dndapiapp.entidades.trasfondos.Trasfondo;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackLista;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioClases;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioCompetencias;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioEnemigos;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioEquipamiento;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioRazas;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioTrasfondos;
import com.hyperion.dndapiapp.utilidades.OrdenablePorNombre;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BibliotecaFragment extends Fragment {

    /* Utiles */
    private final boolean[] filtrosSeleccionados;
    private SharedPreferences.Editor editor;
    private FragmentBibliotecaBinding binding;

    /* Servicios */
    private final ServicioEnemigos servicioEnemigos;
    private final ServicioEquipamiento servicioEquipamiento;
    private final ServicioClases servicioClases;
    private final ServicioRazas servicioRazas;
    private final ServicioTrasfondos servicioTrasfondos;
    private final ServicioCompetencias servicioCompetencias;

    /* Componentes */
    private RecyclerView recyclerView;
    private AdaptadorMix adaptadorMix;
    private LoadingDialog loadingDialog;
    private TextView titulo;

    /* Listas de objetos */
    private List<Enemigo> listaEnemigos;
    private List<Clase> listaClases;
    private List<Raza> listaRazas;
    private List<Hechizo> listaHechizos;
    private List<Equipamiento> listaEquipamiento;
    private List<Trasfondo> listaTrasfondos;
    private List<Competencia> listaCompentencias;

    public BibliotecaFragment() {
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

        filtrosSeleccionados = new boolean[filtros.length];
    }

    @NonNull
    @Contract(" -> new")
    public static BibliotecaFragment newInstance() {
        return new BibliotecaFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedPreferences.edit();

        for (int i = 0; i < filtros.length; i++) {
            filtrosSeleccionados[i] = sharedPreferences.getBoolean(filtros[i], true);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBibliotecaBinding.inflate(inflater, container, false);
        iniciarFragmento();
        binding.botonFiltro.setOnClickListener(view -> listenerFiltro());
        binding.campoBusqueda.addTextChangedListener(new OurTextWatcher());
        return binding.getRoot();
    }

    private void listenerFiltro() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.DialogoFiltrosTheme);

        builder.setMultiChoiceItems(filtros, filtrosSeleccionados, (dialog, which, isChecked) -> {
            filtrosSeleccionados[which] = isChecked;
        });
        builder.setCancelable(false);

        builder.setPositiveButton("Hecho", (dialog, which) -> {
            actualizaListaFiltros();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> {
        });

        builder.setNeutralButton("Limpiar selección", (dialog, which) -> {
            Arrays.fill(filtrosSeleccionados, false);
            limpiaLista();
        });


        builder.create();
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void limpiaLista() {
        adaptadorMix.setElementos(new ArrayList<>());
        recyclerView.setBackgroundResource(R.drawable.imagen_biblioteca);
        binding.campoBusqueda.setText("");
        cambiaTituloBusqueda();

        editor.clear();
        editor.commit();
    }

    private void actualizaListaFiltros() {
        recyclerView.setBackgroundResource(R.color.fondo);
        cambiaTituloBusqueda();

        for (int i = 0; i < filtrosSeleccionados.length; i++) {
            editor.putBoolean(filtros[i], filtrosSeleccionados[i]);
        }
        editor.commit();
        realizaPeticionFiltrada();
        recyclerView.scrollToPosition(0);
    }

    private void iniciarFragmento() {
        this.loadingDialog = new LoadingDialog(getContext());
        this.titulo = binding.tituloBusqueda;

        adaptadorMix = new AdaptadorMix(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView = binding.listaBiblioteca;
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorMix);

        if ((filtrosSeleccionados[POSICION_ENEMIGOS]) || (filtrosSeleccionados[POSICION_CLASES])
                || (filtrosSeleccionados[POSICION_HECHIZOS]) || (filtrosSeleccionados[POSICION_RAZAS])
                || (filtrosSeleccionados[POSICION_COMPETENCIAS]) || (filtrosSeleccionados[POSICION_EQUIPAMIENTO])
                || (filtrosSeleccionados[POSICION_TRASFONDOS]))
            realizaPeticionFiltrada();
        else
            recyclerView.setBackgroundResource(R.drawable.imagen_biblioteca);

        cambiaTituloBusqueda();
    }

    @SuppressLint("DefaultLocale")
    private void cambiaTituloBusqueda() {
        int nFiltros = 0;
        for (boolean filtrosSeleccionado : filtrosSeleccionados) {
            if (filtrosSeleccionado) {
                nFiltros++;
            }
        }
        titulo.setText(String.format("Filtros apliacados: %d\nNº de elementos : %d", nFiltros, adaptadorMix.getItemCount()));
    }

    private void realizaPeticionFiltrada() {
        List<OrdenablePorNombre> listaFiltrada = new ArrayList<>();
        adaptadorMix.clearElementos();
        binding.campoBusqueda.setText("");


        if ((filtrosSeleccionados[POSICION_ENEMIGOS]) || (filtrosSeleccionados[POSICION_CLASES])
                || (filtrosSeleccionados[POSICION_HECHIZOS]) || (filtrosSeleccionados[POSICION_RAZAS])
                || (filtrosSeleccionados[POSICION_COMPETENCIAS]) || (filtrosSeleccionados[POSICION_EQUIPAMIENTO])
                || (filtrosSeleccionados[POSICION_TRASFONDOS])) {

            /* Si las alguna lista esta vacia en la peticon se llama al dialogo de cargando */
            if (listaEnemigos.isEmpty() || listaHechizos.isEmpty() || listaClases.isEmpty()
                    || listaCompentencias.isEmpty() || listaTrasfondos.isEmpty() || listaEquipamiento.isEmpty()
                    || listaRazas.isEmpty()) {

                loadingDialog.show("Cargando elementos");

                if (filtrosSeleccionados[POSICION_ENEMIGOS]) {
                    if (listaEnemigos.isEmpty()) {
                        realizaPeticionEnemigos();
                    } else {
                        listaFiltrada.addAll(listaEnemigos);
                        loadingDialog.dismiss();
                    }
                }

                if (filtrosSeleccionados[POSICION_CLASES]) {
                    if (listaClases.isEmpty()) {
                        realizaPeticionClases();
                    } else {
                        listaFiltrada.addAll(listaClases);
                        loadingDialog.dismiss();
                    }
                }

                if (filtrosSeleccionados[POSICION_HECHIZOS]) {
                    if (listaHechizos.isEmpty()) {
                        realizaPeticionHechizos();
                    } else {
                        listaFiltrada.addAll(listaHechizos);
                        loadingDialog.dismiss();
                    }
                }

                if (filtrosSeleccionados[POSICION_EQUIPAMIENTO]) {
                    if (listaEquipamiento.isEmpty()) {
                        realizaPeticionEquipamiento();
                    } else {
                        listaFiltrada.addAll(listaEquipamiento);
                        loadingDialog.dismiss();
                    }
                }

                if (filtrosSeleccionados[POSICION_RAZAS]) {
                    if (listaRazas.isEmpty()) {
                        realizaPeticionRazas();
                    } else {
                        listaFiltrada.addAll(listaRazas);
                        loadingDialog.dismiss();
                    }
                }

                if (filtrosSeleccionados[POSICION_TRASFONDOS]) {
                    if (listaTrasfondos.isEmpty()) {
                        realizaPeticionTrasfondos();
                    } else {
                        listaFiltrada.addAll(listaTrasfondos);
                        loadingDialog.dismiss();
                    }
                }

                if (filtrosSeleccionados[POSICION_COMPETENCIAS]) {
                    if (listaCompentencias.isEmpty()) {
                        realizaPeticionCompetencias();
                    } else {
                        listaFiltrada.addAll(listaCompentencias);
                        loadingDialog.dismiss();
                    }
                }

                /* En caso de estar todas cargadas */
            } else {
                if (filtrosSeleccionados[POSICION_ENEMIGOS]) listaFiltrada.addAll(listaEnemigos);
                if (filtrosSeleccionados[POSICION_CLASES]) listaFiltrada.addAll(listaClases);
                if (filtrosSeleccionados[POSICION_HECHIZOS]) listaFiltrada.addAll(listaHechizos);
                if (filtrosSeleccionados[POSICION_RAZAS]) listaFiltrada.addAll(listaRazas);
                if (filtrosSeleccionados[POSICION_COMPETENCIAS]) listaFiltrada.addAll(listaCompentencias);
                if (filtrosSeleccionados[POSICION_EQUIPAMIENTO]) listaFiltrada.addAll(listaEquipamiento);
                if (filtrosSeleccionados[POSICION_TRASFONDOS]) listaFiltrada.addAll(listaTrasfondos);

            }
            adaptadorMix.addElementos(listaFiltrada);
            cambiaTituloBusqueda();
        } else {
            recyclerView.setBackgroundResource(R.drawable.imagen_biblioteca);
            cambiaTituloBusqueda();
        }
    }

    private void realizaPeticionCompetencias() {
        servicioCompetencias.getAllCompetencias(new CallbackLista<Competencia>() {
            @Override
            public void exito(List<Competencia> listaResultado) {
                listaCompentencias.addAll(listaResultado);
                List<OrdenablePorNombre> lista = new ArrayList<>(listaResultado);
                adaptadorMix.addElementos(lista);
                cambiaTituloBusqueda();
                loadingDialog.dismiss();
            }

            @Override
            public void fallo() {
                Toast.makeText(getContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });
    }

    private void realizaPeticionTrasfondos() {
        servicioTrasfondos.getAllTrasfondos(new CallbackLista<Trasfondo>() {
            @Override
            public void exito(List<Trasfondo> listaResultado) {
                listaTrasfondos.addAll(listaResultado);
                List<OrdenablePorNombre> lista = new ArrayList<>(listaResultado);
                adaptadorMix.addElementos(lista);
                cambiaTituloBusqueda();
                loadingDialog.dismiss();
            }

            @Override
            public void fallo() {
                Toast.makeText(getContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });
    }

    private void realizaPeticionRazas() {
        servicioRazas.getAllRazas(new CallbackLista<Raza>() {
            @Override
            public void exito(List<Raza> listaResultado) {
                listaRazas.addAll(listaResultado);
                List<OrdenablePorNombre> lista = new ArrayList<>(listaResultado);
                adaptadorMix.addElementos(lista);
                cambiaTituloBusqueda();
                loadingDialog.dismiss();
            }

            @Override
            public void fallo() {
                Toast.makeText(getContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });
    }

    private void realizaPeticionEquipamiento() {
        servicioEquipamiento.getAllArmas(new CallbackLista<Arma>() {
            @Override
            public void exito(List<Arma> listaResultado) {
                listaEquipamiento.addAll(listaResultado);
                List<OrdenablePorNombre> lista = new ArrayList<>(listaResultado);
                adaptadorMix.addElementos(lista);
                cambiaTituloBusqueda();
                loadingDialog.dismiss();
            }

            @Override
            public void fallo() {
                Toast.makeText(getContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });

        servicioEquipamiento.getAllArmaduras(new CallbackLista<Armadura>() {
            @Override
            public void exito(List<Armadura> listaResultado) {
                listaEquipamiento.addAll(listaResultado);
                List<OrdenablePorNombre> lista = new ArrayList<>(listaResultado);
                adaptadorMix.addElementos(lista);
                cambiaTituloBusqueda();
                loadingDialog.dismiss();
            }

            @Override
            public void fallo() {
                Toast.makeText(getContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });

    }

    private void realizaPeticionEnemigos() {
        servicioEnemigos.getAllEnemigos(new CallbackLista<Enemigo>() {
            @Override
            public void exito(List<Enemigo> listaResultado) {
                listaEnemigos = listaResultado;
                List<OrdenablePorNombre> lista = new ArrayList<>(listaResultado);
                adaptadorMix.addElementos(lista);
                cambiaTituloBusqueda();
                loadingDialog.dismiss();
            }

            @Override
            public void fallo() {
                Toast.makeText(getContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });

    }

    private void realizaPeticionHechizos() {
        servicioEquipamiento.getAllHechizos(new CallbackLista<Hechizo>() {
            @Override
            public void exito(List<Hechizo> listaResultado) {
                listaHechizos = listaResultado;
                List<OrdenablePorNombre> lista = new ArrayList<>(listaResultado);
                adaptadorMix.addElementos(lista);
                cambiaTituloBusqueda();
                loadingDialog.dismiss();
            }

            @Override
            public void fallo() {
                Toast.makeText(getContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });
    }

    private void realizaPeticionClases() {
        servicioClases.getAllClases(new CallbackLista<Clase>() {
            @Override
            public void exito(List<Clase> listaResultado) {
                listaClases = listaResultado;
                List<OrdenablePorNombre> lista = new ArrayList<>(listaResultado);
                adaptadorMix.addElementos(lista);
                cambiaTituloBusqueda();
                loadingDialog.dismiss();
            }

            @Override
            public void fallo() {
                Toast.makeText(getContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class OurTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            adaptadorMix.filtra(charSequence.toString());
            cambiaTituloBusqueda();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}