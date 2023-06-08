package com.hyperion.dndapiapp.servicioRest.servicios;

import android.util.Log;

import androidx.annotation.NonNull;

import com.hyperion.dndapiapp.entidades.equipamiento.Arma;
import com.hyperion.dndapiapp.entidades.equipamiento.Armadura;
import com.hyperion.dndapiapp.entidades.equipamiento.Hechizo;
import com.hyperion.dndapiapp.servicioRest.RespuestaApi;
import com.hyperion.dndapiapp.servicioRest.RetrofitHelper;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackCustom;
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
                    Log.d("API", String.format("Hechizos obtenidos con exito [%d]", response.body().getnElementos()));
                    callback.exito(response.body().getResultado());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RespuestaApi<Hechizo>> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener los Hechizos :" + t);
                callback.fallo();
            }
        });
    }

    public void getAllHechizo(CallbackCustom<Hechizo> callback, String nombreHechizo) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<Hechizo> call = retrofitHelper.getCallsEquipamiento().getHechizo(nombreHechizo);

        call.enqueue(new Callback<Hechizo>() {
            @Override
            public void onResponse(@NonNull Call<Hechizo> call,
                                   @NonNull Response<Hechizo> response) {

                if (response.body() != null) {
                    Log.d("API", String.format("Hechizo obtenido con exito [%s]", nombreHechizo));
                    callback.exito(response.body());
                }
            }
            @Override
            public void onFailure(@NonNull Call<Hechizo> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener el hechizo");
                callback.fallo("Error al obtener el hechizo");
            }
        });
    }

    public void getAllArmas(CallbackLista<Arma> callback) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<Arma>> call = retrofitHelper.getCallsEquipamiento().getArmas();

        call.enqueue(new Callback<RespuestaApi<Arma>>() {
            @Override
            public void onResponse(@NonNull Call<RespuestaApi<Arma>> call,
                                   @NonNull Response<RespuestaApi<Arma>> response) {

                if (response.body() != null) {
                    Log.d("API", String.format("Armas obtenidas con exito [%d]", response.body().getnElementos()));
                    callback.exito(response.body().getResultado());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RespuestaApi<Arma>> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener las Armas :" + t);
                callback.fallo();
            }
        });
    }

    public void getAllArmaduras(CallbackLista<Armadura> callback) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<Armadura>> call = retrofitHelper.getCallsEquipamiento().getArmaduras();

        call.enqueue(new Callback<RespuestaApi<Armadura>>() {
            @Override
            public void onResponse(@NonNull Call<RespuestaApi<Armadura>> call,
                                   @NonNull Response<RespuestaApi<Armadura>> response) {

                if (response.body() != null) {
                    Log.d("API", String.format("Armaduras obtenidas con exito [%d]", response.body().getnElementos()));
                    callback.exito(response.body().getResultado());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RespuestaApi<Armadura>> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener las Armaduras :" + t);
                callback.fallo();
            }
        });
    }
}
