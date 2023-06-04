package com.hyperion.dndapiapp.actividades;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.databinding.ActivityMainBinding;
import com.hyperion.dndapiapp.fragmentos.BibliotecaFragment;
import com.hyperion.dndapiapp.fragmentos.FavoritosFragment;
import com.hyperion.dndapiapp.fragmentos.FichasFragment;

public class MainActivity extends AppCompatActivity {

    /* Utils */
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private SharedPreferences sharedPref;

    /* Fragmentos */
    private BibliotecaFragment bibliotecaFragment;
    private FavoritosFragment favoritosFragment;
    private FichasFragment fichasFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fragmentManager = getSupportFragmentManager();

        sharedPref = getPreferences(Context.MODE_PRIVATE);

        inicializaFragmentos();
        aniadeListenerNavBar();
        cambiaFragmento(bibliotecaFragment);
    }

    private void inicializaFragmentos() {
        bibliotecaFragment = new BibliotecaFragment(sharedPref);
        favoritosFragment = new FavoritosFragment();
        fichasFragment = new FichasFragment();
    }

    private void aniadeListenerNavBar() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navBiblioteca) {
                cambiaFragmento(bibliotecaFragment);

            } else if (itemId == R.id.navFavoritos) {
                cambiaFragmento(favoritosFragment);

            } else if (itemId == R.id.navFichas) {
                cambiaFragmento(fichasFragment);
            }
            return true;
        });
    }

    private void cambiaFragmento(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(binding.frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

}