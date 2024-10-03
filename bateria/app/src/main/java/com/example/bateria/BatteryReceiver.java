package com.example.bateria;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

public class BatteryReceiver extends BroadcastReceiver {
    private MainActivity mainActivity;

    public BatteryReceiver(MainActivity activity) {
        this.mainActivity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Obtener el nivel de batería
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        int batteryLevel = (int) ((level / (float) scale) * 100); // Calcular porcentaje

        // Actualizar la interfaz de usuario
        mainActivity.updateBatteryLevel(batteryLevel);

        // Actualizar el mensaje de advertencia si el nivel es bajo
        if (batteryLevel < 15) {
            mainActivity.showBatteryWarning("¡Batería baja!");
        } else {
            mainActivity.showBatteryWarning("");
        }
    }
}

