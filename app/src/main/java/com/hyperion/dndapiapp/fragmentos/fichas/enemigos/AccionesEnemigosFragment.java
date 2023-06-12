package com.hyperion.dndapiapp.fragmentos.fichas.enemigos;

import static com.hyperion.dndapiapp.utilidades.Constantes.ACCIONES_ENEMIGOS_BUNDLE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorGenerico;
import com.hyperion.dndapiapp.databinding.FragmentAccionesEnemigosBinding;
import com.hyperion.dndapiapp.entidades.glosario.enemigos.Accion;

import java.util.Arrays;

public class AccionesEnemigosFragment extends Fragment {

    private FragmentAccionesEnemigosBinding binding;
    private Accion[] acciones;

    public AccionesEnemigosFragment() {
    }

    public static AccionesEnemigosFragment newInstance(Accion[] acciones) {
        AccionesEnemigosFragment fragment = new AccionesEnemigosFragment();
        Bundle args = new Bundle();
        args.putParcelableArray(ACCIONES_ENEMIGOS_BUNDLE, acciones);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            acciones = (Accion[]) bundle.getParcelableArray(ACCIONES_ENEMIGOS_BUNDLE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAccionesEnemigosBinding.inflate(inflater, container, false);
        iniciaFragmento();
        return binding.getRoot();
    }

    private void iniciaFragmento() {
        RecyclerView recyclerView = binding.listaAccionesEnemigos;
        AdaptadorGenerico adaptadorGenerico = new AdaptadorGenerico(Arrays.asList(acciones));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorGenerico);
    }
}