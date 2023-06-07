package com.hyperion.dndapiapp.actividades.fichas;

import static com.hyperion.dndapiapp.utilidades.Constantes.ARMA_BUNDLE;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hyperion.dndapiapp.databinding.ActivityFichaArmaBinding;
import com.hyperion.dndapiapp.entidades.equipamiento.Arma;

public class FichaArmaActivity extends AppCompatActivity {

    private Arma arma;
    private ActivityFichaArmaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFichaArmaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            arma = bundle.getParcelable(ARMA_BUNDLE);
        }

        iniciaActividad();
    }

    private void iniciaActividad() {
        binding.fichaArmaTitulo.setText(arma.getNombre());
        binding.fichaDanioArma.setText(arma.getDanio());
        binding.checkDosManos.setChecked(arma.isDosManos());
        binding.checkArrojadiza.setChecked(arma.isArrojadiza());
        binding.fichaPrecioArma.setText(arma.getPrecio());
        binding.fichaPropiedadesArma.setText(arma.getPropiedad());

        binding.botonAtrasFichaArma.setOnClickListener(view -> finish());
    }
}