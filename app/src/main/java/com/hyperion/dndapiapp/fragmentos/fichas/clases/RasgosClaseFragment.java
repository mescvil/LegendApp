package com.hyperion.dndapiapp.fragmentos.fichas.clases;

import static com.hyperion.dndapiapp.utilidades.Constantes.RASGOS_CLASE_BUNDLE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorGenerico;
import com.hyperion.dndapiapp.databinding.FragmentRasgosClaseBinding;
import com.hyperion.dndapiapp.entidades.glosario.clases.RasgoClase;

import java.util.Arrays;


public class RasgosClaseFragment extends Fragment {

    private RasgoClase[] rasgoClase;
    private FragmentRasgosClaseBinding binding;

    public RasgosClaseFragment() {
    }

    public static RasgosClaseFragment newInstance(RasgoClase[] rasgoClase) {
        RasgosClaseFragment fragment = new RasgosClaseFragment();
        Bundle args = new Bundle();
        args.putParcelableArray(RASGOS_CLASE_BUNDLE, rasgoClase);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rasgoClase = (RasgoClase[]) getArguments().getParcelableArray(RASGOS_CLASE_BUNDLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRasgosClaseBinding.inflate(inflater, container, false);
        iniciaFragmento();
        return binding.getRoot();
    }

    private void iniciaFragmento() {
        RecyclerView recyclerView = binding.listaRasgosClase;
        AdaptadorGenerico adaptadorGenerico = new AdaptadorGenerico(Arrays.asList(rasgoClase));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorGenerico);
    }
}