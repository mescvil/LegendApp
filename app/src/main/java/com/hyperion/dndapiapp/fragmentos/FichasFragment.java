package com.hyperion.dndapiapp.fragmentos;

import static com.hyperion.dndapiapp.utilidades.Constantes.ACTIVIDAD_NUEVA_FICHA;
import static com.hyperion.dndapiapp.utilidades.Constantes.CORREO_USUARIO_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.FICHA_BUNDLE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.actividades.NewPersonajeActivity;
import com.hyperion.dndapiapp.adaptadores.recyclerView.EliminarFichaClick;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorFichas;
import com.hyperion.dndapiapp.databinding.FragmentFichasBinding;
import com.hyperion.dndapiapp.dialogos.LoadingDialog;
import com.hyperion.dndapiapp.entidades.fichas.PersonajeFicha;
import com.hyperion.dndapiapp.entidades.usuario.Usuario;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackCustom;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackLista;
import com.hyperion.dndapiapp.servicioRest.servicios.ServiciosFichas;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class FichasFragment extends Fragment implements RecyclerViewClick, EliminarFichaClick {

    private FragmentFichasBinding binding;
    private String correoUsuario;
    private List<PersonajeFicha> listaPersonajes;
    private boolean primeraCarga;
    private LoadingDialog loadingDialog;
    private AdaptadorFichas adaptadorFichas;

    public FichasFragment() {
        listaPersonajes = new ArrayList<>();
        primeraCarga = true;
    }

    public static FichasFragment newInstance(String correoUsuario) {
        FichasFragment fragment = new FichasFragment();
        Bundle args = new Bundle();
        args.putString(CORREO_USUARIO_BUNDLE, correoUsuario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            correoUsuario = getArguments().getString(CORREO_USUARIO_BUNDLE);
        }
        loadingDialog = new LoadingDialog(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFichasBinding.inflate(inflater, container, false);
        iniciarFragmento();
        return binding.getRoot();
    }

    private void iniciarFragmento() {
        fetchListaPersonajes();
        iniciaListenerBotones();
    }

    private void iniciaListenerBotones() {
        binding.floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), NewPersonajeActivity.class);
            startActivityForResult(intent, ACTIVIDAD_NUEVA_FICHA);
        });
    }

    private void fetchListaPersonajes() {
        if (primeraCarga) {
            loadingDialog.show("Cargado fichas");
            ServiciosFichas.getInstance().getAllFichasUsuario(new CallbackLista<PersonajeFicha>() {
                @Override
                public void exito(List<PersonajeFicha> listaResultado) {
                    listaPersonajes = listaResultado;
                    primeraCarga = false;
                    cargaListaRecyclerView();
                    loadingDialog.dismiss();
                }

                @Override
                public void fallo() {
                    loadingDialog.dismiss();
                    Toast.makeText(getContext(), "Error al obtener las fichas", Toast.LENGTH_SHORT).show();
                }
            }, new Usuario(correoUsuario));

        } else {
            cargaListaRecyclerView();
        }
    }

    private void cargaListaRecyclerView() {
        RecyclerView recyclerView = binding.listaFichasUsuario;
        adaptadorFichas = new AdaptadorFichas(listaPersonajes, this, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorFichas);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVIDAD_NUEVA_FICHA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                PersonajeFicha ficha = data.getParcelableExtra(FICHA_BUNDLE);
                posteaFicha(ficha);
            }
        }
    }

    private void posteaFicha(PersonajeFicha ficha) {
        LoadingDialog loading = new LoadingDialog(getContext());
        loading.show("Enviando ficha");
        ServiciosFichas.getInstance().posteaFicha(new CallbackCustom<Boolean>() {
            @Override
            public void exito(Boolean resultado) {
                loading.dismiss();
                primeraCarga = true;
                fetchListaPersonajes();
            }

            @Override
            public void fallo(String mensaje) {
                loading.dismiss();
            }
        }, new Usuario(correoUsuario), ficha);
    }

    @Override
    public void eliminarFichaClicked(int posicionFicha) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext(), R.style.DialogoFiltrosTheme);
        alertDialogBuilder.setMessage("¿Eliminar ficha?");

        alertDialogBuilder.setPositiveButton("Si", (arg0, arg1) -> eliminarFicha(posicionFicha));

        alertDialogBuilder.setNegativeButton("No", (dialog, which) -> {
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void eliminarFicha(int posicionFicha) {
        LoadingDialog loading = new LoadingDialog(getContext());
        loading.show("Eliminado ficha");

        PersonajeFicha ficha = adaptadorFichas.getItem(posicionFicha);
        Usuario usuario = new Usuario(correoUsuario);

        ServiciosFichas.getInstance().eliminaFicha(new CallbackCustom<Boolean>() {
            @Override
            public void exito(Boolean resultado) {
                loading.dismiss();
                primeraCarga = true;
                fetchListaPersonajes();
            }

            @Override
            public void fallo(String mensaje) {
                loading.dismiss();
            }

        }, usuario, ficha);
    }

    @Override
    public void onCosaCliked(int posicion) {
        Toast.makeText(getContext(), "Click: " + posicion, Toast.LENGTH_SHORT).show();
    }
}