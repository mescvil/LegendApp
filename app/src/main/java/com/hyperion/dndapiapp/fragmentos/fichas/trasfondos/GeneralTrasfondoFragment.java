package com.hyperion.dndapiapp.fragmentos.fichas.trasfondos;

import static com.hyperion.dndapiapp.utilidades.Constantes.TRASFONDO_BUNDLE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hyperion.dndapiapp.databinding.FragmentGeneralTrasfondoBinding;
import com.hyperion.dndapiapp.entidades.glosario.trasfondos.Trasfondo;

public class GeneralTrasfondoFragment extends Fragment {

    private Trasfondo trasfondo;
    private FragmentGeneralTrasfondoBinding binding;

    public GeneralTrasfondoFragment() {
    }

    public static GeneralTrasfondoFragment newInstance(Trasfondo trasfondo) {
        GeneralTrasfondoFragment fragment = new GeneralTrasfondoFragment();
        Bundle args = new Bundle();
        args.putParcelable(TRASFONDO_BUNDLE, trasfondo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            trasfondo = bundle.getParcelable(TRASFONDO_BUNDLE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentGeneralTrasfondoBinding.inflate(inflater, container, false);
        rellenaCampos();

        return binding.getRoot();
    }

    private void rellenaCampos() {
        binding.tituloTrasfondo.setText(trasfondo.getNombre());
        binding.fichaTrasfondoDescripcion.setText(trasfondo.getDescripcion());
    }
}