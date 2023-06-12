package com.hyperion.dndapiapp.actividades.fichas;

import static com.hyperion.dndapiapp.utilidades.Constantes.ARMA_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.FAVORITO_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO_RESULT;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.databinding.ActivityFichaArmaBinding;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Arma;
import com.hyperion.dndapiapp.sqlite.Favorito;

public class FichaArmaActivity extends AppCompatActivity {

    private Arma arma;
    private boolean isFavorito;
    private ActivityFichaArmaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFichaArmaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            arma = bundle.getParcelable(ARMA_BUNDLE);
            isFavorito = bundle.getBoolean(IS_FAVORITO);
        }

        iniciaActividad();
        cambiaIconoFavorito();
    }

    private void iniciaActividad() {
        binding.fichaArmaTitulo.setText(arma.getNombre());
        binding.fichaDanioArma.setText(arma.getDanio());
        binding.checkDosManos.setChecked(arma.isDosManos());
        binding.checkArrojadiza.setChecked(arma.isArrojadiza());
        binding.fichaPrecioArma.setText(arma.getPrecio());
        binding.fichaPropiedadesArma.setText(arma.getPropiedad());

        binding.botonAtrasFichaArma.setOnClickListener(view -> finaliza());
        binding.botonFavArma.setOnClickListener(view -> clickFavorito());
    }


    private void finaliza() {
        Intent intentResultado = new Intent();
        intentResultado.putExtra(FAVORITO_BUNDLE, new Favorito(arma.getNombre(), Arma.class.getSimpleName()));
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
            resImage = R.drawable.icono_bookmark;
        } else {
            resImage = R.drawable.icono_bookmark_no;
        }
        binding.botonFavArma.setBackgroundResource(resImage);
    }

    @Override
    public void onBackPressed() {
        finaliza();
    }
}