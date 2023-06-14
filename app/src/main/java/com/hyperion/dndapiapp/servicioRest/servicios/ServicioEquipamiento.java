package com.hyperion.dndapiapp.servicioRest.servicios;

import android.util.Log;

import androidx.annotation.NonNull;

import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Arma;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Armadura;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Hechizo;
import com.hyperion.dndapiapp.servicioRest.RetrofitHelper;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackCustom;
import com.hyperion.dndapiapp.servicioRest.callbacks.CallbackLista;
import com.hyperion.dndapiapp.servicioRest.servicios.utils.RespuestaApi;

import java.io.IOException;
import java.util.List;

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

    public List<Hechizo> getAllHechizosSync() throws IOException {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<Hechizo>> call = retrofitHelper.getCallsEquipamiento().getHechizos();
        Response<RespuestaApi<Hechizo>> response = call.execute();

        if (response.body() != null) {
            Log.d("API", String.format("Hechizos obtenidos con exito [%d]", response.body().getnElementos()));
            return response.body().getResultado();
        }

        return null;
    }

    public void getHechizo(CallbackCustom<Hechizo> callback, String nombreHechizo) {
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

    public List<Arma> getAllArmasSync() throws IOException {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<Arma>> call = retrofitHelper.getCallsEquipamiento().getArmas();
        Response<RespuestaApi<Arma>> response = call.execute();

        if (response.body() != null) {
            Log.d("API", String.format("Armas obtenidas con exito [%d]", response.body().getnElementos()));
            return response.body().getResultado();
        }

        return null;
    }

    public void getArma(CallbackCustom<Arma> callback, String nombreArma) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<Arma> call = retrofitHelper.getCallsEquipamiento().getArma(nombreArma);

        call.enqueue(new Callback<Arma>() {
            @Override
            public void onResponse(@NonNull Call<Arma> call,
                                   @NonNull Response<Arma> response) {

                if (response.body() != null) {
                    Log.d("API", String.format("Arma obtenida con exito [%s]", nombreArma));
                    callback.exito(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Arma> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener el arma");
                callback.fallo("Error al obtener el hechizo");
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

    public List<Armadura> getAllArmadurasSync() throws IOException {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<RespuestaApi<Armadura>> call = retrofitHelper.getCallsEquipamiento().getArmaduras();
        Response<RespuestaApi<Armadura>> response = call.execute();

        if (response.body() != null) {
            Log.d("API", String.format("Armas obtenidas con exito [%d]", response.body().getnElementos()));
            return response.body().getResultado();
        }

        return null;
    }

    public void getArmadura(CallbackCustom<Armadura> callback, String nombreArmadura) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<Armadura> call = retrofitHelper.getCallsEquipamiento().getArmadura(nombreArmadura);

        call.enqueue(new Callback<Armadura>() {
            @Override
            public void onResponse(@NonNull Call<Armadura> call,
                                   @NonNull Response<Armadura> response) {

                if (response.body() != null) {
                    Log.d("API", String.format("Armadura obtenida con exito [%s]", nombreArmadura));
                    callback.exito(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Armadura> call,
                                  @NonNull Throwable t) {

                Log.d("API-ERROR", "No es posible obtener la armadura");
                callback.fallo("Error al obtener el hechizo");
            }
        });
    }
}
