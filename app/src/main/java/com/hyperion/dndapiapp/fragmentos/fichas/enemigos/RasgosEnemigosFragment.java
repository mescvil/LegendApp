package com.hyperion.dndapiapp.fragmentos.fichas.enemigos;

import static com.hyperion.dndapiapp.utilidades.Constantes.RASGOS_ENEMIGOS_BUNDLE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorGenerico;
import com.hyperion.dndapiapp.databinding.FragmentRasgosEnemigosBinding;
import com.hyperion.dndapiapp.entidades.glosario.enemigos.RasgoEnemigo;

import java.util.Arrays;

public class RasgosEnemigosFragment extends Fragment {

    private FragmentRasgosEnemigosBinding binding;
    private RasgoEnemigo[] listaRasgos;

    public RasgosEnemigosFragment() {
    }

    public static RasgosEnemigosFragment newInstance(RasgoEnemigo[] rasgosEnemigos) {
        RasgosEnemigosFragment fragment = new RasgosEnemigosFragment();
        Bundle args = new Bundle();
        args.putParcelableArray(RASGOS_ENEMIGOS_BUNDLE, rasgosEnemigos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            listaRasgos = (RasgoEnemigo[]) bundle.getParcelableArray(RASGOS_ENEMIGOS_BUNDLE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRasgosEnemigosBinding.inflate(inflater, container, false);
        iniciaFragmento();
        return binding.getRoot();
    }

    private void iniciaFragmento() {
        RecyclerView recyclerView = binding.listaRasgosEnemigos;
        AdaptadorGenerico adaptadorGenerico = new AdaptadorGenerico(Arrays.asList(listaRasgos));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorGenerico);
    }
}