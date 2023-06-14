package com.hyperion.dndapiapp.actividades;

import static com.hyperion.dndapiapp.utilidades.Constantes.FICHA_BUNDLE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.hyperion.dndapiapp.R;
import com.hyperion.dndapiapp.controladores.Controlador;
import com.hyperion.dndapiapp.databinding.ActivityNewPersonajeBinding;
import com.hyperion.dndapiapp.entidades.fichas.ClasePersonaje;
import com.hyperion.dndapiapp.entidades.fichas.EquipamientoPersonaje;
import com.hyperion.dndapiapp.entidades.fichas.HistoriaPersonaje;
import com.hyperion.dndapiapp.entidades.fichas.PersonajeFicha;
import com.hyperion.dndapiapp.entidades.fichas.RazaPersonaje;
import com.hyperion.dndapiapp.entidades.glosario.Alineamientos;
import com.hyperion.dndapiapp.entidades.glosario.clases.Clase;
import com.hyperion.dndapiapp.entidades.glosario.clases.Especialidad;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Arma;
import com.hyperion.dndapiapp.entidades.glosario.equipamiento.Armadura;
import com.hyperion.dndapiapp.entidades.glosario.razas.Raza;
import com.hyperion.dndapiapp.entidades.glosario.trasfondos.Trasfondo;
import com.hyperion.dndapiapp.utilidades.Utils;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("DefaultLocale")
public class NewPersonajeActivity extends AppCompatActivity {

    private ActivityNewPersonajeBinding binding;
    private Controlador controlador;

    private List<Clase> listaClases;
    private List<Raza> listaRazas;
    private List<Trasfondo> listaTrasfondos;
    private int nIntentos;

    /* Componentes */
    private EditText nombre;
    private Spinner spinerRaza;
    private Spinner spinerClase;
    private Spinner spinnerEspecialidad;
    private Spinner spinnerTrasfondo;
    private Spinner spinnerAlineamiento;
    private Spinner spinnerArmas;
    private Spinner spinnerArmaduras;

    private TextView maxEdad;
    private EditText edad;
    private TextView minMaxTamanio;
    private EditText tamanio;

    private TextView fue;
    private TextView des;
    private TextView con;
    private TextView inti;
    private TextView sab;
    private TextView car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewPersonajeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controlador = Controlador.getInstance();

