package com.example.a2dam.ad_actividad_4a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnIntroducirEstudiante= (Button) findViewById(R.id.btn_introducir_estudiante);
        final Button btnIntroducirProfesor= (Button) findViewById(R.id.btn_introducir_profesor);
        final Button btnEliminarDB= (Button) findViewById(R.id.btn_eliminar_DB);
        final Button btnConsultarBD= (Button) findViewById(R.id.btn_consultar_BD);
        final Button btnConsultarLetra= (Button) findViewById(R.id.btn_consultar_por_letra);
        btnIntroducirEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Introducir estudiante
                Intent i= new Intent(getApplicationContext(),IntrEstudiantActivity.class);
                startActivity(i);
            }
        });
        btnIntroducirProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Introducir profesor
                Intent i= new Intent(getApplicationContext(),IntrProfesorActivity.class);
                startActivity(i);
            }
        });
        btnEliminarDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Eliminar base de datos
                MyDBAdapter dbAdapter;
                dbAdapter = new MyDBAdapter(getApplicationContext());
                dbAdapter.open();
                dbAdapter.eliminarDB();
            }
        });
        btnConsultarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Consultar la base de datos
                Intent i= new Intent(getApplicationContext(),ConsultasActivity.class);
                startActivity(i);
            }
        });
        btnConsultarLetra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Consultar la base de datos por letra
                Intent i= new Intent(getApplicationContext(),ConsultarPorLetraActivity.class);
                startActivity(i);
            }
        });
    }
}
