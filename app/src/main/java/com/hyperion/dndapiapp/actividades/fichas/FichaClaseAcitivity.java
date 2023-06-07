package com.hyperion.dndapiapp.actividades.fichas;

import static com.hyperion.dndapiapp.utilidades.Constantes.CLASE_BUNDLE;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.hyperion.dndapiapp.adaptadores.fragmentState.ClaseStateAdapter;
import com.hyperion.dndapiapp.databinding.ActivityFichaClaseBinding;
import com.hyperion.dndapiapp.entidades.clases.Clase;

public class FichaClaseAcitivity extends AppCompatActivity {

    private Clase clase;
    private ActivityFichaClaseBinding binding;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFichaClaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            clase = bundle.getParcelable(CLASE_BUNDLE);
        }

        iniciaActividad();
    }

    private void iniciaActividad() {
        tabLayout = binding.tabLayoutClase;
        viewPager = binding.viewPagerClase;
        binding.fichaClaseTitulo.setText(clase.getNombre());

        ClaseStateAdapter adapter = new ClaseStateAdapter(this, clase);
        viewPager.setAdapter(adapter);

        binding.botonAtrasFichaClase.setOnClickListener(view -> finish());

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