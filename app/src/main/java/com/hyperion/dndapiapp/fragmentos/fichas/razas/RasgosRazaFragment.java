package com.hyperion.dndapiapp.fragmentos.fichas.razas;

import static com.hyperion.dndapiapp.utilidades.Constantes.RASGOS_RAZA_BUNDLE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorGenerico;
import com.hyperion.dndapiapp.databinding.FragmentRasgosRazaBinding;
import com.hyperion.dndapiapp.entidades.glosario.razas.RasgoRaza;

import java.util.Arrays;

public class RasgosRazaFragment extends Fragment {

    private FragmentRasgosRazaBinding binding;
    private RasgoRaza[] rasgos;

    public RasgosRazaFragment() {
    }

    public static RasgosRazaFragment newInstance(RasgoRaza[] rasgos) {
        RasgosRazaFragment fragment = new RasgosRazaFragment();
        Bundle args = new Bundle();
        args.putParcelableArray(RASGOS_RAZA_BUNDLE, rasgos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            rasgos = (RasgoRaza[]) bundle.getParcelableArray(RASGOS_RAZA_BUNDLE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRasgosRazaBinding.inflate(inflater, container, false);
        iniciaFragmento();
        return binding.getRoot();
    }

    private void iniciaFragmento() {
        RecyclerView recyclerView = binding.listaRasgosRaza;
        AdaptadorGenerico adaptadorGenerico = new AdaptadorGenerico(Arrays.asList(rasgos));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorGenerico);
    }
}