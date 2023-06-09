package com.hyperion.dndapiapp.actividades.fichas;

import static com.hyperion.dndapiapp.utilidades.Constantes.FAVORITO_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO_RESULT;
import static com.hyperion.dndapiapp.utilidades.Constantes.TRASFONDO_COMPETENCIAS_BUNDLE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.fragmentState.TrasfondoStateAdapter;
import com.hyperion.dndapiapp.databinding.ActivityFichaTrasfondoBinding;
import com.hyperion.dndapiapp.entidades.glosario.trasfondos.Trasfondo;
import com.hyperion.dndapiapp.sqlite.Favorito;

public class FichaTrasfondoActivity extends AppCompatActivity {

    private Trasfondo trasfondo;
    private ActivityFichaTrasfondoBinding binding;
    private boolean isFavorito;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFichaTrasfondoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            trasfondo = bundle.getParcelable(TRASFONDO_COMPETENCIAS_BUNDLE);
            isFavorito = bundle.getBoolean(IS_FAVORITO);

        }

        iniciaActividad();
        cambiaIconoFavorito();
    }

    private void iniciaActividad() {
        TrasfondoStateAdapter adapter = new TrasfondoStateAdapter(this, trasfondo);

        tabLayout = binding.tabLayoutTrasfondos;
        viewPager = binding.viewPagerTrasfondos;

        binding.fichaTrasfondoTitulo.setText(trasfondo.getNombre());
        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                TabLayout.Tab tab = tabLayout.getTabAt(position);
                if (tab != null)
                    tab.select();
            }
        });

        binding.botonAtrasFichaTrasfondo.setOnClickListener(view -> finaliza());
        binding.botonFavTrasfondo.setOnClickListener(view -> clickFavorito());
    }

    private void finaliza() {
        Intent intentResultado = new Intent();
        intentResultado.putExtra(FAVORITO_BUNDLE, new Favorito(trasfondo.getNombre(), Trasfondo.class.getSimpleName()));
        intentResultado.putExtra(IS_FAVORITO_RESULT, isFavorito);
        setResult(Activity.RESULT_OK, intentResultado);

        finish();
    }

    private void clickFavorito() {
        isFavorito = !isFavorito;
        cambiaIconoFavorito();
    }

    private void cambiaIconoFavorito() {
        int resImage;

        if (isFavorito) {
            resImage = R.drawable.icono_bookmark;
        } else {
            resImage = R.drawable.icono_bookmark_no;
        }
        binding.botonFavTrasfondo.setBackgroundResource(resImage);
    }

    @Override
    public void onBackPressed() {
        finaliza();
    }
}
