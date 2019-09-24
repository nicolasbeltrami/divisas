package com.example.practica_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String[] divisas = new String[] { "CLP", "USD", "EUR", "AUD", "CAD" };

    private Spinner monedaActualSP;
    private Spinner monedaCambioSP;
    private EditText valorCambioET;
    private TextView resultadoTV;


    final private double factorPesoDolar = 0.0013;
    final private double factorPesoEuro = 0.0012;
    final private double factorPesoAustralia = 0.0020;
    final private double factorPesoCanada = 0.0018;

    final private double factorDolarPeso = 725.79;
    final private double factorDolarEuro = 0.90;
    final private double factorDolarAustralia = 1.48;
    final private double factorDolarCanada = 1.33;

    final private double factorEuroPeso = 804.30;
    final private double factorEuroDolar = 1.10;
    final private double factorEuroAustralia = 1.64;
    final private double factorEuroCanada = 1.47;

    final private double factorAustraliPeso = 489.55;
    final private double factorAustraliaEuro = 0.60;
    final private double factorAustraliaDolar = 0.67;
    final private double factorAustraliaCanada = 0.89;

    final private double factorCanadaPeso = 545.53;
    final private double factorCanadaEuro = 0.67;
    final private double factorCanadaAustralia = 1.11;
    final private double factorCanadaDolar = 0.75;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,divisas);

        monedaActualSP = findViewById(R.id.moneda_actual);

        monedaActualSP.setAdapter(adapter);
    }



    public void clickConvertir(View v) {
        monedaActualSP = findViewById(R.id.moneda_actual);
        monedaCambioSP = findViewById(R.id.moneda_cambio);
        valorCambioET = findViewById(R.id.monto_cambio);
        resultadoTV = findViewById(R.id.resultado_cambio);

        String monedaActual = monedaActualSP.getSelectedItem().toString();
        String monedaCambio = monedaCambioSP.getSelectedItem().toString();

        double valorCambio = Double.parseDouble(valorCambioET.getText().toString());

        double resultado = procesarConverson(monedaActual, monedaCambio, valorCambio);

        if (resultado > 0) {
            resultadoTV.setText(String.format("Por %5.2f %s, usted recibirá %5.2f %s",valorCambio,monedaActual,resultado,monedaCambio));
            valorCambioET.setText("");

            SharedPreferences preferences = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        } else {
            resultadoTV.setText(String.format(""));
            Toast.makeText(this, "Debe elgir una moneda a cambiar", Toast.LENGTH_LONG).show();
        }


    }

    private double procesarConverson(String monedaActual, String monedaCambio, double valorCambio){

        double resultadoConversion = 0;

        switch (monedaActual){
            case "CLP":
                if (monedaCambio.equals("USD"))
                    resultadoConversion = valorCambio * factorPesoDolar;

                if (monedaCambio.equals("EUR"))
                    resultadoConversion = valorCambio * factorPesoEuro;
                if (monedaCambio.equals("AUD"))
                    resultadoConversion = valorCambio * factorPesoAustralia;
                if (monedaCambio.equals("CAD"))
                    resultadoConversion = valorCambio * factorPesoCanada;
                break;
            case "EUR":
                if (monedaCambio.equals("USD"))
                    resultadoConversion = valorCambio * factorEuroDolar;
                if (monedaCambio.equals("CLP"))
                    resultadoConversion = valorCambio * factorEuroPeso;
                if (monedaCambio.equals("AUD"))
                    resultadoConversion = valorCambio * factorEuroAustralia;
                if (monedaCambio.equals("CAD"))
                    resultadoConversion = valorCambio * factorEuroCanada;
                break;

            case "USD":
                if (monedaCambio.equals("CLP"))
                    resultadoConversion = valorCambio *factorDolarPeso;
                if (monedaCambio.equals("EUR"))
                    resultadoConversion = valorCambio * factorDolarEuro;
                if (monedaCambio.equals("AUD"))
                    resultadoConversion = valorCambio * factorDolarAustralia;
                if (monedaCambio.equals("CAD"))
                    resultadoConversion = valorCambio * factorDolarCanada;
                break;
            case "AUD":
                if (monedaCambio.equals("CLP"))
                    resultadoConversion = valorCambio *factorAustraliPeso;
                if (monedaCambio.equals("EUR"))
                    resultadoConversion = valorCambio * factorAustraliaEuro;
                if (monedaCambio.equals("USD"))
                    resultadoConversion = valorCambio * factorAustraliaDolar;
                if (monedaCambio.equals("CAD"))
                    resultadoConversion = valorCambio * factorAustraliaCanada;
                break;
            case "CAD":
                if (monedaCambio.equals("CLP"))
                    resultadoConversion = valorCambio *factorCanadaPeso;
                if (monedaCambio.equals("EUR"))
                    resultadoConversion = valorCambio * factorCanadaEuro;
                if (monedaCambio.equals("AUD"))
                    resultadoConversion = valorCambio * factorCanadaAustralia;
                if (monedaCambio.equals("USD"))
                    resultadoConversion = valorCambio * factorCanadaDolar;
                break;
        }
        return resultadoConversion;
    }
}
