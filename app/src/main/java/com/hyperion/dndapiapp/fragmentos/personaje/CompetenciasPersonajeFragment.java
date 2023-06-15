package com.hyperion.dndapiapp.fragmentos.personaje;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorGenericoFichaPersonaje;
import com.hyperion.dndapiapp.databinding.FragmentCompetenciasPersonajeBinding;
import com.hyperion.dndapiapp.entidades.fichas.HistoriaPersonaje;
import com.hyperion.dndapiapp.utilidades.Utils;

import java.util.List;

public class CompetenciasPersonajeFragment extends Fragment {

    private static final String ARG_HISTORIA = "historia";

    private FragmentCompetenciasPersonajeBinding binding;
    private HistoriaPersonaje historia;

    public CompetenciasPersonajeFragment() {
    }

    public static CompetenciasPersonajeFragment newInstance(HistoriaPersonaje historia) {
        CompetenciasPersonajeFragment fragment = new CompetenciasPersonajeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_HISTORIA, historia);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            historia = getArguments().getParcelable(ARG_HISTORIA);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCompetenciasPersonajeBinding.inflate(inflater, container, false);
        iniciaFragmento();
        return binding.getRoot();
    }

    private void iniciaFragmento() {
        String idiomas = historia.getIdiomas().replace(";", ", ");
        binding.listaIdiomasPersonaje.setText(idiomas);

        List<String> competencias = Utils.StringToLista(historia.getCompetencias());

        RecyclerView recyclerView = binding.listaCompetenciasPersonaje;
        AdaptadorGenericoFichaPersonaje adaptadorGenerico = new AdaptadorGenericoFichaPersonaje(competencias);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorGenerico);

    }
}