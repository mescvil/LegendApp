package com.hyperion.dndapiapp.servicioRest.calls;

import com.hyperion.dndapiapp.entidades.equipamiento.Hechizo;
import com.hyperion.dndapiapp.servicioRest.RespuestaApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallsEquipamiento {

    @GET("equipamiento/hechizos")
    Call<RespuestaApi<Hechizo>> getHechizos();

    @GET("equipamiento/hechizos/")
    Call<RespuestaApi<Hechizo>> getHechizosPaginacion(@Query("size") int tamanioPagina,
                                                      @Query("page") int pagina);
}
