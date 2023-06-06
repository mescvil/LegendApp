package com.hyperion.dndapiapp.actividades.fichas;

import static com.hyperion.dndapiapp.utilidades.Constantes.ENEMIGO_BUNDLE;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.hyperion.dndapiapp.adaptadores.fragmentState.EnemigosStateAdapter;
import com.hyperion.dndapiapp.databinding.ActivityFichaEnemigoBinding;
import com.hyperion.dndapiapp.entidades.enemigos.Enemigo;

public class FichaEnemigoActivity extends AppCompatActivity {

    private Enemigo enemigo;
    private ActivityFichaEnemigoBinding binding;

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
        }

        iniciaActividad();
    }

    private void iniciaActividad() {
        tabLayout = binding.tabLayoutEnemigos;
        viewPager = binding.viewPagerEnemigos;
        binding.fichaEnemigoTitulo.setText(enemigo.getNombre());

        EnemigosStateAdapter adapter = new EnemigosStateAdapter(this, enemigo);
        viewPager.setAdapter(adapter);

        binding.botonAtrasFichaEnemigo.setOnClickListener(view -> {
            finish();
        });

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
    }
}