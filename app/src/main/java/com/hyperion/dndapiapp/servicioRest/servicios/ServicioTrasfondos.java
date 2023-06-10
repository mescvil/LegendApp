package com.hyperion.dndapiapp.servicioRest.servicios;

import android.util.Log;

import androidx.annotation.NonNull;

import com.hyperion.dndapiapp.entidades.trasfondos.Trasfondo;
import com.hyperion.dndapiapp.servicioRest.RespuestaApi;
import com.hyperion.dndapiapp.servicioRest.RetrofitHelper;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackCustom;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackLista;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicioTrasfondos {

    private static ServicioTrasfondos servicio;

    public static ServicioTrasfondos getInstance() {
        if (servicio == null)
            servicio = new ServicioTrasfondos();

        return servicio;
    }

    public void getAllTrasfondos(CallbackLista<Trasfondo> callback) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<Trasfondo>> call = retrofitHelper.getCallTrasfondos().getTrasfondos();

        call.enqueue(new Callback<RespuestaApi<Trasfondo>>() {
            @Override
            public void onResponse(@NonNull Call<RespuestaApi<Trasfondo>> call,
                                   @NonNull Response<RespuestaApi<Trasfondo>> response) {

                if (response.body() != null) {
                    Log.d("API", String.format("Trasfondos obtenidos con exito [%d]", response.body().getnElementos()));
                    callback.exito(response.body().getResultado());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RespuestaApi<Trasfondo>> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener los Trasfondos :" + t);
                callback.fallo();
            }
        });
    }

    public List<Trasfondo> getAllTrasfondosSync() throws IOException {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<Trasfondo>> call = retrofitHelper.getCallTrasfondos().getTrasfondos();
        Response<RespuestaApi<Trasfondo>> response = call.execute();

        if (response.body() != null) {
            Log.d("API", String.format("Trasfondos obtenidos con exito [%d]", response.body().getnElementos()));
            return response.body().getResultado();
        }

        return null;
    }

    public void getTrasfondo(CallbackCustom<Trasfondo> callback, String nombreTrasfondo) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<Trasfondo> call = retrofitHelper.getCallTrasfondos().getTrasfondo(nombreTrasfondo);

        call.enqueue(new Callback<Trasfondo>() {
            @Override
            public void onResponse(@NonNull Call<Trasfondo> call,
                                   @NonNull Response<Trasfondo> response) {

                if (response.body() != null) {
                    Log.d("API", String.format("Trasfondo obtenido con exito [%s]", nombreTrasfondo));
                    callback.exito(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Trasfondo> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener el Trasfondo");
                callback.fallo("Error al obtener la clase");
            }
        });
    }
}
