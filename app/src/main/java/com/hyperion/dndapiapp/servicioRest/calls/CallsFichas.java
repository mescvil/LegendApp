package com.hyperion.dndapiapp.servicioRest.calls;

import com.hyperion.dndapiapp.entidades.fichas.PersonajeFicha;
import com.hyperion.dndapiapp.entidades.usuario.Usuario;
import com.hyperion.dndapiapp.servicioRest.RespuestaApi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CallsFichas {

    @POST("fichas/personajes")
    Call<RespuestaApi<PersonajeFicha>> getFichas(@Body Usuario usuario);
}
