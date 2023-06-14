package com.hyperion.dndapiapp.servicioRest.servicios;

import android.util.Log;

import androidx.annotation.NonNull;

import com.hyperion.dndapiapp.entidades.glosario.enemigos.Enemigo;
import com.hyperion.dndapiapp.servicioRest.RetrofitHelper;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackCustom;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackLista;
import com.hyperion.dndapiapp.servicioRest.servicios.utils.RespuestaApi;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicioEnemigos {

    private static ServicioEnemigos servicio;

    public static ServicioEnemigos getInstance() {
        if (servicio == null)
            servicio = new ServicioEnemigos();

        return servicio;
    }

    public void getAllEnemigos(CallbackLista<Enemigo> callback) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<Enemigo>> call = retrofitHelper.getCallsEnemigos().getEnemigos();

        call.enqueue(new Callback<RespuestaApi<Enemigo>>() {
            @Override
            public void onResponse(@NonNull Call<RespuestaApi<Enemigo>> call,
                                   @NonNull Response<RespuestaApi<Enemigo>> response) {

                if (response.body() != null) {
                    Log.d("API", String.format("Enemigos obtenidos con exito [%d]", response.body().getnElementos()));
                    callback.exito(response.body().getResultado());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RespuestaApi<Enemigo>> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener los enemigos :" + t);
                callback.fallo();
            }
        });
    }

    public List<Enemigo> getAllEnemigosSync() throws IOException {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<Enemigo>> call = retrofitHelper.getCallsEnemigos().getEnemigos();
        Response<RespuestaApi<Enemigo>> response = call.execute();

        if (response.body() != null) {
            Log.d("API", String.format("Enemigos obtenidas con exito [%d]", response.body().getnElementos()));
            return response.body().getResultado();
        }

        return null;
    }

    public void getEnemigo(CallbackCustom<Enemigo> callback, String nombreEnemigo) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<Enemigo> call = retrofitHelper.getCallsEnemigos().getEnemigo(nombreEnemigo);

        call.enqueue(new Callback<Enemigo>() {
            @Override
            public void onResponse(@NonNull Call<Enemigo> call,
                                   @NonNull Response<Enemigo> response) {

                if (response.body() != null) {
                    Log.d("API", String.format("Enemigo obtenido con exito [%s]", nombreEnemigo));
                    callback.exito(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Enemigo> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener el enemigo");
                callback.fallo("Error al obtener el enemigo");
            }
        });
    }
}
