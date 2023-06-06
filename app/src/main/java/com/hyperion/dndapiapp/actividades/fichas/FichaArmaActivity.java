package com.hyperion.dndapiapp.actividades.fichas;

import static com.hyperion.dndapiapp.utilidades.Constantes.ARMA_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.HECHIZOS_BUNDLE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.databinding.ActivityFichaArmaBinding;
import com.hyperion.dndapiapp.databinding.ActivityFichaHechizoBinding;
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
        binding.fichaDosArmas.setText((arma.isDosManos()) ? "Si" : "No");
        binding.fichaArrojadiza.setText((arma.isArrojadiza()) ? "Si" : "No");
        binding.fichaPrecioArma.setText(arma.getPrecio());
        binding.fichaPropiedadesArma.setText(arma.getPropiedad());

        binding.botonAtrasFichaArma.setOnClickListener(view -> finish());
    }
}