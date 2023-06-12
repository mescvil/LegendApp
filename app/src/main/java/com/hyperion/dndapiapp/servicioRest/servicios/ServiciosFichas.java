package com.hyperion.dndapiapp.servicioRest.servicios;

import android.util.Log;

import androidx.annotation.NonNull;

import com.hyperion.dndapiapp.entidades.fichas.PersonajeFicha;
import com.hyperion.dndapiapp.entidades.glosario.trasfondos.Trasfondo;
import com.hyperion.dndapiapp.entidades.usuario.Usuario;
import com.hyperion.dndapiapp.servicioRest.RespuestaApi;
import com.hyperion.dndapiapp.servicioRest.RetrofitHelper;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackLista;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiciosFichas {

    private static ServiciosFichas servicio;

    public static ServiciosFichas getInstance() {
        if (servicio == null) {
            servicio = new ServiciosFichas();
        }
        return servicio;
    }

    public void getAllFichasUsuario(CallbackLista<PersonajeFicha> callback, Usuario usuario) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<PersonajeFicha>> call = retrofitHelper.getCallFichas().getFichas(usuario);

        call.enqueue(new Callback<RespuestaApi<PersonajeFicha>>() {
            @Override
            public void onResponse(@NonNull Call<RespuestaApi<PersonajeFicha>> call,
                                   @NonNull Response<RespuestaApi<PersonajeFicha>> response) {

                if (response.body() != null) {
                    Log.d("API", String.format("Fichas obtenidas con exito [%d]", response.body().getnElementos()));
                    callback.exito(response.body().getResultado());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RespuestaApi<PersonajeFicha>> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener las fichas :" + t);
                callback.fallo();
            }
        });
    }
}
