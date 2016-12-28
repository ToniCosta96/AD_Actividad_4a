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
        btnIntroducirEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),IntrEstudiantActivity.class);
                startActivity(i);
            }
        });
        btnIntroducirProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),IntrProfesorActivity.class);
                startActivity(i);
            }
        });
        btnEliminarDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBAdapter dbAdapter;
                dbAdapter = new MyDBAdapter(getApplicationContext());
                dbAdapter.open();
                dbAdapter.eliminarDB();
            }
        });
    }
}
