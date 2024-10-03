    package com.example.bateria;

    import android.content.BroadcastReceiver;
    import android.content.Context;
    import android.content.Intent;
    import android.content.IntentFilter;
    import android.os.Bundle;
    import android.util.Log;
    import android.widget.TextView;
    import androidx.appcompat.app.AppCompatActivity;

    public class MainActivity extends AppCompatActivity {
        private BatteryReceiver batteryReceiver;
        private TextView batteryLevelText;
        private TextView batteryWarningText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            batteryLevelText = findViewById(R.id.battery_level_text);
            batteryWarningText = findViewById(R.id.battery_warning_text);

            batteryReceiver = new BatteryReceiver(this);
        }

        @Override
        protected void onResume() {
            super.onResume();
            IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            registerReceiver(batteryReceiver, filter);
            Log.d("MainActivity", "BatteryReceiver REGISTRADO.");
        }

        @Override
        protected void onPause() {
            super.onPause();
            unregisterReceiver(batteryReceiver);
            Log.d("MainActivity", "BatteryReceiver DESREGISTRADO.");
        }

        public void updateBatteryLevel(int level) {
            batteryLevelText.setText(level + "%"); // Actualiza el porcentaje
        }

        public void showBatteryWarning(String message) {
            batteryWarningText.setText(message); // Muestra mensaje de advertencia
        }
    }
