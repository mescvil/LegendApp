package com.hyperion.dndapiapp.actividades;

import static com.hyperion.dndapiapp.utilidades.Constantes.DB_NAME;
import static com.hyperion.dndapiapp.utilidades.Constantes.USUARIO_BUNDLE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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
import com.hyperion.dndapiapp.sqlite.Favorito;
import com.hyperion.dndapiapp.sqlite.FavoritoClase;
import com.hyperion.dndapiapp.sqlite.SQLiteHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ObservadorDatos {

    private Usuario usuario;

    /* Utils */
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private SQLiteHelper sqLiteHelper;
    private List<Favorito> favoritos;
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
        sqLiteHelper = SQLiteHelper.getInstance(this, DB_NAME, null, 1);

        controlador.suscribirse(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            usuario = bundle.getParcelable(USUARIO_BUNDLE);
        }

        iniciaActividad();
    }

    private void iniciaActividad() {
        loadingDialog = new LoadingDialog(this);
        loadingDialog.show("Cargando recursos");

        actualizaFavoritos();
        inicializaFragmentos();
        aniadeListenerNavBar();

        binding.botonUsuario.setOnClickListener(view -> {
            Intent intent = new Intent(this, UserActivity.class);
            intent.putExtra(USUARIO_BUNDLE, usuario);
            startActivity(intent);
        });

        controlador.cargaRecurosApi();
    }

    private void inicializaFragmentos() {
        bibliotecaFragment = BibliotecaFragment.newInstance();
        favoritosFragment = FavoritosFragment.newInstance();
        fichasFragment = new FichasFragment();
    }

    private void aniadeListenerNavBar() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navBiblioteca) {
                binding.tituloFragment.setText("Biblioteca");
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
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(binding.frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    public void guardaFavorito(Favorito favorito) {
        if (!favoritos.contains(favorito)) {
            sqLiteHelper.iniciaConexion();
            sqLiteHelper.insert(favorito);
            favoritos.add(favorito);
            sqLiteHelper.stop();
        }
    }

    public void gestionaFavoritosClase(List<FavoritoClase> favoritosClase) {
        new Thread(() -> {
            sqLiteHelper.iniciaConexion();
            for (FavoritoClase favoritoClase : favoritosClase) {
                Favorito favorito = favoritoClase.getFavorito();

                if (favoritoClase.isFavorito() && !favoritos.contains(favorito)) {
                    sqLiteHelper.insert(favorito);
                    favoritos.add(favorito);

                } else if (!favoritoClase.isFavorito() && checkFavorito(favorito.getNombre())) {
                    sqLiteHelper.delete(favorito.getNombre());
                    favoritos.remove(favorito);
                }
            }
            sqLiteHelper.stop();
        }).start();
    }

    public List<Favorito> getFavoritos() {
        return favoritos;
    }

    public void eliminaFavorito(Favorito favorito) {
        if (checkFavorito(favorito.getNombre())) {
            favoritos.remove(favorito);
            sqLiteHelper.iniciaConexion();
            sqLiteHelper.delete(favorito.getNombre());
            sqLiteHelper.stop();
        }
    }

    private void actualizaFavoritos() {
        sqLiteHelper.iniciaConexion();
        favoritos = sqLiteHelper.selectAll();
        sqLiteHelper.stop();
    }

    public boolean checkFavorito(String nombre) {
        return favoritos.contains(new Favorito(nombre, ""));
    }

    @Override
    public void exitoObteniendoDatos() {
        loadingDialog.dismiss();
    }

    @Override
    public void falloObteniendoDatos() {
        loadingDialog.dismiss();
        Toast.makeText(this, "Error al cargar los recuros", Toast.LENGTH_SHORT).show();
    }
}