package com.hyperion.dndapiapp.utilidades;

import static com.hyperion.dndapiapp.utilidades.Constantes.DELIMITER_STRING;

import android.content.Context;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;

public class Utils {

    private final static String EXPRESION_CONTRASENIA = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
    private final static String EXPRESION_CORREO = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public final static String FLAG_HECHIZO = "*";

    public static boolean verifcaContrasenia(String pass) {
        return pass.matches(EXPRESION_CONTRASENIA);
    }

    public static boolean verificaCorreo(String correo) {
        return correo.matches(EXPRESION_CORREO);
    }

    public static List<GetNombreInterface> ordenaListaPorNombre(List<GetNombreInterface> listaRecibida) {
        List<GetNombreInterface> listaOrdenada = new ArrayList<>(listaRecibida);
        listaOrdenada.sort((ordenablePorNombre, t1) -> ordenablePorNombre.getNombre().compareToIgnoreCase(t1.getNombre()));
        return listaOrdenada;
    }

    public static int[] tiraDados() {
        int[] dados = new int[6];
        int min = 1;
        int max = 6;

        for (int i = 0; i < dados.length; i++) {
            int[] nRandoms = new int[4];
            int resultado = 0;

            for (int j = 0; j < nRandoms.length; j++) {
                nRandoms[j] = (int) (Math.random() * ((max - min) + 1)) + min;
            }

            Arrays.sort(nRandoms);
            resultado += nRandoms[3];
            resultado += nRandoms[2];
            resultado += nRandoms[1];
            dados[i] = resultado;
        }

        return dados;
    }

    public static String listaToString(List<GetNombreInterface> lista) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            if (i != lista.size() - 1) {
                resultado.append(lista.get(i).getNombre()).append(DELIMITER_STRING);
            } else {
                resultado.append(lista.get(i).getNombre());
            }
        }
        return resultado.toString();
    }

    public static List<String> StringToLista(String cadena) {
        String[] partes = cadena.split(DELIMITER_STRING);
        return Arrays.asList(partes);
    }

    public static List<String> StringToListaHechizos(String cadena) {
        String[] partes = cadena.split(DELIMITER_STRING);
        for (int i = 0; i < partes.length; i++) {
            partes[i] = partes[i] + FLAG_HECHIZO;
        }

        return Arrays.asList(partes);
    }

    public static boolean esNumerico(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int numeroToDP(int numero, Context context) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, numero, context
                        .getResources()
                        .getDisplayMetrics());
    }
}
