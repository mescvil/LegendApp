package com.hyperion.dndapiapp.actividades;

import static com.hyperion.dndapiapp.utilidades.Constantes.IMAGEN_USUARIO_BUNDLE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hyperion.dndapiapp.databinding.ActivityImageSelectorBinding;

public class ImageSelectorActivity extends AppCompatActivity {

    private ActivityImageSelectorBinding binding;
    private final static String imagenBase = "imagen_perfil_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageSelectorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        iniciaListenersImagenes();
    }

    private void iniciaListenersImagenes() {
        binding.imagenPerfilCaco.setOnClickListener(view -> {
            Intent intentResultado = new Intent();
            intentResultado.putExtra(IMAGEN_USUARIO_BUNDLE, imagenBase + "caco");
            setResult(Activity.RESULT_OK, intentResultado);
            finish();
        });

        binding.imagenPerfilHombre.setOnClickListener(view -> {
            Intent intentResultado = new Intent();
            intentResultado.putExtra(IMAGEN_USUARIO_BUNDLE, imagenBase + "humano");
            setResult(Activity.RESULT_OK, intentResultado);
            finish();
        });

        binding.imagenPerfilMarrana.setOnClickListener(view -> {
            Intent intentResultado = new Intent();
            intentResultado.putExtra(IMAGEN_USUARIO_BUNDLE, imagenBase + "fresca");
            setResult(Activity.RESULT_OK, intentResultado);
            finish();
        });

        binding.imagenPerfilDnd.setOnClickListener(view -> {
            Intent intentResultado = new Intent();
            intentResultado.putExtra(IMAGEN_USUARIO_BUNDLE, imagenBase + "dnd");
            setResult(Activity.RESULT_OK, intentResultado);
            finish();
        });

        binding.imagenPerfilDraconido.setOnClickListener(view -> {
            Intent intentResultado = new Intent();
            intentResultado.putExtra(IMAGEN_USUARIO_BUNDLE, imagenBase + "draconido");
            setResult(Activity.RESULT_OK, intentResultado);
            finish();
        });

        binding.imagePerfilMascara.setOnClickListener(view -> {
            Intent intentResultado = new Intent();
            intentResultado.putExtra(IMAGEN_USUARIO_BUNDLE, imagenBase + "mascara");
            setResult(Activity.RESULT_OK, intentResultado);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Selecciona una imagen", Toast.LENGTH_SHORT).show();
    }
}