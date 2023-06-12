package com.hyperion.dndapiapp.fragmentos.fichas.enemigos;

import static com.hyperion.dndapiapp.utilidades.Constantes.ENEMIGO_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.URL_BASE_IMAGEN_CRIATURAS;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.hyperion.dndapiapp.databinding.FragmentGeneralEnemigosBinding;
import com.hyperion.dndapiapp.entidades.glosario.enemigos.Enemigo;

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
        Glide.with(requireContext())
                .load(URL_BASE_IMAGEN_CRIATURAS + enemigo.getUrlImagen())
                .into(binding.imagenFichaEnemigo);

        binding.fichaAC.setText(String.valueOf(enemigo.getClaseArmadura()));
        binding.fichaHP.setText(String.valueOf(enemigo.getPuntosGolpe()));


        String fuerza = String.valueOf(enemigo.getFuerza());
        String constitucion = String.valueOf(enemigo.getConstitucion());
        String sabiduria = String.valueOf(enemigo.getSabiduria());

        binding.fichaDES.setText((String.valueOf(enemigo.getDestreza())));
        binding.fichaFUE.setText((fuerza.length() == 1) ? fuerza + " " : fuerza);
        binding.fichaCON.setText((constitucion.length() == 1) ? constitucion + " " : constitucion);
        binding.fichaINT.setText(String.valueOf(enemigo.getInteligencia()));
        binding.fichaCAR.setText(String.valueOf(enemigo.getCarisma()));
        binding.fichaSAB.setText((sabiduria.length() == 1) ? sabiduria + " " : sabiduria);

        binding.fichaSentidos.setText(enemigo.getSentidos());
        binding.fichaTipo.setText(enemigo.getTipo());
        binding.fichaTamanio.setText(enemigo.getTamanio());
        binding.fichaHabilidades.setText(enemigo.getHabilidades());
        binding.fichaIdiomas.setText(enemigo.getIdiomas());
        binding.fichaAlineamiento.setText(enemigo.getAlineamiento().replace("_", " "));
        binding.fichaDesafio.setText(String.valueOf(enemigo.getDesafio()));

    }
}