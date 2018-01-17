package com.example.manolo.reconocimientovozescritura;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;
    EditText etTextoGrabado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTextoGrabado =(EditText)findViewById(R.id.etTextoGrabado);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case RECOGNIZE_SPEECH_ACTIVITY:
                if(resultCode==RESULT_OK && data != null){
                    ArrayList<String> speech=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String strSpeechParaEscribir=speech.get(0);

                    etTextoGrabado.setText(strSpeechParaEscribir);
                }
                break;
            default:
                break;

        }
    }


    public void hablar(View v){
        Intent intentActionRecognizeSpeech = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //Pasamos al intent como parametro el idioma y la region
        intentActionRecognizeSpeech.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "values-es-rES");
        try{
            startActivityForResult(intentActionRecognizeSpeech, RECOGNIZE_SPEECH_ACTIVITY);
        }
        catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "Tu dispositivo no soporta reconocimiento de voz", Toast.LENGTH_LONG).show();
        }

    }

}
