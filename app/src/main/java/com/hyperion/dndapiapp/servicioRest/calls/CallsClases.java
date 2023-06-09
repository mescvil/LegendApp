package com.hyperion.dndapiapp.servicioRest.calls;

import com.hyperion.dndapiapp.entidades.glosario.clases.Clase;
import com.hyperion.dndapiapp.servicioRest.servicios.utils.RespuestaApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CallsClases {

    @GET("clases")
    Call<RespuestaApi<Clase>> getClases();

    @GET("clases/{clase}")
    Call<Clase> getClase(@Path("clase") String nombre);
}
