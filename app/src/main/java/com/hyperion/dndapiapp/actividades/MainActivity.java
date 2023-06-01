package com.hyperion.dndapiapp.actividades;

import static com.hyperion.dndapiapp.utilidades.Constantes.CIERRA_SESION;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.hyperion.dndapiapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.botonCierra).setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            Intent intent = new Intent(this, EmptyMainActivity.class);
            bundle.putBoolean(CIERRA_SESION, true);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}