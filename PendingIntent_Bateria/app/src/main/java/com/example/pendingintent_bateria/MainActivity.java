package com.example.pendingintent_bateria;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private BatteryReceiver batteryReceiver;
    private TextView batteryStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el TextView para mostrar el nivel de la batería
        batteryStatusTextView = findViewById(R.id.batteryStatusTextView);

        // Crear una instancia del BroadcastReceiver
        batteryReceiver = new BatteryReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Crear un Intent para recibir actualizaciones del estado de la batería
        Intent batteryStatusIntent = new Intent(Intent.ACTION_BATTERY_CHANGED);

        // Crear un PendingIntent que se disparará cuando el BroadcastReceiver reciba un mensaje
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                batteryStatusIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        // Registrar el BroadcastReceiver dinámicamente
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, intentFilter);

        // Llamar al PendingIntent
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }

        // Mostrar en consola que se registró el receptor
        Log.i("MainActivity", "BroadcastReceiver registrado satisfactoriamente.");
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Desregistrar el BroadcastReceiver cuando la actividad no esté en primer plano
        unregisterReceiver(batteryReceiver);
        Log.i("MainActivity", "BroadcastReceiver desregistrado satisfactoriamente.");
    }

    // Método para actualizar la interfaz de usuario con el nivel de batería
    public void updateBatteryLevel(float batteryLevel) {
        batteryStatusTextView.setText("Nivel de batería: " + batteryLevel + "%");
    }
}


