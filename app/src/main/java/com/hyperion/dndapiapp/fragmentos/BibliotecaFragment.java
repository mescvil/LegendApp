package com.hyperion.dndapiapp.fragmentos;

import static com.hyperion.dndapiapp.utilidades.Constantes.ACTIVIDAD_FAVORITO;
import static com.hyperion.dndapiapp.utilidades.Constantes.ACTIVIDAD_FAVORITO_CLASE;
import static com.hyperion.dndapiapp.utilidades.Constantes.ARMADURA_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.ARMA_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.CLASE_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.ENEMIGO_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.FAVORITO_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.HECHIZOS_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO_RESULT;
import static com.hyperion.dndapiapp.utilidades.Constantes.LISTA_FAVORITOS_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.LISTA_FAVORITOS_CLASE_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.POSICION_CLASES;
import static com.hyperion.dndapiapp.utilidades.Constantes.POSICION_COMPETENCIAS;
import static com.hyperion.dndapiapp.utilidades.Constantes.POSICION_ENEMIGOS;
import static com.hyperion.dndapiapp.utilidades.Constantes.POSICION_EQUIPAMIENTO;
import static com.hyperion.dndapiapp.utilidades.Constantes.POSICION_HECHIZOS;
import static com.hyperion.dndapiapp.utilidades.Constantes.POSICION_RAZAS;
import static com.hyperion.dndapiapp.utilidades.Constantes.POSICION_TRASFONDOS;
import static com.hyperion.dndapiapp.utilidades.Constantes.RAZA_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.TRASFONDO_COMPETENCIAS_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.filtros;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.actividades.fichas.FichaArmaActivity;
import com.hyperion.dndapiapp.actividades.fichas.FichaArmaduraActivity;
import com.hyperion.dndapiapp.actividades.fichas.FichaClaseAcitivity;
import com.hyperion.dndapiapp.actividades.fichas.FichaEnemigoActivity;
import com.hyperion.dndapiapp.actividades.fichas.FichaHechizoActivity;
import com.hyperion.dndapiapp.actividades.fichas.FichaRazaActivity;
import com.hyperion.dndapiapp.actividades.fichas.FichaTrasfondoActivity;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorMix;
import com.hyperion.dndapiapp.controladores.Controlador;
import com.hyperion.dndapiapp.databinding.FragmentBibliotecaBinding;
import com.hyperion.dndapiapp.entidades.clases.Clase;
import com.hyperion.dndapiapp.entidades.competencias.Competencia;
import com.hyperion.dndapiapp.entidades.enemigos.Enemigo;
import com.hyperion.dndapiapp.entidades.equipamiento.Arma;
import com.hyperion.dndapiapp.entidades.equipamiento.Armadura;
import com.hyperion.dndapiapp.entidades.equipamiento.Hechizo;
import com.hyperion.dndapiapp.entidades.razas.Raza;
import com.hyperion.dndapiapp.entidades.trasfondos.Trasfondo;
import com.hyperion.dndapiapp.sqlite.Favorito;
import com.hyperion.dndapiapp.sqlite.FavoritoClase;
import com.hyperion.dndapiapp.utilidades.GetNombreInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings({"deprecation", "DefaultLocale"})
public class BibliotecaFragment extends Fragment implements RecyclerViewClick {

    /* Utiles */
    private final boolean[] filtrosSeleccionados;
    private SharedPreferences.Editor editor;
    private FragmentBibliotecaBinding binding;
    private final Controlador controlador;

    /* Componentes */
    private RecyclerView recyclerView;
    private AdaptadorMix adaptadorMix;
    private TextView titulo;

