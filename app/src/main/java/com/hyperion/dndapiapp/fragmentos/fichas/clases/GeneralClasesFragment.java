package com.hyperion.dndapiapp.fragmentos.fichas.clases;

import static com.hyperion.dndapiapp.utilidades.Constantes.CLASE_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.URL_BASE_IMAGEN_CLASES;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.hyperion.dndapiapp.databinding.FragmentGeneralClasesBinding;
import com.hyperion.dndapiapp.entidades.clases.Clase;

public class GeneralClasesFragment extends Fragment {

    private Clase clase;
    private FragmentGeneralClasesBinding binding;

    public GeneralClasesFragment() {
    }

    public static GeneralClasesFragment newInstance(Clase clase) {
        GeneralClasesFragment fragment = new GeneralClasesFragment();
        Bundle args = new Bundle();
        args.putParcelable(CLASE_BUNDLE, clase);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            clase = getArguments().getParcelable(CLASE_BUNDLE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentGeneralClasesBinding.inflate(inflater, container, false);
        rellenaDatos();

        return binding.getRoot();
    }

    private void rellenaDatos() {
        Glide.with(requireContext())
                .load(URL_BASE_IMAGEN_CLASES + clase.getImagen())
                .into(binding.imagenFichaClase);

        binding.fichaClaseGolpe.setText(clase.getDadosGolpe());
        binding.fichaCarp.setText(clase.getCaracteristicaPrincipal());
        binding.fichaClaseSal.setText(clase.getTiradasSalvacion());
        binding.fichaDescClase.setText(clase.getDescripcion());
    }
}