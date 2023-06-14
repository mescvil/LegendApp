package com.hyperion.dndapiapp.servicioRest.calls;

import com.hyperion.dndapiapp.entidades.glosario.razas.Raza;
import com.hyperion.dndapiapp.servicioRest.servicios.utils.RespuestaApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CallsRazas {

    @GET("razas")
    Call<RespuestaApi<Raza>> getRazas();

    @GET("razas/{raza}")
    Call<Raza> getRaza(@Path("raza") String nombre);
}
