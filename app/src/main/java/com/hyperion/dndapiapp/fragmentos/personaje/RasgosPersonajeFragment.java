package com.hyperion.dndapiapp.fragmentos.personaje;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorGenerico;
import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorGenericoFichaPersonaje;
import com.hyperion.dndapiapp.databinding.FragmentRasgosClaseBinding;
import com.hyperion.dndapiapp.databinding.FragmentRasgosPersonajeBinding;
import com.hyperion.dndapiapp.utilidades.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RasgosPersonajeFragment extends Fragment {

    private final static String ARG_CLASE = "clase";
    private final static String ARG_RAZA = "raza";

    private FragmentRasgosPersonajeBinding binding;
    private String rasgosClase;
    private String rasgosRaza;

    public RasgosPersonajeFragment() {
    }

    public static RasgosPersonajeFragment newInstance(String rasgosClase, String rasgosRaza) {
        RasgosPersonajeFragment fragment = new RasgosPersonajeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CLASE, rasgosClase);
        args.putString(ARG_RAZA, rasgosRaza);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rasgosClase = getArguments().getString(ARG_CLASE);
            rasgosRaza = getArguments().getString(ARG_RAZA);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRasgosPersonajeBinding.inflate(inflater, container, false);
        iniciaFragmento();
        return binding.getRoot();
    }

    private void iniciaFragmento() {
        List<String> rasgos = Utils.StringToLista(rasgosClase);
        List<String> rasgos_ = Utils.StringToLista(rasgosRaza);
        List<String> mixRasgos = new ArrayList<>();

        mixRasgos.addAll(rasgos);
        mixRasgos.addAll(rasgos_);
        mixRasgos.remove("");

        RecyclerView recyclerView = binding.listaRagosPersonaje;
        AdaptadorGenericoFichaPersonaje adaptadorGenerico = new AdaptadorGenericoFichaPersonaje(mixRasgos, getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorGenerico);
    }
}