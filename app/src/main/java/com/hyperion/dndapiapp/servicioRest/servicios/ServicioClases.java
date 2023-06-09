package com.hyperion.dndapiapp.servicioRest.servicios;

import android.util.Log;

import androidx.annotation.NonNull;

import com.hyperion.dndapiapp.entidades.clases.Clase;
import com.hyperion.dndapiapp.entidades.equipamiento.Arma;
import com.hyperion.dndapiapp.servicioRest.RespuestaApi;
import com.hyperion.dndapiapp.servicioRest.RetrofitHelper;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackCustom;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackLista;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicioClases {

    private static ServicioClases servicio;

    public static ServicioClases getInstance() {
        if (servicio == null)
            servicio = new ServicioClases();

        return servicio;
    }

    public void getAllClases(CallbackLista<Clase> callback) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<Clase>> call = retrofitHelper.getCallsClases().getClases();

        call.enqueue(new Callback<RespuestaApi<Clase>>() {
            @Override
            public void onResponse(@NonNull Call<RespuestaApi<Clase>> call,
                                   @NonNull Response<RespuestaApi<Clase>> response) {

                if (response.body() != null) {
                    Log.d("API", String.format("Clases obtenidas con exito [%d]", response.body().getnElementos()));
                    callback.exito(response.body().getResultado());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RespuestaApi<Clase>> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener las clases :" + t);
                callback.fallo();
            }
        });
    }

    public void getClase(CallbackCustom<Clase> callback, String nombreClase) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<Clase> call = retrofitHelper.getCallsClases().getClase(nombreClase);

        call.enqueue(new Callback<Clase>() {
            @Override
            public void onResponse(@NonNull Call<Clase> call,
                                   @NonNull Response<Clase> response) {

                if (response.body() != null) {
                    Log.d("API", String.format("Clase obtenida con exito [%s]", nombreClase));
                    callback.exito(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Clase> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener la Clase");
                callback.fallo("Error al obtener la clase");
            }
        });
    }
}
