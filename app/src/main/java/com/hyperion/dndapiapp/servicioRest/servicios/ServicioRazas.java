package com.hyperion.dndapiapp.servicioRest.servicios;

import android.util.Log;

import androidx.annotation.NonNull;

import com.hyperion.dndapiapp.entidades.razas.Raza;
import com.hyperion.dndapiapp.servicioRest.RespuestaApi;
import com.hyperion.dndapiapp.servicioRest.RetrofitHelper;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackLista;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicioRazas {

    private static ServicioRazas servicio;

    public static ServicioRazas getInstance() {
        if (servicio == null)
            servicio = new ServicioRazas();

        return servicio;
    }

    public void getAllRazas(CallbackLista<Raza> callback) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<Raza>> call = retrofitHelper.getCallRazas().getRazas();

        call.enqueue(new Callback<RespuestaApi<Raza>>() {
            @Override
            public void onResponse(@NonNull Call<RespuestaApi<Raza>> call,
                                   @NonNull Response<RespuestaApi<Raza>> response) {

                if (response.body() != null) {
                    Log.d("API", String.format("Razas obtenidas con exito [%d]", response.body().getnElementos()));
                    callback.exito(response.body().getResultado());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RespuestaApi<Raza>> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener las Razas :" + t);
                callback.fallo();
            }
        });
    }
}
