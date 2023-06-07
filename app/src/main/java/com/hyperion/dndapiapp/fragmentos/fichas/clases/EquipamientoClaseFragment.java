package com.hyperion.dndapiapp.fragmentos.fichas.clases;

import static com.hyperion.dndapiapp.utilidades.Constantes.ARMADURA_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.ARMA_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.HECHIZOS_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.RASGOS_CLASE_BUNDLE;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.actividades.fichas.FichaArmaActivity;
import com.hyperion.dndapiapp.actividades.fichas.FichaArmaduraActivity;
import com.hyperion.dndapiapp.actividades.fichas.FichaHechizoActivity;
import com.hyperion.dndapiapp.adaptadores.recyclerView.GenericoRecyclerView;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorEquipamiento;
import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorGenerico;
import com.hyperion.dndapiapp.databinding.FragmentEquipamientoClaseBinding;
import com.hyperion.dndapiapp.databinding.FragmentRasgosClaseBinding;
import com.hyperion.dndapiapp.entidades.equipamiento.Arma;
import com.hyperion.dndapiapp.entidades.equipamiento.Armadura;
import com.hyperion.dndapiapp.entidades.equipamiento.Equipamiento;
import com.hyperion.dndapiapp.entidades.equipamiento.Hechizo;
import com.hyperion.dndapiapp.utilidades.OrdenablePorNombre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EquipamientoClaseFragment extends Fragment implements RecyclerViewClick {

    private Armadura[] armaduras;
    private Arma[] armas;
    private Hechizo[] hechizos;
    private List<OrdenablePorNombre> listaTotal;
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
        AdaptadorEquipamiento adaptadorGenerico = new AdaptadorEquipamiento(listaTotal, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorGenerico);
    }

    @Override
    public void onCosaCliked(int posicion) {
        OrdenablePorNombre objeto = listaTotal.get(posicion);

        if (objeto instanceof Hechizo) {
            Intent intent = new Intent(getContext(), FichaHechizoActivity.class);
            intent.putExtra(HECHIZOS_BUNDLE, (Hechizo) objeto);
            startActivity(intent);

        } else if (objeto instanceof Arma) {
            Intent intent = new Intent(getContext(), FichaArmaActivity.class);
            intent.putExtra(ARMA_BUNDLE, (Arma) objeto);
            startActivity(intent);

        } else if (objeto instanceof Armadura) {
            Intent intent = new Intent(getContext(), FichaArmaduraActivity.class);
            intent.putExtra(ARMADURA_BUNDLE, (Armadura) objeto);
            startActivity(intent);
        }
    }
}