package com.ramalho.calculadora_imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etPeso = findViewById(R.id.etPeso);
        final EditText etAltura = findViewById(R.id.etAltura);

        Button btnCalcular = findViewById(R.id.btnCalcular);
        Button btnLimpar = findViewById(R.id.btnLimpar);

        final TextView tvResp = findViewById(R.id.tvResp);

        //Botão para calcular
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double imc = calcIMC(Double.parseDouble(etPeso.getText().toString()), Double.parseDouble(etAltura.getText().toString()));
                    imcString(imc, tvResp);
                } catch (Exception e) {
                    Log.d("Erro:", e.toString());
                    tvResp.setText(getText(R.string.erro));
                }
                hideKeyBoard();
            }

            private void imcString(double calc, TextView tv) {
                DecimalFormat df = new DecimalFormat("#.00");
                String resp = "IMC: "+df.format(calc)+"\n";
                if(calc < 17){
                    resp+= getText(R.string.resposta01);
                    tv.setTextColor(getResources().getColor(R.color.color1));
                }
                else if(calc >= 17 && calc < 18.5){
                    resp+= getText(R.string.resposta02);
                    tv.setTextColor(getResources().getColor(R.color.color2));
                }
                else if(calc >= 18.5 && calc < 25){
                    resp+= getText(R.string.resposta03);
                    tv.setTextColor(getResources().getColor(R.color.color3));
                }
                else if(calc >= 25 && calc < 30){
                    resp+= getText(R.string.resposta04);
                    tv.setTextColor(getResources().getColor(R.color.color4));
                }
                else if(calc >= 30 && calc < 35){
                    resp+= getText(R.string.resposta05);
                    tv.setTextColor(getResources().getColor(R.color.color5));
                }
                else if(calc >= 35 && calc < 40){
                    resp+= getText(R.string.resposta06);
                    tv.setTextColor(getResources().getColor(R.color.color6));
                }
                else {
                    resp+= getText(R.string.resposta07);
                    tv.setTextColor(getResources().getColor(R.color.color7));
                }
                tv.setText(resp);
            }
        });

        //Botão limpar
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPeso.setText("");
                etAltura.setText("");
                tvResp.setText("");
            }
        });
    }

    public void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private double calcIMC(double peso, double altura) {
        double resp = peso / (altura * altura);
        return resp;
    }
};