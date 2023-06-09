package com.hyperion.dndapiapp.actividades.fichas;

import static com.hyperion.dndapiapp.utilidades.Constantes.ARMADURA_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.FAVORITO_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO_RESULT;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hyperion.dndapiapp.databinding.ActivityFichaArmaduraBinding;
import com.hyperion.dndapiapp.entidades.equipamiento.Arma;
import com.hyperion.dndapiapp.entidades.equipamiento.Armadura;
import com.hyperion.dndapiapp.sqlite.Favorito;

public class FichaArmaduraActivity extends AppCompatActivity {

    private Armadura armadura;
    private boolean isFavorito;
    private ActivityFichaArmaduraBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFichaArmaduraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            armadura = bundle.getParcelable(ARMADURA_BUNDLE);
            isFavorito = bundle.getBoolean(IS_FAVORITO);
        }

        iniciaActividad();
        cambiaIconoFavorito();
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

        binding.botonAtrasFichaArmadura.setOnClickListener(view -> finaliza());
        binding.botonFavArmadura.setOnClickListener(view -> clickFavorito());
    }


    private void finaliza() {
        Intent intentResultado = new Intent();
        intentResultado.putExtra(FAVORITO_BUNDLE, new Favorito(armadura.getNombre(), Armadura.class.getSimpleName()));
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

        binding.botonFavArmadura.setBackgroundResource(resImage);
    }

    @Override
    public void onBackPressed() {
        finaliza();
    }
}