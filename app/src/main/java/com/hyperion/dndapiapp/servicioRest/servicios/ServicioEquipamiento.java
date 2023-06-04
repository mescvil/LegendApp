package com.hyperion.dndapiapp.servicioRest.servicios;

import android.util.Log;

import androidx.annotation.NonNull;

import com.hyperion.dndapiapp.entidades.clases.Clase;
import com.hyperion.dndapiapp.entidades.equipamiento.Hechizo;
import com.hyperion.dndapiapp.servicioRest.RespuestaApi;
import com.hyperion.dndapiapp.servicioRest.RetrofitHelper;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackLista;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicioEquipamiento {

    private static ServicioEquipamiento servicio;

    public static ServicioEquipamiento getInstance() {
        if (servicio == null)
            servicio = new ServicioEquipamiento();

        return servicio;
    }

    public void getAllHechizos(CallbackLista<Hechizo> callback) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<Hechizo>> call = retrofitHelper.getCallsEquipamiento().getHechizos();

        call.enqueue(new Callback<RespuestaApi<Hechizo>>() {
            @Override
            public void onResponse(@NonNull Call<RespuestaApi<Hechizo>> call,
                                   @NonNull Response<RespuestaApi<Hechizo>> response) {

                if (response.body() != null) {
                    Log.d("REST-HECHIZOS", String.format("Hechizos obtenidos con exito [%d]", response.body().getnElementos()));
                    callback.exito(response.body().getResultado());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RespuestaApi<Hechizo>> call,
                                  @NonNull Throwable t) {

                Log.d("REST-HECHIZOS", "No es posible obtener los Hechizos :" + t);
                callback.fallo();
            }
        });
    }
}
