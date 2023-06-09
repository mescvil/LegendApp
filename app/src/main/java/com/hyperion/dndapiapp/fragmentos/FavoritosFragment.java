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
import static com.hyperion.dndapiapp.utilidades.Constantes.RAZA_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.TRASFONDO_COMPETENCIAS_BUNDLE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorFavoritos;
import com.hyperion.dndapiapp.controladores.Controlador;
import com.hyperion.dndapiapp.databinding.FragmentFavoritosBinding;
import com.hyperion.dndapiapp.dialogos.LoadingDialog;
import com.hyperion.dndapiapp.entidades.glosario.clases.Clase;
import com.hyperion.dndapiapp.entidades.glosario.enemigos.Enemigo;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Arma;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Armadura;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Hechizo;
import com.hyperion.dndapiapp.entidades.glosario.razas.Raza;
import com.hyperion.dndapiapp.entidades.glosario.trasfondos.Trasfondo;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackCustom;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioClases;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioEnemigos;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioEquipamiento;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioRazas;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioTrasfondos;
import com.hyperion.dndapiapp.sqlite.Favorito;
import com.hyperion.dndapiapp.sqlite.FavoritoClase;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"deprecation"})
public class FavoritosFragment extends Fragment implements RecyclerViewClick {

    private FragmentFavoritosBinding binding;

    /* Componentes */
    private AdaptadorFavoritos adaptador;
    private RecyclerView recyclerView;

    /* Utils */
    private final Controlador controlador;
    private List<Favorito> favoritos;

    public FavoritosFragment() {
        controlador = Controlador.getInstance();
    }

    public static FavoritosFragment newInstance() {
        return new FavoritosFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favoritos = new ArrayList<>(controlador.getFavoritos());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFavoritosBinding.inflate(inflater, container, false);
        iniciarFragmento();
        return binding.getRoot();
    }

    private void iniciarFragmento() {
        recyclerView = binding.listaFavoritos;
        adaptador = new AdaptadorFavoritos(getContext(), favoritos, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptador);

        binding.campoBusquedaFav.addTextChangedListener(new OurTextWatcher());
        binding.botonLimpiarBusqueda.setOnClickListener(v -> binding.campoBusquedaFav.setText(""));
        binding.botonLimpiaFav.setOnClickListener(v -> creaDialogoBorrar());
        binding.campoBusquedaFav.setText("");

        compruebaFavoritosVacio();
    }

