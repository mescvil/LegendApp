package com.hyperion.dndapiapp.servicioRest.calls;

import com.hyperion.dndapiapp.entidades.fichas.PersonajeFicha;
import com.hyperion.dndapiapp.entidades.usuario.Usuario;
import com.hyperion.dndapiapp.servicioRest.servicios.utils.PayloadEliminarFicha;
import com.hyperion.dndapiapp.servicioRest.servicios.utils.PayloadFicha;
import com.hyperion.dndapiapp.servicioRest.servicios.utils.RespuestaApi;
import com.hyperion.dndapiapp.servicioRest.servicios.utils.RespuestaSimple;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CallsFichas {

    @POST("private/fichas")
    Call<RespuestaApi<PersonajeFicha>> getFichas(@Body Usuario usuario);

    @POST("private/fichas/registrar")
    Call<RespuestaSimple> postFicha(@Body PayloadFicha payload);

    @POST("private/fichas/borrar")
    Call<RespuestaSimple> eliminaFicha(@Body PayloadEliminarFicha payload);
}
