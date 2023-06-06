package com.hyperion.dndapiapp.actividades.fichas;

import static com.hyperion.dndapiapp.utilidades.Constantes.ARMADURA_BUNDLE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hyperion.dndapiapp.databinding.ActivityFichaArmaduraBinding;
import com.hyperion.dndapiapp.entidades.equipamiento.Armadura;

public class FichaArmaduraActivity extends AppCompatActivity {

    private Armadura armadura;
    private ActivityFichaArmaduraBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFichaArmaduraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            armadura = bundle.getParcelable(ARMADURA_BUNDLE);
        }

        iniciaActividad();
    }

    @SuppressLint("SetTextI18n")
    private void iniciaActividad() {
        binding.fichaArmaduraTitulo.setText(armadura.getNombre());
        binding.fichaCA.setText(armadura.getClaseArmadura());
        binding.fichaPrecioArmadura.setText(armadura.getPrecio());
        binding.fichaTipoArmadura.setText(armadura.getTipo());
        binding.fichaPesoArmadura.setText(armadura.getPeso() + " Kg");
        binding.fichaFuerzaReq.setText(String.valueOf(armadura.getFuerzaRequerida()));
        binding.checkSigilo.setChecked(armadura.isDesventajaSigilo());

        binding.botonAtrasFichaArmadura.setOnClickListener(view -> finish());
    }
}