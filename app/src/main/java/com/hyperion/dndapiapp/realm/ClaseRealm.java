package com.hyperion.dndapiapp.realm;

import com.hyperion.dndapiapp.entidades.clases.Clase;

import java.util.List;

import io.realm.Realm;

public class ClaseRealm {

    public static void addClases(List<Clase> clases) {
        Realm db = Realm.getDefaultInstance();
        db.executeTransaction(realm -> {
            for (Clase clase : clases) {
                Clase c = realm.createObject(Clase.class, clase.getNombre());
                realm.insertOrUpdate(c);
            }
        });
    }
}
