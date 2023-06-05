package com.hyperion.dndapiapp.actividades;

import static com.hyperion.dndapiapp.utilidades.Constantes.ACTIVIDAD_REGISTER;
import static com.hyperion.dndapiapp.utilidades.Constantes.USUARIO_BUNDLE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.databinding.ActivityLoginBinding;
import com.hyperion.dndapiapp.dialogos.LoadingDialog;
import com.hyperion.dndapiapp.entidades.usuario.Usuario;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackCustom;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioUsuario;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private ServicioUsuario servicio;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        servicio = ServicioUsuario.getInstance();
        loadingDialog = new LoadingDialog(this);
        setListeners();
    }

    private void setListeners() {
        binding.botonLogin.setOnClickListener(view -> checkLogin());
        binding.botonRegistro.setOnClickListener(view -> startRegister());
    }

    private void startRegister() {
        Intent intentRegister = new Intent(this, RegisterActivity.class);
        startActivityForResult(intentRegister, ACTIVIDAD_REGISTER);
    }

    private void checkLogin() {
        String pass = binding.cajaContrasenia.getText().toString();
        String correo = binding.cajaCorreo.getText().toString();

        if (pass.isEmpty() || correo.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        loadingDialog.show("Comprobando datos");
        servicio.doLogin(new Usuario(correo, pass), getString(R.string.encoder), new CallbackCustom<Usuario>() {
            @Override
            public void exito(Usuario resultado) {
                loadingDialog.dismiss();

                Intent intentResultado = new Intent();
                intentResultado.putExtra(USUARIO_BUNDLE, resultado);
                setResult(Activity.RESULT_OK, intentResultado);
                finish();
            }

            @Override
            public void fallo(String mensaje) {
                loadingDialog.dismiss();
                Toast.makeText(LoginActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }
}