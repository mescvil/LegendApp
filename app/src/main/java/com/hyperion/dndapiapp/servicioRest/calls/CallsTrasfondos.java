package com.hyperion.dndapiapp.servicioRest.calls;

import com.hyperion.dndapiapp.entidades.trasfondos.Trasfondo;
import com.hyperion.dndapiapp.servicioRest.RespuestaApi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CallsTrasfondos {

    @GET("trasfondos")
    Call<RespuestaApi<Trasfondo>> getTrasfondos();
}
