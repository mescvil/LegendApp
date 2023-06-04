package com.hyperion.dndapiapp.servicioRest.calls;

import com.hyperion.dndapiapp.entidades.razas.Raza;
import com.hyperion.dndapiapp.servicioRest.RespuestaApi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CallsRazas {

    @GET("razas")
    Call<RespuestaApi<Raza>> getRazas();
}
