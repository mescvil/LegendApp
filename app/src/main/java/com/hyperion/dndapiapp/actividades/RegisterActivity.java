package com.hyperion.dndapiapp.actividades;

import static com.hyperion.dndapiapp.utilidades.Constantes.ACTIVIDAD_IMAGEN;
import static com.hyperion.dndapiapp.utilidades.Constantes.IMAGEN_USUARIO_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Utils.verifcaContrasenia;
import static com.hyperion.dndapiapp.utilidades.Utils.verificaCorreo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.databinding.ActivityRegisterBinding;
import com.hyperion.dndapiapp.entidades.usuario.Usuario;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackCustom;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioUsuario;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private ServicioUsuario servicio;
    private boolean imagenDefecto = true;
    private String imagenPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        servicio = ServicioUsuario.getInstance();
        setListeners();
    }

    private void setListeners() {
        binding.botonAtras.setOnClickListener(v -> finish());
        binding.botonRegistrador.setOnClickListener(v -> registraUsuario());
        binding.imagenRegistroUsuario.setOnClickListener(v -> abrirSelectorImagen());
    }

    private void abrirSelectorImagen() {
        Intent intent = new Intent(this, ImageSelectorActivity.class);
        startActivityForResult(intent, ACTIVIDAD_IMAGEN);
    }

    private void registraUsuario() {
        Usuario usuario = compruebaCampos();
        if (usuario != null)
            servicio.registraUsuario(usuario, getString(R.string.encoder), new CallbackCustom<Boolean>() {
                @Override
                public void exito(Boolean resultado) {
                    Toast.makeText(RegisterActivity.this, "Registrado con éxito, puede iniciar sesión", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void fallo(String mensaje) {
                    Toast.makeText(RegisterActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                }
            });
    }

    private Usuario compruebaCampos() {
        String nombreUsuario = binding.campoNombreRegistro.getText().toString();
        String contrasenia1 = binding.campoPass1.getText().toString();
        String contrasenia2 = binding.campoPass2.getText().toString();
        String email = binding.campoCorreoRegistro.getText().toString();
        boolean check = binding.checkBoxInutil.isChecked();

        if (nombreUsuario.isEmpty()) {
            Toast.makeText(this, "Introduce un nombre de usuario", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (!verificaCorreo(email)) {
            Toast.makeText(this, "Correo no válido", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (!verifcaContrasenia(contrasenia1)) {
            Toast.makeText(this, "La contraseña no cumple los requisitos", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (!contrasenia1.equals(contrasenia2)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (imagenDefecto) {
            Toast.makeText(this, "Selecciona una imagen de pefil", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (!check) {
            Toast.makeText(this, "Acepta los terminos y condiciones", Toast.LENGTH_SHORT).show();
            return null;
        }

        return new Usuario(nombreUsuario, email, contrasenia1, imagenPerfil);
    }

    @Override
    @SuppressLint("DiscouragedApi")
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVIDAD_IMAGEN) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    imagenPerfil = data.getStringExtra(IMAGEN_USUARIO_BUNDLE);
                    int resImage = getResources().getIdentifier(imagenPerfil, "drawable", getPackageName());
                    binding.imagenRegistroUsuario.setImageResource(resImage);
                    imagenDefecto = false;
                }
            }
        }
    }
}