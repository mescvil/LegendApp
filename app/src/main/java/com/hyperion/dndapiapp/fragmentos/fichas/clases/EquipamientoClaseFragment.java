package com.hyperion.dndapiapp.fragmentos.fichas.clases;

import static com.hyperion.dndapiapp.utilidades.Constantes.ACTIVIDAD_FAVORITO;
import static com.hyperion.dndapiapp.utilidades.Constantes.ARMADURA_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.ARMA_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.FAVORITO_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.HECHIZOS_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO_RESULT;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.actividades.fichas.FichaArmaActivity;
import com.hyperion.dndapiapp.actividades.fichas.FichaArmaduraActivity;
import com.hyperion.dndapiapp.actividades.fichas.FichaClaseAcitivity;
import com.hyperion.dndapiapp.actividades.fichas.FichaHechizoActivity;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorEquipoEspecialidades;
import com.hyperion.dndapiapp.databinding.FragmentEquipamientoClaseBinding;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Arma;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Armadura;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Hechizo;
import com.hyperion.dndapiapp.sqlite.Favorito;
import com.hyperion.dndapiapp.utilidades.GetNombreInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EquipamientoClaseFragment extends Fragment implements RecyclerViewClick {

    private Armadura[] armaduras;
    private Arma[] armas;
    private Hechizo[] hechizos;
    private List<GetNombreInterface> listaTotal;
    private FragmentEquipamientoClaseBinding binding;

    public EquipamientoClaseFragment() {
    }

    public static EquipamientoClaseFragment newInstance(Armadura[] armaduras, Arma[] armas, Hechizo[] hechizos) {
        EquipamientoClaseFragment fragment = new EquipamientoClaseFragment();
        Bundle args = new Bundle();
        args.putParcelableArray(ARMA_BUNDLE, armas);
        args.putParcelableArray(ARMADURA_BUNDLE, armaduras);
        args.putParcelableArray(HECHIZOS_BUNDLE, hechizos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            armaduras = (Armadura[]) getArguments().getParcelableArray(ARMADURA_BUNDLE);
            armas = (Arma[]) getArguments().getParcelableArray(ARMA_BUNDLE);
            hechizos = (Hechizo[]) getArguments().getParcelableArray(HECHIZOS_BUNDLE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEquipamientoClaseBinding.inflate(inflater, container, false);
        iniciaFragmento();
        return binding.getRoot();
    }

    private void iniciaFragmento() {
        listaTotal = new ArrayList<>();
        listaTotal.addAll(Arrays.asList(hechizos));
        listaTotal.addAll(Arrays.asList(armas));
        listaTotal.addAll(Arrays.asList(armaduras));

        RecyclerView recyclerView = binding.listaEquipamientoClase;
        AdaptadorEquipoEspecialidades adaptadorGenerico = new AdaptadorEquipoEspecialidades(listaTotal, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorGenerico);
    }

    @Override
    @SuppressWarnings("all")
    public void onCosaCliked(int posicion) {
        GetNombreInterface objeto = listaTotal.get(posicion);
        boolean isFavorito = ((FichaClaseAcitivity) getActivity())
                .getFavoritosRecibidos()
                .contains(new Favorito(objeto.getNombre()));

        if (objeto instanceof Hechizo) {
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

                    ((FichaClaseAcitivity) getActivity()).gestionaFavorito(favorito, isFavorito);
                }
            }
        }
    }
}