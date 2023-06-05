package com.hyperion.dndapiapp.actividades;

import static com.hyperion.dndapiapp.utilidades.Constantes.CIERRA_SESION_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.USUARIO_BUNDLE;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hyperion.dndapiapp.databinding.ActivityUserBinding;
import com.hyperion.dndapiapp.entidades.usuario.Usuario;

public class UserActivity extends AppCompatActivity {

    private Usuario usuario;
    private ActivityUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            usuario = bundle.getParcelable(USUARIO_BUNDLE);
        }

        iniciaActividad();
        iniciaListenersBotones();
    }

    private void iniciaActividad() {
        binding.campoNombreUsuario.setText(usuario.getNombre());
    }

    private void iniciaListenersBotones() {
        binding.botonCerrarSesion.setOnClickListener(view -> {
            Intent intent = new Intent(this, EmptyMainActivity.class);
            intent.putExtra(CIERRA_SESION_BUNDLE, true);
            startActivity(intent);
            finish();
        });
    }
}