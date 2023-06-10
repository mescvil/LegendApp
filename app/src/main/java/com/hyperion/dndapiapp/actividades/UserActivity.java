package com.hyperion.dndapiapp.actividades;

import static com.hyperion.dndapiapp.utilidades.Constantes.CIERRA_SESION_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.USUARIO_BUNDLE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.databinding.ActivityUserBinding;
import com.hyperion.dndapiapp.entidades.usuario.Usuario;
import com.hyperion.dndapiapp.utilidades.Seguridad;

public class UserActivity extends AppCompatActivity {

    private Usuario usuario;
    private Seguridad seguridad;
    private ActivityUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        seguridad = new Seguridad();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            usuario = bundle.getParcelable(USUARIO_BUNDLE);
        }

        iniciaActividad();
        iniciaListenersBotones();
    }

    @SuppressLint("DiscouragedApi")
    private void iniciaActividad() {
        binding.campoNombreUsuario.setText(usuario.getNombre());
        binding.campoCorreoUsuario.setText(usuario.getCorreo());
        try {
            binding.campoContraseniaUsuario.setText(seguridad.desencriptar(usuario.getContrasenia(), getString(R.string.encoder)));

        } catch (Exception e) {
            Toast.makeText(this, "No es posible desencriptar la contraseña", Toast.LENGTH_SHORT).show();
        }

        int resImage = getResources().getIdentifier(usuario.getImagenPerfil(), "drawable", getPackageName());
        binding.imagenPerfil.setImageResource(resImage);
    }

    private void iniciaListenersBotones() {
        binding.botonCerrarSesion.setOnClickListener(view -> {
            setResult(RESULT_OK);
            finish();
        });

        binding.botonAtrasUsuario.setOnClickListener(view -> {
            finish();
        });
    }
}