package com.hyperion.dndapiapp.servicioRest.servicios;

import android.util.Log;

import androidx.annotation.NonNull;

import com.hyperion.dndapiapp.entidades.glosario.razas.Raza;
import com.hyperion.dndapiapp.servicioRest.RespuestaApi;
import com.hyperion.dndapiapp.servicioRest.RetrofitHelper;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackCustom;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackLista;

import java.io.IOException;
import java.util.List;

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

    public List<Raza> getAllRazasSync() throws IOException {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<Raza>> call = retrofitHelper.getCallRazas().getRazas();
        Response<RespuestaApi<Raza>> response = call.execute();

        if (response.body() != null) {
            Log.d("API", String.format("Razas obtenidas con exito [%d]", response.body().getnElementos()));
            return response.body().getResultado();
        }

        return null;
    }

    public void getRaza(CallbackCustom<Raza> callback, String nombreRaza) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<Raza> call = retrofitHelper.getCallRazas().getRaza(nombreRaza);

        call.enqueue(new Callback<Raza>() {
            @Override
            public void onResponse(@NonNull Call<Raza> call,
                                   @NonNull Response<Raza> response) {

                if (response.body() != null) {
                    Log.d("API", String.format("Raza obtenida con exito [%s]", nombreRaza));
                    callback.exito(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Raza> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener la Raza");
                callback.fallo("Error al obtener la clase");
            }
        });
    }
}
