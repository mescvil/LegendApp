package com.hyperion.dndapiapp.actividades.fichas;

import static com.hyperion.dndapiapp.utilidades.Constantes.CLASE_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.FAVORITO_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO;
import static com.hyperion.dndapiapp.utilidades.Constantes.IS_FAVORITO_RESULT;
import static com.hyperion.dndapiapp.utilidades.Constantes.LISTA_FAVORITOS_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.LISTA_FAVORITOS_CLASE_BUNDLE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.adaptadores.fragmentState.ClaseStateAdapter;
import com.hyperion.dndapiapp.databinding.ActivityFichaClaseBinding;
import com.hyperion.dndapiapp.entidades.glosario.clases.Clase;
import com.hyperion.dndapiapp.sqlite.Favorito;
import com.hyperion.dndapiapp.sqlite.FavoritoClase;

import java.util.ArrayList;
import java.util.List;

public class FichaClaseAcitivity extends AppCompatActivity {

    private List<Favorito> favoritosRecibidos;
    private List<FavoritoClase> favoritosEnviar;
    private Clase clase;
    private ActivityFichaClaseBinding binding;
    private boolean isFavorito;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFichaClaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.favoritosRecibidos = new ArrayList<>();
        this.favoritosEnviar = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            clase = bundle.getParcelable(CLASE_BUNDLE);
            isFavorito = bundle.getBoolean(IS_FAVORITO);

            for (Parcelable parcelable : bundle.getParcelableArray(LISTA_FAVORITOS_BUNDLE)) {
                favoritosRecibidos.add((Favorito) parcelable);
            }
        }

        iniciaActividad();
        cambiaIconoFavorito();
    }

    private void iniciaActividad() {
        tabLayout = binding.tabLayoutClase;
        viewPager = binding.viewPagerClase;
        binding.fichaClaseTitulo.setText(clase.getNombre());

        ClaseStateAdapter adapter = new ClaseStateAdapter(this, clase);
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

        binding.botonAtrasFichaClase.setOnClickListener(view -> finaliza());
        binding.botonFavClase.setOnClickListener(view -> clickFavorito());
    }

    private void finaliza() {
        Intent intentResultado = new Intent();
        intentResultado.putExtra(FAVORITO_BUNDLE, new Favorito(clase.getNombre(), Clase.class.getSimpleName()));
        intentResultado.putExtra(IS_FAVORITO_RESULT, isFavorito);
        intentResultado.putExtra(LISTA_FAVORITOS_CLASE_BUNDLE, favoritosEnviar.toArray(new FavoritoClase[0]));
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
        binding.botonFavClase.setBackgroundResource(resImage);
    }

    @Override
    public void onBackPressed() {
        finaliza();
    }

    public List<Favorito> getFavoritosRecibidos() {
        return favoritosRecibidos;
    }

    public void gestionaFavorito(Favorito favorito, boolean isFavorito) {
        favoritosEnviar.add(new FavoritoClase(favorito.getNombre(), favorito.getTipo(), isFavorito));
    }
}