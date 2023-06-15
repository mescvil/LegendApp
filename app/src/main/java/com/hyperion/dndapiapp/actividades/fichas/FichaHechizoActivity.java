package com.hyperion.dndapiapp.actividades.fichas;

import static com.hyperion.dndapiapp.utilidades.Constantes.FAVORITO_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.HECHIZOS_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO_RESULT;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_READ_ONLY;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hyperion.dndapiapp.databinding.ActivityFichaHechizoBinding;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Hechizo;
import com.hyperion.dndapiapp.sqlite.Favorito;

public class FichaHechizoActivity extends AppCompatActivity {

    private Hechizo hechizo;
    private boolean isFavorito;
    private boolean isSoloLectura;
    private ActivityFichaHechizoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFichaHechizoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            hechizo = bundle.getParcelable(HECHIZOS_BUNDLE);
            isFavorito = bundle.getBoolean(IS_FAVORITO);
            isSoloLectura = bundle.getBoolean(IS_READ_ONLY);
        }

        iniciaActividad();
        cambiaIconoFavorito();
    }

    private void iniciaActividad() {
        binding.botonAtrasFichaHehizo.setOnClickListener(view -> finaliza());
        binding.botonFavHechizo.setOnClickListener(view -> clickFavorito());

        binding.fichaHechizoTitulo.setText(hechizo.getNombre());
        binding.fichaNivel.setText(String.valueOf(hechizo.getNivel()));
        binding.fichaAlcance.setText(String.valueOf(hechizo.getAlcance()));
        binding.fichaDuracion.setText(hechizo.getDuracion());
        binding.fichaLanzamiento.setText(hechizo.getTiempoLanzamiento());
        binding.fichaSalvacion.setText(hechizo.getTiradaSalvacion());
        binding.fichaDesc.setText(hechizo.getDescripcion());

        if (isSoloLectura)
            binding.botonFavHechizo.setVisibility(View.INVISIBLE);
    }

    private void finaliza() {
        Intent intentResultado = new Intent();
        intentResultado.putExtra(FAVORITO_BUNDLE, new Favorito(hechizo.getNombre(), Hechizo.class.getSimpleName()));
        intentResultado.putExtra(IS_FAVORITO_RESULT, isFavorito);
        setResult(Activity.RESULT_OK, intentResultado);

        finish();
    }

    private void clickFavorito() {
        isFavorito = !isFavorito;
        cambiaIconoFavorito();
    }

    @SuppressLint("DiscouragedApi")
    private void cambiaIconoFavorito() {
        int resImage;

        if (isFavorito) {
            resImage = getResources().getIdentifier("icono_bookmark", "drawable", getPackageName());
        } else {
            resImage = getResources().getIdentifier("icono_bookmark_no", "drawable", getPackageName());
        }

        binding.botonFavHechizo.setBackgroundResource(resImage);
    }

    @Override
    public void onBackPressed() {
        finaliza();
    }
}