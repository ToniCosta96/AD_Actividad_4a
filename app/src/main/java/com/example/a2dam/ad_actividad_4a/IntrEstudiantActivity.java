package com.example.a2dam.ad_actividad_4a;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class IntrEstudiantActivity extends AppCompatActivity {
    private MyDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intr_estudiant);

        final EditText editTextNom= (EditText) findViewById(R.id.editTextNom);
        final EditText editTextEdat= (EditText) findViewById(R.id.editTextEdad);
        final Spinner spinnerCicleCurs= (Spinner) findViewById(R.id.spinnerCicloCurso);
        final EditText editTextNotaMedia= (EditText) findViewById(R.id.editTextNotaMedia);
        final EditText editTextEliminar= (EditText) findViewById(R.id.editTextEliminarEstudiante);
        final Button btnGuardar= (Button) findViewById(R.id.buttonInsertarEstudiante);
        final Button btnEliminar= (Button) findViewById(R.id.buttonEliminarEstudiante);

        dbAdapter = new MyDBAdapter(getApplicationContext());
        dbAdapter.open();

        ArrayAdapter<String> adaptadorCurs = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.ciclo_y_curso));
        spinnerCicleCurs.setAdapter(adaptadorCurs);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Guardar
                String nom=editTextNom.getText().toString();
                int edat;
                String cicleCurs=spinnerCicleCurs.getSelectedItem().toString();
                int notaMedia;
                try {
                    //Pasar String a int
                    edat=Integer.parseInt(editTextEdat.getText().toString());
                    notaMedia=Integer.parseInt(editTextNotaMedia.getText().toString());
                    dbAdapter.insertarEstudiante(nom,edat,cicleCurs,notaMedia);
                }catch (NumberFormatException nfe){
                    Toast.makeText(getApplicationContext(),"Faltan datos por introducir",Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Eliminar
                String id=editTextEliminar.getText().toString();
                try{
                    Integer.valueOf(id);
                    dbAdapter.eliminarEstudiante(id);
                }catch(NumberFormatException nfe){
                    Toast.makeText(getApplicationContext(),"La ID "+id+" es incorrecta",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
