package com.jimenez.jdavid.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tPorcentajes, porcentaje, textProy, textExpo, textPrac;
    EditText eExpo, ePrac, eProy, eNota;
    String porcExpo="15", porcPrac="50", porcProy="35";
    Button bCalc, bLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textExpo=(TextView)findViewById(R.id.textExpo);
        textProy=(TextView)findViewById(R.id.textProy);
        textPrac=(TextView)findViewById(R.id.textPrac);
        porcentaje=(TextView)findViewById(R.id.porcentaje);
        tPorcentajes=(TextView)findViewById(R.id.tPorcentajes);

        eExpo = (EditText)findViewById(R.id.eExpo);
        ePrac=(EditText)findViewById(R.id.ePrac);
        eProy=(EditText)findViewById(R.id.eProy);
        eNota=(EditText)findViewById(R.id.eNota);

        bCalc=(Button)findViewById(R.id.bCalcular);
        bLimpiar=(Button)findViewById(R.id.bLimpiar);

        ePrac.setText(String.valueOf(0.0));
        eProy.setText(String.valueOf(0.0));
        eExpo.setText(String.valueOf(0.0));

        tPorcentajes.setText(textExpo.getText() + "=" + porcExpo + "%. " + textPrac.getText() +"=" + porcPrac + "%. " +textProy.getText()+"=" + porcProy + "%.");
        //tPorcentajes.setText("Porcentajes: Exposiciones=" + porcExpo + "%. Prácticas=" + porcPrac + "%. Proyecto=" + porcProy + "%.");

        bLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ePrac.setText(String.valueOf(0.0));
                eProy.setText(String.valueOf(0.0));
                eExpo.setText(String.valueOf(0.0));
                eNota.setText(String.valueOf(0.0));
            }
        });


        bCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double nota, validar;

                validar = Double.valueOf(eExpo.getText().toString());

                if (validar>5.0) {
                    Toast.makeText(MainActivity.this, "Debe ingresar un valor entre 0 y 5 en la nota de Exposiciones", Toast.LENGTH_SHORT).show();
                    return;
                }

                validar = Double.valueOf(ePrac.getText().toString());

                if (validar>5.0) {
                    Toast.makeText(MainActivity.this, "Debe ingresar un valor entre 0 y 5 en la nota de Prácticas", Toast.LENGTH_SHORT).show();
                    return;
                }

                validar = Double.valueOf(eProy.getText().toString());

                if (validar>5.0) {
                    Toast.makeText(MainActivity.this, "Debe ingresar un valor entre 0 y 5 en la nota de Proyectos", Toast.LENGTH_SHORT).show();
                    return;
                }

                nota = Double.parseDouble(eExpo.getText().toString())*Double.valueOf(porcExpo.toString())/100 +
                        Double.parseDouble(ePrac.getText().toString())*Double.valueOf(porcPrac.toString())/100 +
                        Double.parseDouble(eProy.getText().toString())*Double.valueOf(porcProy.toString())/100;



                eNota.setText(String.valueOf(String.format("%.1f", nota)));

                }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.mConfigurar){
            //Toast.makeText(this, "Presionó configurar", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SettingsActivity.class);

            intent.putExtra("porcExpo", Double.valueOf(porcExpo.toString()));
            intent.putExtra("porcPrac", Double.valueOf(porcPrac.toString()));
            intent.putExtra("porcProy", Double.valueOf(porcProy.toString()));
            startActivityForResult(intent, 1234);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==1234 && resultCode==RESULT_OK){
            porcExpo = data.getExtras().getString("npExpo");
            porcProy = data.getExtras().getString("npProy");
            porcPrac = data.getExtras().getString("npPrac");

            tPorcentajes.setText(textExpo.getText() + "=" + porcExpo + "%. " + textPrac.getText() +"=" + porcPrac + "%. " +textProy.getText()+"=" + porcProy + "%.");
            //+": " + textExpo.getText() + "=" + porcExpo + "%. " + textPrac.getText() +"=" + porcPrac + "%. " +textProy.getText()+"=" + porcProy + "%."
        }

        super.onActivityResult(requestCode, resultCode, data);

    }
}
