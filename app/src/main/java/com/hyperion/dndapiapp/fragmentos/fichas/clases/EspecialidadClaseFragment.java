package com.hyperion.dndapiapp.fragmentos.fichas.clases;

import static com.hyperion.dndapiapp.utilidades.Constantes.ESPECIALIDADES_BUNDLE;

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

import com.hyperion.dndapiapp.actividades.fichas.FichaEspecialidadActivity;
import com.hyperion.dndapiapp.adaptadores.recyclerView.RecyclerViewClick;
import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorEquipoEspecialidades;
import com.hyperion.dndapiapp.adaptadores.recyclerView.adaptadores.AdaptadorGenerico;
import com.hyperion.dndapiapp.databinding.FragmentEspecialidadClaseBinding;
import com.hyperion.dndapiapp.entidades.clases.Especialidad;

import java.util.Arrays;


public class EspecialidadClaseFragment extends Fragment implements RecyclerViewClick {

    private Especialidad[] especialidades;
    private FragmentEspecialidadClaseBinding binding;

    public EspecialidadClaseFragment() {
    }

    public static EspecialidadClaseFragment newInstance(Especialidad[] especialides) {
        EspecialidadClaseFragment fragment = new EspecialidadClaseFragment();
        Bundle args = new Bundle();
        args.putParcelableArray(ESPECIALIDADES_BUNDLE, especialides);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            especialidades = (Especialidad[]) getArguments().getParcelableArray(ESPECIALIDADES_BUNDLE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEspecialidadClaseBinding.inflate(inflater, container, false);
        iniciaFragmento();
        return binding.getRoot();
    }

    private void iniciaFragmento() {
        RecyclerView recyclerView = binding.listaEspecialidadesClase;
        AdaptadorEquipoEspecialidades adaptador
                = new AdaptadorEquipoEspecialidades(Arrays.asList(especialidades), this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptador);
    }

    @Override
    public void onCosaCliked(int posicion) {
        Intent intent = new Intent(getContext(), FichaEspecialidadActivity.class);
        intent.putExtra(ESPECIALIDADES_BUNDLE, especialidades[posicion]);
        startActivity(intent);
    }
}