    private void compruebaFavoritosVacio() {
        ConstraintLayout layoutLimpia = binding.layoutBuscadorFav;
        ConstraintLayout layoutGeneral = binding.layoutFavoritos;
        ConstraintLayout layoutVacio = binding.layoutTextoResultado;

        if (favoritos == null || favoritos.isEmpty()) {
            layoutGeneral.setBackgroundResource(R.drawable.imagen_fondo_fav);
            layoutLimpia.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
            layoutVacio.setVisibility(View.VISIBLE);

        } else {
            layoutVacio.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            layoutLimpia.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCosaCliked(int posicion) {
        LoadingDialog dialog = new LoadingDialog(getContext());
        Favorito favorito = adaptador.getFavorito(posicion);

        dialog.show("Obteniendo favorito");

        switch (favorito.getTipo()) {
            case "Hechizo":
                ServicioEquipamiento.getInstance().getHechizo(new CallbackCustom<Hechizo>() {
                    @Override
                    public void exito(Hechizo resultado) {
                        dialog.dismiss();

                        Intent intent = new Intent(getContext(), FichaHechizoActivity.class);
                        intent.putExtra(HECHIZOS_BUNDLE, resultado);
                        intent.putExtra(IS_FAVORITO, true);
                        startActivityForResult(intent, ACTIVIDAD_FAVORITO);
                    }

                    @Override
                    public void fallo(String mensaje) {
                        dialog.dismiss();
                    }

                }, favorito.getNombre());
                break;

            case "Arma":
                ServicioEquipamiento.getInstance().getArma(new CallbackCustom<Arma>() {
                    @Override
                    public void exito(Arma resultado) {
                        dialog.dismiss();

                        Intent intent = new Intent(getContext(), FichaArmaActivity.class);
                        intent.putExtra(ARMA_BUNDLE, resultado);
                        intent.putExtra(IS_FAVORITO, true);
                        startActivityForResult(intent, ACTIVIDAD_FAVORITO);
                    }

                    @Override
                    public void fallo(String mensaje) {
                        dialog.dismiss();
                    }

                }, favorito.getNombre());
                break;

            case "Armadura":
                ServicioEquipamiento.getInstance().getArmadura(new CallbackCustom<Armadura>() {
                    @Override
                    public void exito(Armadura resultado) {
                        dialog.dismiss();

                        Intent intent = new Intent(getContext(), FichaArmaduraActivity.class);
                        intent.putExtra(ARMADURA_BUNDLE, resultado);
                        intent.putExtra(IS_FAVORITO, true);
                        startActivityForResult(intent, ACTIVIDAD_FAVORITO);
                    }

                    @Override
                    public void fallo(String mensaje) {
                        dialog.dismiss();
                    }

                }, favorito.getNombre());
                break;

            case "Enemigo":
                ServicioEnemigos.getInstance().getEnemigo(new CallbackCustom<Enemigo>() {
                    @Override
                    public void exito(Enemigo resultado) {
                        dialog.dismiss();

                        Intent intent = new Intent(getContext(), FichaEnemigoActivity.class);
                        intent.putExtra(ENEMIGO_BUNDLE, resultado);
                        intent.putExtra(IS_FAVORITO, true);
                        startActivityForResult(intent, ACTIVIDAD_FAVORITO);
                    }

                    @Override
                    public void fallo(String mensaje) {
                        dialog.dismiss();
                    }

                }, favorito.getNombre());
                break;

            case "Raza":
                ServicioRazas.getInstance().getRaza(new CallbackCustom<Raza>() {
                    @Override
                    public void exito(Raza resultado) {
                        dialog.dismiss();

                        Intent intent = new Intent(getContext(), FichaRazaActivity.class);
                        intent.putExtra(RAZA_BUNDLE, resultado);
                        intent.putExtra(IS_FAVORITO, true);
                        startActivityForResult(intent, ACTIVIDAD_FAVORITO);
                    }

                    @Override
                    public void fallo(String mensaje) {
                        dialog.dismiss();
                    }

                }, favorito.getNombre());
                break;

            case "Trasfondo":
                ServicioTrasfondos.getInstance().getTrasfondo(new CallbackCustom<Trasfondo>() {
                    @Override
                    public void exito(Trasfondo resultado) {
                        dialog.dismiss();

                        Intent intent = new Intent(getContext(), FichaTrasfondoActivity.class);
                        intent.putExtra(TRASFONDO_COMPETENCIAS_BUNDLE, resultado);
                        intent.putExtra(IS_FAVORITO, true);
                        startActivityForResult(intent, ACTIVIDAD_FAVORITO);
                    }

                    @Override
                    public void fallo(String mensaje) {
                        dialog.dismiss();
                    }

                }, favorito.getNombre());
                break;

            case "Clase":
                ServicioClases.getInstance().getClase(new CallbackCustom<Clase>() {
                    @Override
                    public void exito(Clase resultado) {
                        dialog.dismiss();

                        Intent intent = new Intent(getContext(), FichaClaseAcitivity.class);
                        intent.putExtra(CLASE_BUNDLE, resultado);
                        intent.putExtra(IS_FAVORITO, true);
                        intent.putExtra(LISTA_FAVORITOS_BUNDLE,
                                controlador.getFavoritos().toArray(new Favorito[0]));
                        startActivityForResult(intent, ACTIVIDAD_FAVORITO_CLASE);
                    }

                    @Override
                    public void fallo(String mensaje) {
                        dialog.dismiss();
                    }

                }, favorito.getNombre());
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVIDAD_FAVORITO) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    boolean isFavorito = data.getBooleanExtra(IS_FAVORITO_RESULT, false);
                    Favorito favorito = data.getParcelableExtra(FAVORITO_BUNDLE);

                    if (isFavorito)
                        controlador.addFavorito(favorito);
                    else {
                        controlador.removeFavorito(favorito);
                        adaptador.removeItem(favorito);
                    }
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
                    else {
                        controlador.removeFavorito(favorito);
                        adaptador.removeItem(favorito);
                    }

                    if (favoritosClase.length > 0) {
                        List<FavoritoClase> favoritoClaseList = new ArrayList<>();
                        for (Parcelable parcelable : favoritosClase) {
                            FavoritoClase favoritoClase = (FavoritoClase) parcelable;
                            Favorito f = favoritoClase.getFavorito();

                            favoritoClaseList.add(favoritoClase);

                            if (favoritoClase.isFavorito() && !adaptador.containsItem(f)) {
                                adaptador.addItem(f);
                            } else if (!favoritoClase.isFavorito() && adaptador.containsItem(f)) {
                                adaptador.removeItem(f);
                            }
                        }

                        controlador.gestionaFavoritosClase(favoritoClaseList);
                    }
                }
            }
        }
        compruebaFavoritosVacio();
    }

    private void limpiaAllFavoritos() {
        binding.campoBusquedaFav.setText("");
        adaptador.clearAll();
        controlador.removeAllFavoritos();
        compruebaFavoritosVacio();
    }

    private void creaDialogoBorrar() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext(), R.style.DialogoFiltrosTheme);
        alertDialogBuilder.setMessage("¿Eliminar todos los favoritos?");

        alertDialogBuilder.setPositiveButton("Si", (arg0, arg1) -> limpiaAllFavoritos());
        alertDialogBuilder.setNegativeButton("No", (dialog, which) -> {
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private class OurTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            adaptador.filtra(charSequence.toString());
            cambiaEstadoBotonLimpiar();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }

    private void cambiaEstadoBotonLimpiar() {
        if (binding.campoBusquedaFav.getText().toString().isEmpty()) {
            binding.botonLimpiarBusqueda.setVisibility(View.INVISIBLE);
        } else {
            binding.botonLimpiarBusqueda.setVisibility(View.VISIBLE);
        }
    }
}