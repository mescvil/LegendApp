package com.hyperion.dndapiapp.servicioRest.servicios;

import android.util.Log;

import androidx.annotation.NonNull;

import com.hyperion.dndapiapp.entidades.fichas.PersonajeFicha;
import com.hyperion.dndapiapp.entidades.usuario.Usuario;
import com.hyperion.dndapiapp.servicioRest.RetrofitHelper;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackCustom;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackLista;
import com.hyperion.dndapiapp.servicioRest.servicios.utils.PayloadFicha;
import com.hyperion.dndapiapp.servicioRest.servicios.utils.RespuestaApi;
import com.hyperion.dndapiapp.servicioRest.servicios.utils.RespuestaSimple;

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

    public void posteaFicha(CallbackCustom<Boolean> callback, Usuario usuario, PersonajeFicha ficha) {
        PayloadFicha payload = new PayloadFicha(usuario.getCorreo(), ficha);
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaSimple> call = retrofitHelper.getCallFichas().postFicha(payload);

        call.enqueue(new Callback<RespuestaSimple>() {
            @Override
            public void onResponse(@NonNull Call<RespuestaSimple> call,
                                   @NonNull Response<RespuestaSimple> response) {

                if (response.code() == 200) {
                    if (response.body() != null) {
                        if (response.body().getCodigo().equals("201")) {
                            Log.d("API", "Ficha guardada para el usuario " + usuario.getCorreo());
                            callback.exito(true);
                        }
                    }

                } else {
                    Log.d("API", response.toString());
                    callback.fallo("Error al guardar la ficha");
                }
            }

            @Override
            public void onFailure(@NonNull Call<RespuestaSimple> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener las fichas :" + t);
                callback.fallo("Algo salio mal");
            }
        });

    }
}