    public BibliotecaFragment() {
        controlador = Controlador.getInstance();
        filtrosSeleccionados = new boolean[filtros.length];
    }

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
        iniciaFragmento();
        return binding.getRoot();

    }

    private void listenerFiltro() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.DialogoFiltrosTheme);

        builder.setMultiChoiceItems(
                filtros,
                filtrosSeleccionados,
                (dialog, which, isChecked) -> filtrosSeleccionados[which] = isChecked);

        builder.setPositiveButton("Hecho", (dialog, which) -> actualizaListaFiltros());
        builder.setNegativeButton("Marcar todo", (dialog, which) -> {
            Arrays.fill(filtrosSeleccionados, true);
            actualizaListaFiltros();
        });

        builder.setNeutralButton("Desmarcar todo", (dialog, which) -> {
            Arrays.fill(filtrosSeleccionados, false);
            actualizaListaFiltros();
        });

        builder.create();
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void actualizaListaFiltros() {
        recyclerView.setBackgroundResource(R.color.fondo);
        cambiaTituloBusqueda();

        for (int i = 0; i < filtrosSeleccionados.length; i++) {
            editor.putBoolean(filtros[i], filtrosSeleccionados[i]);
        }

        editor.commit();
        actualizaLista();
        recyclerView.scrollToPosition(0);
    }

    private void iniciaFragmento() {
        this.titulo = binding.tituloBusqueda;

        /* RecyclerView */
        adaptadorMix = new AdaptadorMix(getContext(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView = binding.listaBiblioteca;
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorMix);

        /* Bindings */
        binding.botonFiltro.setOnClickListener(view -> listenerFiltro());
        binding.campoBusqueda.addTextChangedListener(new OurTextWatcher());
        binding.botonLimpiar.setOnClickListener(v -> binding.campoBusqueda.setText(""));

        /* Peticion de listas */
        actualizaLista();
    }

    private void cambiaTituloBusqueda() {
        int nFiltros = 0;
        for (boolean filtrosSeleccionado : filtrosSeleccionados) {
            if (filtrosSeleccionado) {
                nFiltros++;
            }
        }
        titulo.setText(String.format("Filtros apliacados: %d\nNº de elementos : %d", nFiltros, adaptadorMix.getItemCount()));
    }

    private void actualizaLista() {
        List<GetNombreInterface> listaFiltrada = new ArrayList<>();

        adaptadorMix.clearElementos();
        binding.campoBusqueda.setText("");

        if (filtrosSeleccionados[POSICION_ENEMIGOS])
            listaFiltrada.addAll(controlador.getListaEnemigos());

        if (filtrosSeleccionados[POSICION_CLASES])
            listaFiltrada.addAll(controlador.getListaClases());

        if (filtrosSeleccionados[POSICION_HECHIZOS])
            listaFiltrada.addAll(controlador.getListaHechizos());

        if (filtrosSeleccionados[POSICION_RAZAS])
            listaFiltrada.addAll(controlador.getListaRazas());

        if (filtrosSeleccionados[POSICION_COMPETENCIAS])
            listaFiltrada.addAll(controlador.getListaCompentencias());

        if (filtrosSeleccionados[POSICION_EQUIPAMIENTO])
            listaFiltrada.addAll(controlador.getListaEquipamiento());

        if (filtrosSeleccionados[POSICION_TRASFONDOS])
            listaFiltrada.addAll(controlador.getListaTrasfondos());

        if (listaFiltrada.isEmpty()) {
            recyclerView.setBackgroundResource(R.drawable.imagen_biblioteca);
        } else {
            adaptadorMix.addElementos(listaFiltrada);
        }

        cambiaTituloBusqueda();
    }

    @Override
    public void onCosaCliked(int posicion) {
        GetNombreInterface objeto = adaptadorMix.getObjeto(posicion);
        boolean isFavorito = controlador.isFavorito(objeto.getNombre());

        if (objeto instanceof Enemigo) {
            Intent intent = new Intent(getContext(), FichaEnemigoActivity.class);
            intent.putExtra(ENEMIGO_BUNDLE, (Enemigo) objeto);
            intent.putExtra(IS_FAVORITO, isFavorito);
            startActivityForResult(intent, ACTIVIDAD_FAVORITO);

        } else if (objeto instanceof Competencia) {
            Toast.makeText(getContext(), "Es autoexplicativo =)", Toast.LENGTH_SHORT).show();

        } else if (objeto instanceof Trasfondo) {
            Intent intent = new Intent(getContext(), FichaTrasfondoActivity.class);
            intent.putExtra(TRASFONDO_COMPETENCIAS_BUNDLE, (Trasfondo) objeto);
            intent.putExtra(IS_FAVORITO, isFavorito);
            startActivityForResult(intent, ACTIVIDAD_FAVORITO);

        } else if (objeto instanceof Hechizo) { // Compìar para el resto fijarse en los hechizos
            Intent intent = new Intent(getContext(), FichaHechizoActivity.class);
            intent.putExtra(HECHIZOS_BUNDLE, (Hechizo) objeto);
            intent.putExtra(IS_FAVORITO, isFavorito);
            startActivityForResult(intent, ACTIVIDAD_FAVORITO);

        } else if (objeto instanceof Arma) {
            Intent intent = new Intent(getContext(), FichaArmaActivity.class);
            intent.putExtra(ARMA_BUNDLE, (Arma) objeto);
            intent.putExtra(IS_FAVORITO, isFavorito);
            startActivityForResult(intent, ACTIVIDAD_FAVORITO);

        } else if (objeto instanceof Armadura) {
            Intent intent = new Intent(getContext(), FichaArmaduraActivity.class);
            intent.putExtra(ARMADURA_BUNDLE, (Armadura) objeto);
            intent.putExtra(IS_FAVORITO, isFavorito);
            startActivityForResult(intent, ACTIVIDAD_FAVORITO);

        } else if (objeto instanceof Raza) {
            Intent intent = new Intent(getContext(), FichaRazaActivity.class);
            intent.putExtra(RAZA_BUNDLE, (Raza) objeto);
            intent.putExtra(IS_FAVORITO, isFavorito);
            startActivityForResult(intent, ACTIVIDAD_FAVORITO);

        } else if (objeto instanceof Clase) {
            Intent intent = new Intent(getContext(), FichaClaseAcitivity.class);
            intent.putExtra(CLASE_BUNDLE, (Clase) objeto);
            intent.putExtra(IS_FAVORITO, isFavorito);
            intent.putExtra(LISTA_FAVORITOS_BUNDLE,
                    controlador.getFavoritos().toArray(new Favorito[0]));
            startActivityForResult(intent, ACTIVIDAD_FAVORITO_CLASE);
        }
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

    @Override
    @SuppressWarnings("all")
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVIDAD_FAVORITO) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    boolean isFavorito = data.getBooleanExtra(IS_FAVORITO_RESULT, false);
                    Favorito favorito = data.getParcelableExtra(FAVORITO_BUNDLE);

                    if (isFavorito)
                        controlador.addFavorito(favorito);
                    else
                        controlador.removeFavorito(favorito);
                }
            }

        } else if (requestCode == ACTIVIDAD_FAVORITO_CLASE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    boolean isFavorito = data.getBooleanExtra(IS_FAVORITO_RESULT, false);
                    Favorito favorito = data.getParcelableExtra(FAVORITO_BUNDLE);
                    Parcelable[] favoritosClase = data.getParcelableArrayExtra(LISTA_FAVORITOS_CLASE_BUNDLE);

                    if (isFavorito)
                        controlador.addFavorito(favorito);
                    else
                        controlador.removeFavorito(favorito);

                    if (favoritosClase.length > 0) {
                        List<FavoritoClase> favoritoClaseList = new ArrayList<>();
                        for (Parcelable parcelable : favoritosClase) {
                            favoritoClaseList.add((FavoritoClase) parcelable);
                        }
                        controlador.gestionaFavoritosClase(favoritoClaseList);
                    }
                }
            }
        }
    }
}