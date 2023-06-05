package com.hyperion.dndapiapp.actividades;

import static com.hyperion.dndapiapp.utilidades.Constantes.ACTIVIDAD_LOGIN;
import static com.hyperion.dndapiapp.utilidades.Constantes.CIERRA_SESION_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.CORREO_USUARIO_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.PASS_USUARIO_BUNDLE;
import static com.hyperion.dndapiapp.utilidades.Constantes.USUARIO_BUNDLE;

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
            if (sharedPref.contains(PASS_USUARIO_BUNDLE) && sharedPref.contains(CORREO_USUARIO_BUNDLE)) {
                autologin();
            } else {
                login();
            }
        } else {
            /* Version Xiaomi que son unos toca huevos */
            if (extras.containsKey(CIERRA_SESION_BUNDLE)) {
                if (extras.getBoolean(CIERRA_SESION_BUNDLE)) {
                    editorShared.clear();
                    editorShared.apply();
                    login();
                }
            } else {
                Log.d("XIAOMI", "Es un xiaomi y es un toca huevos");
                if (sharedPref.contains(PASS_USUARIO_BUNDLE) && sharedPref.contains(CORREO_USUARIO_BUNDLE)) {
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
        String correo = sharedPref.getString(CORREO_USUARIO_BUNDLE, "");
        String pass = sharedPref.getString(PASS_USUARIO_BUNDLE, "");
        Usuario usuario = new Usuario(correo, pass);

        loadingDialog.show("Iniciando sesión");
        ServicioUsuario.getInstance().doLoginNoEncrypt(usuario, new CallbackCustom<Usuario>() {
            @Override
            public void exito(Usuario resultado) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(USUARIO_BUNDLE, resultado);

                loadingDialog.dismiss();
                startActivity(intent);
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

        if (requestCode == ACTIVIDAD_LOGIN) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Usuario usuario = data.getExtras().getParcelable(USUARIO_BUNDLE);

                    editorShared.putString(CORREO_USUARIO_BUNDLE, usuario.getCorreo());
                    editorShared.putString(PASS_USUARIO_BUNDLE, usuario.getContrasenia());
                    editorShared.apply();

                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra(USUARIO_BUNDLE, usuario);

                    startActivity(intent);
                    finish();
                }
            }
        }
    }
}