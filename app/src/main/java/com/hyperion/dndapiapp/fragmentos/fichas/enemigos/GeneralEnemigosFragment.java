package com.hyperion.dndapiapp.fragmentos.fichas.enemigos;

import static com.hyperion.dndapiapp.utilidades.Constantes.ENEMIGO_BUNDLE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hyperion.dndapiapp.databinding.FragmentGeneralEnemigosBinding;
import com.hyperion.dndapiapp.entidades.enemigos.Enemigo;

public class GeneralEnemigosFragment extends Fragment {

    private Enemigo enemigo;
    private FragmentGeneralEnemigosBinding binding;

    public GeneralEnemigosFragment() {
    }

    public static GeneralEnemigosFragment newInstance(Enemigo enemigo) {
        GeneralEnemigosFragment fragment = new GeneralEnemigosFragment();
        Bundle args = new Bundle();
        args.putParcelable(ENEMIGO_BUNDLE, enemigo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            enemigo = getArguments().getParcelable(ENEMIGO_BUNDLE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentGeneralEnemigosBinding.inflate(inflater, container, false);
        rellenaCamposEnemigo();

        return binding.getRoot();
    }

    private void rellenaCamposEnemigo() {
        binding.nombreFichaEnemigo.setText(enemigo.getNombre());
        binding.fichaAC.setText(String.valueOf(enemigo.getClaseArmadura()));
        binding.fichaHP.setText(String.valueOf(enemigo.getPuntosGolpe()));
    }
}