package com.hyperion.dndapiapp.actividades;

import static com.hyperion.dndapiapp.utilidades.Constantes.FICHA_BUNDLE;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.hyperion.dndapiapp.adaptadores.fragmentState.PersonajeStateAdapter;
import com.hyperion.dndapiapp.databinding.ActivityFichaPersonajeBinding;
import com.hyperion.dndapiapp.entidades.fichas.PersonajeFicha;

public class FichaPersonajeActivity extends AppCompatActivity {

    private ActivityFichaPersonajeBinding binding;
    private PersonajeFicha ficha;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFichaPersonajeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ficha = bundle.getParcelable(FICHA_BUNDLE);
        }

        iniciaActividad();
    }

    private void iniciaActividad() {
        binding.botonAtrasFichaPersonaje.setOnClickListener(view -> finish());
        binding.fichaPersonajeTitulo.setText(ficha.getNombre());

        tabLayout = binding.tabLayoutPersonaje;
        viewPager = binding.viewPagerPersonaje;

        PersonajeStateAdapter adapter = new PersonajeStateAdapter(this, ficha);
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
    }
}