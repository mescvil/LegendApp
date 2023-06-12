package com.hyperion.dndapiapp.actividades.fichas;

import static com.hyperion.dndapiapp.utilidades.Constantes.ENEMIGO_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.FAVORITO_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO_RESULT;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.fragmentState.EnemigosStateAdapter;
import com.hyperion.dndapiapp.databinding.ActivityFichaEnemigoBinding;
import com.hyperion.dndapiapp.entidades.glosario.enemigos.Enemigo;
import com.hyperion.dndapiapp.sqlite.Favorito;

public class FichaEnemigoActivity extends AppCompatActivity {

    private Enemigo enemigo;
    private ActivityFichaEnemigoBinding binding;
    private boolean isFavorito;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFichaEnemigoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            enemigo = bundle.getParcelable(ENEMIGO_BUNDLE);
            isFavorito = bundle.getBoolean(IS_FAVORITO);
        }

        iniciaActividad();
        cambiaIconoFavorito();
    }

    private void iniciaActividad() {
        tabLayout = binding.tabLayoutEnemigos;
        viewPager = binding.viewPagerEnemigos;
        binding.fichaEnemigoTitulo.setText(enemigo.getNombre());

        EnemigosStateAdapter adapter = new EnemigosStateAdapter(this, enemigo);
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

        binding.botonAtrasFichaEnemigo.setOnClickListener(view -> finaliza());
        binding.botonFavEnemigo.setOnClickListener(view -> clickFavorito());
    }

    private void finaliza() {
        Intent intentResultado = new Intent();
        intentResultado.putExtra(FAVORITO_BUNDLE, new Favorito(enemigo.getNombre(), Enemigo.class.getSimpleName()));
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
        binding.botonFavEnemigo.setBackgroundResource(resImage);
    }

    @Override
    public void onBackPressed() {
        finaliza();
    }
}