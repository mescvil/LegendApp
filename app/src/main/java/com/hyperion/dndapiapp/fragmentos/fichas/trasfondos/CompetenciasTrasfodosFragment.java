package com.hyperion.dndapiapp.fragmentos.fichas.trasfondos;

import static com.hyperion.dndapiapp.utilidades.Constantes.TRASFONDO_COMPETENCIAS_BUNDLE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorGenerico;
import com.hyperion.dndapiapp.databinding.FragmentCompetenciasTrasfodosBinding;
import com.hyperion.dndapiapp.entidades.glosario.competencias.Competencia;

import java.util.Arrays;

public class CompetenciasTrasfodosFragment extends Fragment {

    private Competencia[] competencias;
    private FragmentCompetenciasTrasfodosBinding binding;

    public CompetenciasTrasfodosFragment() {
    }

    public static CompetenciasTrasfodosFragment newInstance(Competencia[] competencias) {
        CompetenciasTrasfodosFragment fragment = new CompetenciasTrasfodosFragment();
        Bundle args = new Bundle();
        args.putParcelableArray(TRASFONDO_COMPETENCIAS_BUNDLE, competencias);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            competencias = (Competencia[]) bundle.getParcelableArray(TRASFONDO_COMPETENCIAS_BUNDLE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCompetenciasTrasfodosBinding.inflate(inflater, container, false);
        iniciaFragmento();
        return binding.getRoot();
    }

    private void iniciaFragmento() {
        RecyclerView recyclerView = binding.listaCompetenciasTrasfondo;
        AdaptadorGenerico adaptadorGenerico = new AdaptadorGenerico(Arrays.asList(competencias));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorGenerico);
    }
}