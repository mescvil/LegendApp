package com.hyperion.dndapiapp.servicioRest;

import com.hyperion.dndapiapp.servicioRest.calls.CallsClases;
import com.hyperion.dndapiapp.servicioRest.calls.CallsCompetencias;
import com.hyperion.dndapiapp.servicioRest.calls.CallsEnemigos;
import com.hyperion.dndapiapp.servicioRest.calls.CallsEquipamiento;
import com.hyperion.dndapiapp.servicioRest.calls.CallsRazas;
import com.hyperion.dndapiapp.servicioRest.calls.CallsTrasfondos;
import com.hyperion.dndapiapp.servicioRest.calls.CallsUsuario;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static RetrofitHelper retrofitHelper;
    private final Retrofit retrofit;

    private RetrofitHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.33/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitHelper getInstance() {
        if (retrofitHelper == null) {
            retrofitHelper = new RetrofitHelper();
        }
        return retrofitHelper;
    }

    public CallsEnemigos getCallsEnemigos() {
        return retrofit.create(CallsEnemigos.class);
    }

    public CallsEquipamiento getCallsEquipamiento() {
        return retrofit.create(CallsEquipamiento.class);
    }

    public CallsClases getCallsClases() {
        return retrofit.create(CallsClases.class);
    }

    public CallsUsuario getCallsUsuario() {
        return retrofit.create(CallsUsuario.class);
    }

    public CallsRazas getCallRazas() {
        return retrofit.create(CallsRazas.class);
    }

    public CallsTrasfondos getCallTrasfondos() {
        return retrofit.create(CallsTrasfondos.class);
    }

    public CallsCompetencias getCallCompetencias(){
        return retrofit.create(CallsCompetencias.class);
    }
}
