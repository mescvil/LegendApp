package com.hyperion.dndapiapp.actividades.fichas;

import static com.hyperion.dndapiapp.utilidades.Constantes.FAVORITO_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO_RESULT;
import static com.hyperion.dndapiapp.utilidades.Constantes.RAZA_BUNDLE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.fragmentState.RazaStateAdapter;
import com.hyperion.dndapiapp.databinding.ActivityFichaRazaBinding;
import com.hyperion.dndapiapp.entidades.glosario.razas.Raza;
import com.hyperion.dndapiapp.sqlite.Favorito;

public class FichaRazaActivity extends AppCompatActivity {

    private Raza raza;
    private ActivityFichaRazaBinding binding;
    private boolean isFavorito;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFichaRazaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            raza = bundle.getParcelable(RAZA_BUNDLE);
            isFavorito = bundle.getBoolean(IS_FAVORITO);
        }

        iniciaActividad();
        cambiaIconoFavorito();
    }

    private void iniciaActividad() {
        RazaStateAdapter adapter = new RazaStateAdapter(this, raza);

        tabLayout = binding.tabLayoutRaza;
        viewPager = binding.viewPagerRaza;

        binding.fichaRazaTitulo.setText(raza.getNombre());
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

        binding.botonAtrasFichaRaza.setOnClickListener(view -> finaliza());
        binding.botonFavRaza.setOnClickListener(view -> clickFavorito());
    }

    private void finaliza() {
        Intent intentResultado = new Intent();
        intentResultado.putExtra(FAVORITO_BUNDLE, new Favorito(raza.getNombre(), Raza.class.getSimpleName()));
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
        binding.botonFavRaza.setBackgroundResource(resImage);
    }

    @Override
    public void onBackPressed() {
        finaliza();
    }
}