        iniciaListas();
        iniciaActividad();
        listenerBotones();
    }

    private void listenerBotones() {
        binding.botonAtrasNuevaFicha.setOnClickListener(view -> finish());

        binding.botonRoll.setOnClickListener(view -> {
            if (nIntentos > 0) {
                int[] dados = Utils.tiraDados();

                fue.setText(String.valueOf(dados[0]));
                des.setText(String.valueOf(dados[1]));
                con.setText(String.valueOf(dados[2]));
                inti.setText(String.valueOf(dados[3]));
                sab.setText(String.valueOf(dados[4]));
                car.setText(String.valueOf(dados[5]));

                cambiaColorStats();

                nIntentos--;
                Toast.makeText(this, "Te quedan " + nIntentos + " tiradas", Toast.LENGTH_SHORT).show();

                if (nIntentos == 0) {
                    binding.botonRoll.setEnabled(false);
                    binding.botonRoll.setBackgroundColor(getColor(R.color.hint));
                }
            }
        });

        binding.actionButtonGuardar.setOnClickListener(view -> {
            if (nIntentos > 0)
                creaDialoIntentos();
            else
                generaFicha();
        });
    }

    private void cambiaColorStats() {
        ConstraintLayout layout = binding.cajaStats;

        for (int i = 0; i < layout.getChildCount(); i++) {
            TextView caja = (TextView) layout.getChildAt(i);
            String texto = caja.getText().toString();

            if (Utils.esNumerico(texto)) {
                int n = Integer.parseInt(texto);

                if (n == 18 || n == 4 || n == 3)
                    caja.setTextColor(getColor(R.color.doradoMetalico));
                else if (n >= 15)
                    caja.setTextColor(getColor(R.color.verdeCriatura));
                else if (n < 10)
                    caja.setTextColor(getColor(R.color.rojo));
                else
                    caja.setTextColor(getColor(R.color.white));
            }
        }
    }

    private void creaDialoIntentos() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.DialogoFiltrosTheme);
        alertDialogBuilder.setMessage("Aún te quedan tiradas, ¿Continuar igualmente?");

        alertDialogBuilder.setPositiveButton("Si", (arg0, arg1) -> generaFicha());

        alertDialogBuilder.setNegativeButton("No", (dialog, which) -> {
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void generaFicha() {
        if (nombre.getText().toString().isEmpty()
                || edad.getText().toString().isEmpty()
                || tamanio.getText().toString().isEmpty()) {

            Toast.makeText(this, "Rellena todos los campos pls =)", Toast.LENGTH_SHORT).show();
            return;
        }

        Clase clase = (Clase) spinerClase.getSelectedItem();
        Especialidad especialidad = (Especialidad) spinnerEspecialidad.getSelectedItem();
        Trasfondo trasfondo = (Trasfondo) spinnerTrasfondo.getSelectedItem();
        Raza raza = (Raza) spinerRaza.getSelectedItem();
        String alineamiento = (String) spinnerAlineamiento.getSelectedItem();
        Arma arma = (Arma) spinnerArmas.getSelectedItem();
        Armadura armadura = (Armadura) spinnerArmaduras.getSelectedItem();

        int edadPersonaje = Integer.parseInt(edad.getText().toString());
        float tamanioPersonaje = Float.parseFloat(tamanio.getText().toString());

        if (edadPersonaje > raza.getEdadMaxima() || edadPersonaje < 18) {
            Toast.makeText(this, "Introduce una edad válida", Toast.LENGTH_SHORT).show();
            return;
        }

        if (tamanioPersonaje > raza.getAlturaMaxima() || tamanioPersonaje < raza.getAlturaMinima()) {
            Toast.makeText(this, "Introduce un tamaño válido", Toast.LENGTH_SHORT).show();
            return;
        }


        EquipamientoPersonaje equipamientoPersonaje = new EquipamientoPersonaje(clase, arma, armadura);
        ClasePersonaje clasePersonaje = new ClasePersonaje(clase, especialidad.getNombre());
        HistoriaPersonaje historiaPersonaje = new HistoriaPersonaje(trasfondo, clase);
        RazaPersonaje razaPersonaje = new RazaPersonaje(raza);
        PersonajeFicha ficha = new PersonajeFicha();

        ficha.setNombre(nombre.getText().toString());
        ficha.setEdad(edadPersonaje);
        ficha.setAlineamiento(alineamiento);
        ficha.setTamanio(tamanioPersonaje);

        ficha.setFuerza(Integer.parseInt(fue.getText().toString()));
        ficha.setDestreza(Integer.parseInt(des.getText().toString()));
        ficha.setConstitucion(Integer.parseInt(con.getText().toString()));
        ficha.setInteligencia(Integer.parseInt(inti.getText().toString()));
        ficha.setSabiduria(Integer.parseInt(sab.getText().toString()));
        ficha.setCarisma(Integer.parseInt(car.getText().toString()));

        ficha.setClaseArmadura(armadura.getClaseArmadura());
        ficha.setRaza(razaPersonaje);
        ficha.setClase(clasePersonaje);
        ficha.setEquipamiento(equipamientoPersonaje);
        ficha.setHistoria(historiaPersonaje);

        Intent intentResultado = new Intent();
        intentResultado.putExtra(FICHA_BUNDLE, ficha);
        setResult(Activity.RESULT_OK, intentResultado);
        finish();

    }

    private void iniciaListas() {
        listaClases = new ArrayList<>(controlador.getListaClases());
        listaRazas = new ArrayList<>(controlador.getListaRazas());
        listaTrasfondos = new ArrayList<>(controlador.getListaTrasfondos());
    }

    private void iniciaActividad() {
        nombre = binding.campoNombre;
        spinerClase = binding.spinnerClase;
        spinerRaza = binding.spinnerRaza;
        spinnerEspecialidad = binding.spinnerEspec;
        spinnerTrasfondo = binding.spinnerTrasf;
        spinnerAlineamiento = binding.spinerAlineamiento;
        spinnerArmas = binding.spinnerArma;
        spinnerArmaduras = binding.spinnerArmadura;

        maxEdad = binding.edadMaxima;
        minMaxTamanio = binding.tamanioMinMax;
        tamanio = binding.campoTamanio;
        edad = binding.campoEdad;

        fue = binding.campoFuerza;
        des = binding.campoDestreza;
        con = binding.campoCons;
        inti = binding.campoInte;
        sab = binding.campoSab;
        car = binding.campoCar;

        nIntentos = 3;

        /* Cosas de raza */
        ArrayAdapter<Object> adapterRaza = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaRazas.toArray());
        spinerRaza.setAdapter(adapterRaza);

        /* Cosas de clses */
        ArrayAdapter<Object> adapterClase = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaClases.toArray());
        spinerClase.setAdapter(adapterClase);

        /* Cosas de especialidad */
        ArrayAdapter<Object> adapterEspec = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new ArrayList<>());
        spinnerEspecialidad.setAdapter(adapterEspec);

        /* Cosas de trasfondos */
        ArrayAdapter<Object> adapterTrans = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaTrasfondos.toArray());
        spinnerTrasfondo.setAdapter(adapterTrans);

        /* Cosas de armas y armaduras */
        ArrayAdapter<Object> adapterArma = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new ArrayList<>());
        spinnerArmas.setAdapter(adapterArma);

        ArrayAdapter<Object> adapterArmadura = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new ArrayList<>());
        spinnerArmaduras.setAdapter(adapterArmadura);

        /* Cosas de alineamiento */
        Alineamientos[] alineamientos = Alineamientos.values();
        String[] alineamientosStrings = new String[alineamientos.length];

        for (int i = 0; i < alineamientos.length; i++) {
            alineamientosStrings[i] = alineamientos[i].toString().replace("_", " ");
        }

        ArrayAdapter<Object> adapterAlineamiento = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alineamientosStrings);
        spinnerAlineamiento.setAdapter(adapterAlineamiento);

        /* Eventos */

        spinerClase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                Clase clase = (Clase) adapterView.getItemAtPosition(posicion);
                adapterEspec.clear();
                adapterArma.clear();
                adapterArmadura.clear();

                adapterEspec.addAll(clase.getEspecialidades());
                adapterArma.addAll(clase.getArmas());
                adapterArmadura.addAll(clase.getArmadurasSinEscudo());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinerRaza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                Raza raza = (Raza) adapterView.getItemAtPosition(posicion);
                String edadMax = String.format("Max %d", raza.getEdadMaxima());
                String rangoTamanio = String.format("Min %.2f / Max %.2f", raza.getAlturaMinima(), raza.getAlturaMaxima());

                maxEdad.setText(edadMax);
                minMaxTamanio.setText(rangoTamanio);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}