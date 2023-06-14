package com.hyperion.dndapiapp.servicioRest.calls;

import com.hyperion.dndapiapp.entidades.glosario.competencias.Competencia;
import com.hyperion.dndapiapp.servicioRest.servicios.utils.RespuestaApi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CallsCompetencias {

    @GET("competencias")
    Call<RespuestaApi<Competencia>> getCompetencias();
}
