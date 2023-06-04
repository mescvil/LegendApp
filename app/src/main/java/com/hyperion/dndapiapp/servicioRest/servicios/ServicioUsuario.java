package com.hyperion.dndapiapp.servicioRest.servicios;

import android.util.Log;

import androidx.annotation.NonNull;

import com.hyperion.dndapiapp.entidades.usuario.Usuario;
import com.hyperion.dndapiapp.servicioRest.RetrofitHelper;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackCustom;
import com.hyperion.dndapiapp.utilidades.Seguridad;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicioUsuario {

    private static ServicioUsuario servicio;
    private static Seguridad seguridad;

    public static ServicioUsuario getInstance() {
        if (servicio == null) {
            servicio = new ServicioUsuario();
            seguridad = new Seguridad();
        }
        return servicio;
    }

    public void doLogin(Usuario usuarioLogin, String encoder, CallbackCustom<Usuario> callback) {

        try {
            usuarioLogin.setContrasenia(seguridad.encriptar(usuarioLogin.getContrasenia(), encoder));
        } catch (Exception e) {
            Log.d("LOGIN-USUARIO", "Fallo al encriptar la contraseña");
            callback.fallo("Fallo al encriptar la contraseña");
        }

        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<Usuario> call = retrofitHelper.getCallsUsuario().doLogin(usuarioLogin);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(@NonNull Call<Usuario> call,
                                   @NonNull Response<Usuario> response) {

                if (response.body() != null) {
                    Log.d("LOGIN-USUARIO", String.format("Login realizado con exito [%s]", response.body().getCorreo()));
                    callback.exito(response.body());

                } else {
                    callback.fallo("Error en el usuario o contraseña");
                    Log.d("LOGIN-USUARIO", String.format("Login fallido codigo: %s", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call,
                                  @NonNull Throwable t) {

                Log.d("LOGIN-USUARIO", String.format("Login fallido -> %s", t.getMessage()));
                callback.fallo("Error en el login");
            }
        });
    }

    public void doLoginNoEncrypt(Usuario usuarioLogin, CallbackCustom<Usuario> callback) {

        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<Usuario> call = retrofitHelper.getCallsUsuario().doLogin(usuarioLogin);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(@NonNull Call<Usuario> call,
                                   @NonNull Response<Usuario> response) {

                if (response.body() != null) {
                    Log.d("LOGIN-USUARIO", String.format("Login realizado con exito [%s]", response.body().getCorreo()));
                    callback.exito(response.body());

                } else {
                    callback.fallo("Error en el usuario o contraseña");
                    Log.d("LOGIN-USUARIO", String.format("Login fallido codigo: %s", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call,
                                  @NonNull Throwable t) {

                Log.d("LOGIN-USUARIO", String.format("Login fallido -> %s", t.getMessage()));
                callback.fallo("Fallo en el login");
            }
        });
    }

    public void registraUsuario(Usuario usuario, String encoder, CallbackCustom<Boolean> callback) {

        try {
            usuario.setContrasenia(seguridad.encriptar(usuario.getContrasenia(), encoder));
        } catch (Exception e) {
            Log.d("LOGIN-USUARIO", "Fallo al encriptar la contraseña");
            callback.fallo("Fallo al encriptar la contraseña");
        }

        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<Object> call = retrofitHelper.getCallsUsuario().registra(usuario);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NonNull Call<Object> call,
                                   @NonNull Response<Object> response) {

                if (response.code() == 201) {
                    Log.d("REGISTRO-USUARIO", String.format("Registro realizado con exito [%s]", usuario.getCorreo()));
                    callback.exito(true);
                    return;
                }

                if (response.code() == 400) {
                    Log.d("REGISTRO-USUARIO", "Registro fallido [Usuario duplicado]");
                    callback.fallo("Usuario ya registrado en el sistema");
                    return;
                }

                Log.d("REGISTRO-USUARIO", "Registro fallido no se sabe porque");
                callback.fallo("Fallo en el registro");
            }

            @Override
            public void onFailure(@NonNull Call<Object> call,
                                  @NonNull Throwable t) {

                Log.d("REGISTRO-USUARIO", String.format("Registro fallido -> %s", t.getMessage()));
                callback.fallo("Fallo en el registro");
            }
        });
    }

}
