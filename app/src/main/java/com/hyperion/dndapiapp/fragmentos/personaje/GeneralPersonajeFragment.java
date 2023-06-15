package com.hyperion.dndapiapp.fragmentos.personaje;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.databinding.FragmentGeneralPersonajeBinding;
import com.hyperion.dndapiapp.databinding.FragmentMochilaPersonajeBinding;
import com.hyperion.dndapiapp.entidades.fichas.PersonajeFicha;
import com.hyperion.dndapiapp.utilidades.Utils;

public class GeneralPersonajeFragment extends Fragment {

    private final static String FICHA_ARG = "ficha";

    private FragmentGeneralPersonajeBinding binding;
    private PersonajeFicha ficha;
    private int nivel;

    public GeneralPersonajeFragment() {
        nivel = 1;
    }

    public static GeneralPersonajeFragment newInstance(PersonajeFicha personajeFicha) {
        GeneralPersonajeFragment fragment = new GeneralPersonajeFragment();
        Bundle args = new Bundle();
        args.putParcelable(FICHA_ARG, personajeFicha);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ficha = getArguments().getParcelable(FICHA_ARG);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentGeneralPersonajeBinding.inflate(inflater, container, false);
        iniciaFragmento();
        return binding.getRoot();
    }

    private void iniciaFragmento() {
        binding.razaPersonaje.setText(ficha.getRaza().getNombre());
        binding.alineamientoPersonaje.setText(ficha.getAlineamiento());
        binding.trasfondoPersonaje.setText(ficha.getHistoria().getTrasfondo());
        binding.clasePersonaje.setText(ficha.getClase().getNombre());
        binding.especialidadPersonaje.setText(ficha.getClase().getEspecialidad());

        binding.campoCA.setText(String.valueOf(ficha.getClaseArmadura()));
        binding.campoVelocidad.setText(String.valueOf(30));

        int bono = Utils.calculaBonificador(ficha.getDestreza());
        binding.campoInicativa.setText((bono > 0) ? "+" + bono : String.valueOf(bono));

        int vida = Utils.calculaVida(nivel, ficha.getClase().getDadosGolpe(), ficha.getConstitucion());
        binding.campoVida.setText(String.valueOf(vida));

        binding.campoFuerza.setText(String.valueOf(ficha.getFuerza()));
        binding.campoDestreza.setText(String.valueOf(ficha.getDestreza()));
        binding.campoCons.setText(String.valueOf(ficha.getConstitucion()));
        binding.campoInte.setText(String.valueOf(ficha.getInteligencia()));
        binding.campoSab.setText(String.valueOf(ficha.getSabiduria()));
        binding.campoCar.setText(String.valueOf(ficha.getCarisma()));

        bono = Utils.calculaBonificador(ficha.getFuerza());
        binding.modFue.setText(String.format("FUE (%s)", (bono > 0) ? "+" + bono : String.valueOf(bono)));

        bono = Utils.calculaBonificador(ficha.getDestreza());
        binding.modDes.setText(String.format("DES (%s)", (bono > 0) ? "+" + bono : String.valueOf(bono)));

        bono = Utils.calculaBonificador(ficha.getConstitucion());
        binding.modCon.setText(String.format("CON (%s)", (bono > 0) ? "+" + bono : String.valueOf(bono)));

        bono = Utils.calculaBonificador(ficha.getInteligencia());
        binding.modInt.setText(String.format("INT (%s)", (bono > 0) ? "+" + bono : String.valueOf(bono)));

        bono = Utils.calculaBonificador(ficha.getSabiduria());
        binding.modSab.setText(String.format("SAB (%s)", (bono > 0) ? "+" + bono : String.valueOf(bono)));

        bono = Utils.calculaBonificador(ficha.getCarisma());
        binding.modCar.setText(String.format("CAR (%s)", (bono > 0) ? "+" + bono : String.valueOf(bono)));

        binding.nivelPersonaje.addTextChangedListener(new OurTextWatcher());
    }

    private class OurTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (Utils.esNumerico(charSequence.toString())) {
                nivel = Integer.parseInt(charSequence.toString());

                if (nivel > 20) {
                    Toast.makeText(getContext(), "No puedes superar el nivel 20", Toast.LENGTH_SHORT).show();
                    return;
                }


                int vida = Utils.calculaVida(nivel, ficha.getClase().getDadosGolpe(), ficha.getConstitucion());
                binding.campoVida.setText(String.valueOf(vida));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }
}