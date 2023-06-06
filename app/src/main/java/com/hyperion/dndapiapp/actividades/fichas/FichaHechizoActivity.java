package com.hyperion.dndapiapp.actividades.fichas;

import static com.hyperion.dndapiapp.utilidades.Constantes.HECHIZOS_BUNDLE;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hyperion.dndapiapp.databinding.ActivityFichaHechizoBinding;
import com.hyperion.dndapiapp.entidades.equipamiento.Hechizo;

public class FichaHechizoActivity extends AppCompatActivity {

    private Hechizo hechizo;
    private ActivityFichaHechizoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFichaHechizoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            hechizo = bundle.getParcelable(HECHIZOS_BUNDLE);
        }

        iniciaActividad();
    }

    private void iniciaActividad() {
        binding.botonAtrasFichaHehizo.setOnClickListener(view -> finish());

        binding.fichaHechizoTitulo.setText(hechizo.getNombre());
        binding.fichaNivel.setText(String.valueOf(hechizo.getNivel()));
        binding.fichaAlcance.setText(String.valueOf(hechizo.getAlcance()));
        binding.fichaDuracion.setText(hechizo.getDuracion());
        binding.fichaLanzamiento.setText(hechizo.getTiempoLanzamiento());
        binding.fichaSalvacion.setText(hechizo.getTiradaSalvacion());
        binding.fichaDesc.setText(hechizo.getDescripcion());
    }
}