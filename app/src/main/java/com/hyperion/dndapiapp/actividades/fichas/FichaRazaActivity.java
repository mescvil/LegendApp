package com.hyperion.dndapiapp.actividades.fichas;

import static com.hyperion.dndapiapp.utilidades.Constantes.RAZA_BUNDLE;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.hyperion.dndapiapp.adaptadores.fragmentState.RazaStateAdapter;
import com.hyperion.dndapiapp.databinding.ActivityFichaRazaBinding;
import com.hyperion.dndapiapp.entidades.razas.Raza;

public class FichaRazaActivity extends AppCompatActivity {

    private Raza raza;
    private ActivityFichaRazaBinding binding;

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
        }

        iniciaActividad();
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

        binding.botonAtrasFichaRaza.setOnClickListener(view -> finish());
    }
}