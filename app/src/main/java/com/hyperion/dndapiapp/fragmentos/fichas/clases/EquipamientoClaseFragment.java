package com.hyperion.dndapiapp.fragmentos.fichas.clases;

import static com.hyperion.dndapiapp.utilidades.Constantes.ARMADURA_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.ARMA_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.HECHIZOS_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.RASGOS_CLASE_BUNDLE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.GenericoRecyclerView;
import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorGenerico;
import com.hyperion.dndapiapp.databinding.FragmentEquipamientoClaseBinding;
import com.hyperion.dndapiapp.databinding.FragmentRasgosClaseBinding;
import com.hyperion.dndapiapp.entidades.equipamiento.Arma;
import com.hyperion.dndapiapp.entidades.equipamiento.Armadura;
import com.hyperion.dndapiapp.entidades.equipamiento.Equipamiento;
import com.hyperion.dndapiapp.entidades.equipamiento.Hechizo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EquipamientoClaseFragment extends Fragment {

    private Armadura[] armaduras;
    private Arma[] armas;
    private Hechizo[] hechizos;
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
        List<GenericoRecyclerView> lista = new ArrayList<>();
        lista.addAll(Arrays.asList(hechizos));
        lista.addAll(Arrays.asList(armas));
        lista.addAll(Arrays.asList(armaduras));

        RecyclerView recyclerView = binding.listaEquipamientoClase;
        AdaptadorGenerico adaptadorGenerico = new AdaptadorGenerico(lista);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorGenerico);
    }
}