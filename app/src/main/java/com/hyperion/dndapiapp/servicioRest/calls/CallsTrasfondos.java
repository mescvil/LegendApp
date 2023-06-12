package com.hyperion.dndapiapp.servicioRest.calls;

import com.hyperion.dndapiapp.entidades.glosario.trasfondos.Trasfondo;
import com.hyperion.dndapiapp.servicioRest.RespuestaApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CallsTrasfondos {

    @GET("trasfondos")
    Call<RespuestaApi<Trasfondo>> getTrasfondos();

    @GET("trasfondos/{trasfondo}")
    Call<Trasfondo> getTrasfondo(@Path("trasfondo") String nombre);
}
