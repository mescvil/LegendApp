package com.hyperion.dndapiapp.actividades;

import static com.hyperion.dndapiapp.utilidades.Constantes.ACTIVIDAD_LOGIN;
import static com.hyperion.dndapiapp.utilidades.Constantes.CIERRA_SESION;
import static com.hyperion.dndapiapp.utilidades.Constantes.CORREO_USUARIO;
import static com.hyperion.dndapiapp.utilidades.Constantes.PASS_USUARIO;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.dialogos.LoadingDialog;
import com.hyperion.dndapiapp.entidades.usuario.Usuario;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackCustom;
import com.hyperion.dndapiapp.servicioRest.servicios.ServicioUsuario;

public class EmptyMainActivity extends AppCompatActivity {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editorShared;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Base_Theme_DnDAPIApp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_main);
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        editorShared = sharedPref.edit();
        loadingDialog = new LoadingDialog(this);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            if (sharedPref.contains(PASS_USUARIO) && sharedPref.contains(CORREO_USUARIO)) {
                autologin();
            } else {
                login();
            }
        } else {
            /* Version Xiaomi que son unos toca huevos */
            if (extras.containsKey(CIERRA_SESION)) {
                if (extras.getBoolean(CIERRA_SESION)) {
                    editorShared.clear();
                    editorShared.apply();
                    login();
                }
            } else {
                Log.d("XIAOMI", "Es un xiaomi y es un toca huevos");
                if (sharedPref.contains(PASS_USUARIO) && sharedPref.contains(CORREO_USUARIO)) {
                    autologin();
                } else {
                    login();
                }
            }
        }
    }

    private void login() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, ACTIVIDAD_LOGIN);
    }

    private void autologin() {
        Intent intent;
        String correo = sharedPref.getString(CORREO_USUARIO, "");
        String pass = sharedPref.getString(PASS_USUARIO, "");
        Usuario usuario = new Usuario(correo, pass);

        loadingDialog.show("Iniciando sesión");
        ServicioUsuario.getInstance().doLoginNoEncrypt(usuario, new CallbackCustom<Usuario>() {
            @Override
            public void exito(Usuario resultado) {
                loadingDialog.dismiss();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }

            @Override
            public void fallo(String mensaje) {
                Toast.makeText(EmptyMainActivity.this, "Error al iniciar sesión automaticamente", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
                login();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String correo;
        String pass;

        if (requestCode == ACTIVIDAD_LOGIN) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    correo = data.getStringExtra(CORREO_USUARIO);
                    pass = data.getStringExtra(PASS_USUARIO);

                    editorShared.putString(CORREO_USUARIO, correo);
                    editorShared.putString(PASS_USUARIO, pass);
                    editorShared.apply();

                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
            }
        }
    }
}