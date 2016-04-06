package com.jimenez.jdavid.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.transform.Source;

public class SettingsActivity extends AppCompatActivity {

    TextView mensaje;
    EditText seExpo, sePrac, seProy;
    Button sbguardar, sbLimpiar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mensaje=(TextView)findViewById(R.id.mensaje);
        seExpo=(EditText)findViewById(R.id.seExpo);
        sePrac=(EditText)findViewById(R.id.sePrac);
        seProy=(EditText)findViewById(R.id.seProy);
        sbguardar =(Button) findViewById(R.id.sbGuardar);
        sbLimpiar = (Button)findViewById(R.id.sbLimpiar);

        Bundle extras = getIntent().getExtras();

        seExpo.setText(String.valueOf(extras.getDouble("porcExpo")));
        sePrac.setText(String.valueOf(extras.getDouble("porcPrac")));
        seProy.setText(String.valueOf(extras.getDouble("porcProy")));

        sbLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sePrac.setText(String.valueOf(50.0));
                seProy.setText(String.valueOf(35.0));
                seExpo.setText(String.valueOf(15.0));
                Toast.makeText(SettingsActivity.this, mensaje.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        sbguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double suma;

                suma=Double.parseDouble(seExpo.getText().toString())+Double.parseDouble(sePrac.getText().toString())+ Double.parseDouble(seProy.getText().toString());

                if (suma==100) {

                    Intent intent = new Intent();
                    intent.putExtra("npExpo", seExpo.getText().toString());
                    intent.putExtra("npPrac", sePrac.getText().toString());
                    intent.putExtra("npProy", seProy.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else{
                    Toast.makeText(SettingsActivity.this, "Los porcentajes deben sumar 100", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        });

    }



}
