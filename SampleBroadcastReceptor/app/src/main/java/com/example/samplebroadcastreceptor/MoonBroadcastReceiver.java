package com.example.samplebroadcastreceptor;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.LongDef;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MoonBroadcastReceiver extends BroadcastReceiver {
    public static final String EXTRA_MOON_PHASE = "org.idnp.sampleintentbroadcastreceiver.MoonBroadcastReceiver.EXTRA_MOON_PHASE";
    public static final String TAG = "MoonBroadcastReceiver";


    @Override
    public void onReceive(Context context, Intent intent){
        String message = intent.getStringExtra(EXTRA_MOON_PHASE);
        writeToFile(message, context);
        Log.d(TAG, message);
    }

    private void writeToFile(String data, Context context) {
        try {
            // Abre (o crea) un archivo llamado "config.txt" en modo de añadir texto (MODE_APPEND)
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_APPEND));

            // Escribe el mensaje en el archivo
            outputStreamWriter.write(data);
            outputStreamWriter.write("\n"); // Escribir un salto de línea después del mensaje

            // Cierra el flujo de escritura
            outputStreamWriter.close();

            Log.d(TAG, "Escrito en el archivo: " + data);

        } catch (IOException e) {
            // En caso de que ocurra un error de entrada/salida (I/O), registramos el error en los logs
            Log.e(TAG, "Error al escribir en el archivo: " + e.toString());
        }
    }
}
