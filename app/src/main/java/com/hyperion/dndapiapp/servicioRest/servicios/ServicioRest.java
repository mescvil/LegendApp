package com.hyperion.dndapiapp.servicioRest.servicios;

import android.util.Log;

import androidx.annotation.NonNull;

import com.hyperion.dndapiapp.entidades.enemigos.Enemigo;
import com.hyperion.dndapiapp.servicioRest.RespuestaApi;
import com.hyperion.dndapiapp.servicioRest.RetrofitHelper;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackLista;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicioRest {

    public void getAllEnemigos(CallbackLista<Enemigo> callback) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<Enemigo>> call = retrofitHelper.getCallsEnemigos().getEnemigos();

        call.enqueue(new Callback<RespuestaApi<Enemigo>>() {
            @Override
            public void onResponse(@NonNull Call<RespuestaApi<Enemigo>> call,
                                   @NonNull Response<RespuestaApi<Enemigo>> response) {

                if (response.body() != null) {
                    Log.d("REST-ENEMIGOS", String.format("Enemigos obtenidos con exito [%d]", response.body().getnElementos()));
                    callback.exito(response.body().getResultado());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RespuestaApi<Enemigo>> call,
                                  @NonNull Throwable t) {

                Log.d("REST-ENEMIGOS", "No es posible obtener los enemigos :" + t);
                callback.fallo();
            }
        });
    }
}
