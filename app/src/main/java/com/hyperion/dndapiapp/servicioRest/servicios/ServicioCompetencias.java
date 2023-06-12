package com.hyperion.dndapiapp.servicioRest.servicios;

import android.util.Log;

import androidx.annotation.NonNull;

import com.hyperion.dndapiapp.entidades.glosario.competencias.Competencia;
import com.hyperion.dndapiapp.servicioRest.RespuestaApi;
import com.hyperion.dndapiapp.servicioRest.RetrofitHelper;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackLista;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicioCompetencias {

    private static ServicioCompetencias servicio;

    public static ServicioCompetencias getInstance() {
        if (servicio == null)
            servicio = new ServicioCompetencias();

        return servicio;
    }

    public void getAllCompetencias(CallbackLista<Competencia> callback) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<Competencia>> call = retrofitHelper.getCallCompetencias().getCompetencias();

        call.enqueue(new Callback<RespuestaApi<Competencia>>() {
            @Override
            public void onResponse(@NonNull Call<RespuestaApi<Competencia>> call,
                                   @NonNull Response<RespuestaApi<Competencia>> response) {

                if (response.body() != null) {
                    Log.d("API", String.format("Compentencias obtenidas con exito [%d]", response.body().getnElementos()));
                    callback.exito(response.body().getResultado());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RespuestaApi<Competencia>> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener las Competencias :" + t);
                callback.fallo();
            }
        });
    }

    public List<Competencia> getAllCompetenciasSync() throws IOException {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<Competencia>> call = retrofitHelper.getCallCompetencias().getCompetencias();
        Response<RespuestaApi<Competencia>> response = call.execute();

        if (response.body() != null) {
            Log.d("API", String.format("Competencias obtenidas con exito [%d]", response.body().getnElementos()));
            return response.body().getResultado();
        }

        return null;
    }
}
