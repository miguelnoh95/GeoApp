package com.miguelnohuc.geoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelnohuc.geoapp.modelo.BancodePreguntas;
import com.miguelnohuc.geoapp.modelo.Pregunta;

public class GeoActivity extends AppCompatActivity {
    private final String KEY_POSICION_ACTUAL = "posicion actual";
    private final int REQUEST_CODE_SE_MOSTRO_RESPUESTA = 0;
    private Button mBotonCierto;
    private Button mBotonFalso;
    private Button mBotonAnterior;
    private Button mBotonSiguiente;
    private TextView mTextoPregunta;
    private int posicion;
    private BancodePreguntas banco;

    private Pregunta mPreguntaActual;
    private Button mBotonVerRespuesta;
    private boolean mEstaHaciendoTrampa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);


        mTextoPregunta = (TextView) findViewById(R.id.texto_pregunta);
        mBotonCierto = (Button) findViewById(R.id.boton_cierto);
        mBotonFalso = (Button) findViewById(R.id.boton_falso);
        mBotonAnterior = (Button) findViewById(R.id.boton_anterior);
        mBotonSiguiente = (Button) findViewById(R.id.boton_siguiente);
        mBotonVerRespuesta = findViewById(R.id.boton_ver_respuesta);

        crearBancodePreguntas();
        if (savedInstanceState != null) {
            int posicion = savedInstanceState.getInt(KEY_POSICION_ACTUAL);
            mPreguntaActual = banco.get(posicion);
        }



        actualizarPregunta();

        mBotonCierto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta(true);
            }
        });

        mBotonFalso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta(false);
            }
        });

        mBotonAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreguntaActual = banco.previous();
                actualizarPregunta();
            }
        });

        mBotonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreguntaActual = banco.next();
                actualizarPregunta();
            }
        });
        mBotonVerRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        GeoActivity.this, TrampaActivity.class);

                boolean LaRespuestaEsCorrecta = mPreguntaActual.isRespuestaVerdadera();
                intent.putExtra(TrampaActivity.EXTRA_RESPUESTA_ES_CORRECTA,
                        LaRespuestaEsCorrecta);

                //  startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_SE_MOSTRO_RESPUESTA);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_SE_MOSTRO_RESPUESTA) {
            if (data != null) {
                return;
            }
            mEstaHaciendoTrampa = TrampaActivity.respuestaMostrada(data);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        posicion = banco.getPosicionActual();
        outState.putInt(KEY_POSICION_ACTUAL, posicion);
        super.onSaveInstanceState(outState);
    }

    private void crearBancodePreguntas() {
        banco = new BancodePreguntas();
        banco.add(new Pregunta(R.string.texto_pregunta_1, false));
        banco.add(new Pregunta(R.string.texto_pregunta_2, true));
        banco.add(new Pregunta(R.string.texto_pregunta_3, true));
        banco.add(new Pregunta(R.string.texto_pregunta_4, true));
        banco.add(new Pregunta(R.string.texto_pregunta_5, false));
    }

    private void actualizarPregunta() {

        mTextoPregunta.setText(mPreguntaActual.getmIdResTexto());
        mEstaHaciendoTrampa =false;
    }

    private void verificarRespuesta(boolean botonOprimido) {
        if (mEstaHaciendoTrampa) {
            Toast.makeText(GeoActivity.this,
                    R.string.texto_haciendo_trampa,
                    Toast.LENGTH_SHORT)
                    .show();
        } else {

            boolean respuestaEsVerdadera = mPreguntaActual.isVerdadera();
            if (botonOprimido == respuestaEsVerdadera) {
                Toast.makeText(GeoActivity.this,
                        R.string.texto_correcto,
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(GeoActivity.this,
                        R.string.texto_incorrecto,
                        Toast.LENGTH_SHORT)
                        .show();

            }

        }
    }
}