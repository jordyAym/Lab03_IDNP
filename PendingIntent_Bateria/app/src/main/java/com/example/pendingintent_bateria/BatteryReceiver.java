package com.example.pendingintent_bateria;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

public class BatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Obtener el nivel de la batería
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level / (float) scale * 100;

        // Mostrar información en Logcat
        Log.i("BatteryReceiver", "Nivel de batería: " + batteryPct + "%");

        // Actualizar la interfaz de usuario (esto se haría desde la actividad)
        if (context instanceof MainActivity) {
            ((MainActivity) context).updateBatteryLevel(batteryPct);
        }
    }
}
