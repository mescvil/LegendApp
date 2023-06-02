package com.hyperion.dndapiapp.servicioRest.calls;

import com.hyperion.dndapiapp.entidades.usuario.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CallsUsuario {

    @POST("private/login")
    Call<Usuario> doLogin(@Body Usuario usuario);

    @POST("private/register")
    Call<Object> registra(@Body Usuario usuario);
}
