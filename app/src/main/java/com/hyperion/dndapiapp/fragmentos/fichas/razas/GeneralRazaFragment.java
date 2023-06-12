package com.hyperion.dndapiapp.fragmentos.fichas.razas;

import static com.hyperion.dndapiapp.utilidades.Constantes.RAZA_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.URL_BASE_IMAGEN_RAZAS;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.hyperion.dndapiapp.databinding.FragmentGeneralRazaBinding;
import com.hyperion.dndapiapp.entidades.glosario.razas.Raza;

public class GeneralRazaFragment extends Fragment {

    private Raza raza;
    private FragmentGeneralRazaBinding binding;
    private boolean isFemenino;

    public GeneralRazaFragment() {
    }

    public static GeneralRazaFragment newInstance(Raza raza) {
        GeneralRazaFragment fragment = new GeneralRazaFragment();
        Bundle args = new Bundle();
        args.putParcelable(RAZA_BUNDLE, raza);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            raza = getArguments().getParcelable(RAZA_BUNDLE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentGeneralRazaBinding.inflate(inflater, container, false);
        isFemenino = true;
        iniciaFragmento();
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    private void iniciaFragmento() {
        binding.fichaAlturaMin.setText(raza.getAlturaMinima() + " pies");
        binding.fichaAlturaMax.setText(raza.getAlturaMaxima() + " pies");
        binding.fichaEdadMax.setText(raza.getEdadMaxima() + " años");
        binding.fichaVelocidad.setText(raza.getVelocidad() + " pies");
        cambiaImagen();

        binding.imagenFichaRaza.setOnClickListener(view -> cambiaImagen());
    }

    private void cambiaImagen() {
        Glide.with(requireContext())
                .load((isFemenino)
                        ? URL_BASE_IMAGEN_RAZAS + raza.getImagenHembra()
                        : URL_BASE_IMAGEN_RAZAS + raza.getImagenVaron())
                .into(binding.imagenFichaRaza);

        isFemenino = !isFemenino;
    }
}