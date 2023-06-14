package com.hyperion.dndapiapp.servicioRest.calls;

import com.hyperion.dndapiapp.entidades.glosario.enemigos.Enemigo;
import com.hyperion.dndapiapp.servicioRest.servicios.utils.RespuestaApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CallsEnemigos {

    @GET("enemigos")
    Call<RespuestaApi<Enemigo>> getEnemigos();

    @GET("enemigos/")
    Call<RespuestaApi<Enemigo>> getEnemigosPaginacion(@Query("size") int tamanioPagina,
                                                      @Query("page") int pagina);

    @GET("enemigos/{enemigo}")
    Call<Enemigo> getEnemigo(@Path("enemigo") String nombre);
}
