package com.miguelnohuc.geoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TrampaActivity extends AppCompatActivity {
    public static String EXTRA_RESPUESTA_ES_CORRECTA ="com.miguelnohuc.geoapp.respuesta_es_correcta";
    public static String EXTRA_SE_MOSTRO_RESPUESTA = "com.miguelnohuc.geoapp.se_mostro_respuesta";
    private Button mBotonMostrarRespuesta;
    private TextView mRespuestaTextView;

    public static boolean respuestaMostrada (Intent result ){
        return result.getBooleanExtra(EXTRA_SE_MOSTRO_RESPUESTA, false);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trampa);

        mRespuestaTextView = findViewById(R.id.respuesta_textview);
        mBotonMostrarRespuesta = findViewById(R.id.mostrar_respuesta);

        mBotonMostrarRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                boolean respuesEsCorrecta = intent.getBooleanExtra(EXTRA_RESPUESTA_ES_CORRECTA,false );
                if (respuesEsCorrecta){
                    mRespuestaTextView.setText("Cierto");

                }
                else {
                    mRespuestaTextView.setText("Falso");
                }
                setSeMOSTRORESPUESTAResult(true);

            }
        });
    }
    private void setSeMOSTRORESPUESTAResult (boolean seMostroRespuesta){
        Intent datos = new Intent();
        datos.putExtra(EXTRA_SE_MOSTRO_RESPUESTA, seMostroRespuesta);
        setResult(RESULT_OK,datos);
    }
}