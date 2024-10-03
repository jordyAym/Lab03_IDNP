package com.example.samplebroadcastemisor;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Emisor";
    public static final String EXTRA_MOON_PHASE = "org.idnp.sampleintentbroadcastreceiver.MoonBroadcastReceiver.EXTRA_MOON_PHASE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSenMessage = findViewById(R.id.btnSenMessage);
        EditText edtMessage = findViewById(R.id.edtMessage);

        btnSenMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message =  edtMessage.getText().toString();
                sendMessage(message);
            }
        });
    }
    public void sendMessage(String message){

        Intent intent = new Intent();
        //Se define el compoente a donde se desea enviar
        intent.setComponent(new ComponentName("org.idnp.samplebroadcastreceptor", "org.idnp.samplebroadcastreceptor.Moon"));

        //Se establece la accion y se envia el mensaje
        intent.setAction(EXTRA_MOON_PHASE);
        intent.putExtra(EXTRA_MOON_PHASE, message);

        //Se envia el intent
        sendBroadcast(intent);
        Log.d(TAG, "Mensaje enviado");
    }
}
//Listeners para obtenern lo datos de l broadcast
