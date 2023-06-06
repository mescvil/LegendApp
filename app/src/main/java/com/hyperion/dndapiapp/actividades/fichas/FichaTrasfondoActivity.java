package com.hyperion.dndapiapp.actividades.fichas;

import static com.hyperion.dndapiapp.utilidades.Constantes.TRASFONDO_COMPETENCIAS_BUNDLE;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.hyperion.dndapiapp.adaptadores.fragmentState.TrasfondoStateAdapter;
import com.hyperion.dndapiapp.databinding.ActivityFichaTrasfondoBinding;
import com.hyperion.dndapiapp.entidades.trasfondos.Trasfondo;

public class FichaTrasfondoActivity extends AppCompatActivity {

    private Trasfondo trasfondo;
    private ActivityFichaTrasfondoBinding binding;

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
        }

        iniciaActividad();

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

        binding.botonAtrasFichaTrasfondo.setOnClickListener(view -> finish());
    }
}