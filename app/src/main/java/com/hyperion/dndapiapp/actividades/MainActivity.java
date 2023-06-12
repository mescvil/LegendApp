package com.hyperion.dndapiapp.actividades;

import static com.hyperion.dndapiapp.utilidades.Constantes.ACTIVIDAD_USER;
import static com.hyperion.dndapiapp.utilidades.Constantes.CIERRA_SESION_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.USUARIO_BUNDLE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.controladores.Controlador;
import com.hyperion.dndapiapp.controladores.ObservadorDatos;
import com.hyperion.dndapiapp.databinding.ActivityMainBinding;
import com.hyperion.dndapiapp.dialogos.LoadingDialog;
import com.hyperion.dndapiapp.entidades.usuario.Usuario;
import com.hyperion.dndapiapp.fragmentos.BibliotecaFragment;
import com.hyperion.dndapiapp.fragmentos.FavoritosFragment;
import com.hyperion.dndapiapp.fragmentos.FichasFragment;

public class MainActivity extends AppCompatActivity implements ObservadorDatos {

    private Usuario usuario;

    /* Utils */
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private Controlador controlador;

    /* Fragmentos */
    private BibliotecaFragment bibliotecaFragment;
    private FavoritosFragment favoritosFragment;
    private FichasFragment fichasFragment;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();
        controlador = Controlador.getInstance();
        controlador.suscribirse(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            usuario = bundle.getParcelable(USUARIO_BUNDLE);
        }

        iniciaActividad();
    }

    @SuppressLint("DiscouragedApi")
    private void iniciaActividad() {
        loadingDialog = new LoadingDialog(this);
        loadingDialog.show("Cargando recursos");

        inicializaFragmentos();
        aniadeListenerNavBar();

        int resImage = getResources().getIdentifier(usuario.getImagenPerfil(), "drawable", getPackageName());
        binding.botonUsuario.setImageResource(resImage);

        binding.botonUsuario.setOnClickListener(view -> {
            Intent intent = new Intent(this, UserActivity.class);
            intent.putExtra(USUARIO_BUNDLE, usuario);
            startActivityForResult(intent, ACTIVIDAD_USER);
        });

        controlador.iniciaFavoritos(getApplicationContext());
        binding.tituloFragment.setText("Glosario");
        controlador.cargaRecursos();
    }

    private void inicializaFragmentos() {
        bibliotecaFragment = BibliotecaFragment.newInstance();
        favoritosFragment = FavoritosFragment.newInstance();
        fichasFragment = FichasFragment.newInstance(usuario.getCorreo());
    }

    private void aniadeListenerNavBar() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navBiblioteca) {
                binding.tituloFragment.setText("Glosario");
                cambiaFragmento(bibliotecaFragment);

            } else if (itemId == R.id.navFavoritos) {
                binding.tituloFragment.setText("Favoritos");
                cambiaFragmento(favoritosFragment);

            } else if (itemId == R.id.navFichas) {
                binding.tituloFragment.setText("Mis Fichas");
                cambiaFragmento(fichasFragment);
            }
            return true;
        });
    }

    private void cambiaFragmento(Fragment fragment) {
        if (!fragmentManager.isDestroyed()) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(binding.frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }

    /*  ================= Observador =================  */

    @Override
    public void exitoObteniendoDatos() {
        loadingDialog.dismiss();
        cambiaFragmento(bibliotecaFragment);
    }

    @Override
    public void falloObteniendoDatos() {
        loadingDialog.dismiss();
        Toast.makeText(this, "Error al cargar los recuros", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVIDAD_USER && resultCode == Activity.RESULT_OK) {
            Intent intent = new Intent(this, EmptyMainActivity.class);
            intent.putExtra(CIERRA_SESION_BUNDLE, true);
            startActivity(intent);
            finish();
        }
    }
}