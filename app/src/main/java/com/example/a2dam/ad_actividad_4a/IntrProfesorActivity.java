package com.example.a2dam.ad_actividad_4a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class IntrProfesorActivity extends AppCompatActivity {
    private MyDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intr_profesor);

        final EditText editTextNom= (EditText) findViewById(R.id.editTextNomProfesor);
        final EditText editTextEdat= (EditText) findViewById(R.id.editTextEdadProfesor);
        final Spinner spinnerCicleCurs= (Spinner) findViewById(R.id.spinnerCicloCursoProfesor);
        final Spinner spinnerTutor= (Spinner) findViewById(R.id.spinnerTutor);
        final Spinner spinnerDespacho= (Spinner) findViewById(R.id.spinnerDespacho);
        final EditText editTextEliminar= (EditText) findViewById(R.id.editTextEliminarProfesor);
        final Button btnGuardar= (Button) findViewById(R.id.buttonInsertarProfesor);
        final Button btnEliminar= (Button) findViewById(R.id.buttonEliminarProfesor);

        dbAdapter = new MyDBAdapter(getApplicationContext());

        ArrayAdapter<String> adaptadorCurs = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.ciclo_y_curso));
        spinnerCicleCurs.setAdapter(adaptadorCurs);
        ArrayAdapter<String> adaptadorTutor = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.ciclo_y_curso));
        spinnerTutor.setAdapter(adaptadorTutor);
        ArrayAdapter<String> adaptadorDespacho = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.despacho));
        spinnerDespacho.setAdapter(adaptadorDespacho);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Guardar
                dbAdapter.open();
                String nom=editTextNom.getText().toString();
                int edat;
                String cicleCurs=spinnerCicleCurs.getSelectedItem().toString();
                String tutor=spinnerTutor.getSelectedItem().toString();
                String despacho= spinnerDespacho.getSelectedItem().toString();
                try {
                    //Pasar String a int
                    edat=Integer.parseInt(editTextEdat.getText().toString());
                    dbAdapter.insertarProfesor(nom,edat,cicleCurs,tutor,despacho);
                }catch(NumberFormatException nfe){
                    Toast.makeText(getApplicationContext(),"No se ha introducido la edad",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Eliminar
                dbAdapter.open();
                String id=editTextEliminar.getText().toString();
                try{
                    Integer.valueOf(id);
                    dbAdapter.eliminarProfesor(id);
                }catch(NumberFormatException nfe){
                    Toast.makeText(getApplicationContext(),"La ID "+id+" es incorrecta",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
