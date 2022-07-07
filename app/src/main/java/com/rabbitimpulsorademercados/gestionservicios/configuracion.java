package com.rabbitimpulsorademercados.gestionservicios;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class configuracion extends AppCompatActivity implements View.OnClickListener{
    private Button lock, continuar, enable;
    public static final int RESULT_ENABLE = 11;
    private DevicePolicyManager devicePolicyManager;
    private ActivityManager activityManager;
    private ComponentName compName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        compName = new ComponentName(this, MyAdmin.class);

        lock = (Button) findViewById(R.id.lock);
        enable = (Button) findViewById(R.id.enableBtn);
        continuar = (Button) findViewById(R.id.btcontinuar);
        lock.setOnClickListener(this);
        enable.setOnClickListener(this);
        continuar.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isActive = devicePolicyManager.isAdminActive(compName);
        continuar.setVisibility(isActive ? View.VISIBLE : View.GONE);
        enable.setVisibility(isActive ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        if (view == lock) {
            boolean active = devicePolicyManager.isAdminActive(compName);

            if (active) {
                devicePolicyManager.lockNow();
            } else {
                Toast.makeText(this, "Se necesita activar la Administración del dispositivo.", Toast.LENGTH_SHORT).show();
            }

        } else if (view == enable) {

            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Additional text explaining why we need this permission");
            startActivity(intent);

        } else if (view == continuar) {
            Intent intent = new Intent(this, mensaje.class);
            startActivity(intent);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case RESULT_ENABLE :
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(configuracion.this, "Se habilito correctamente la administración del dispositivo", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(configuracion.this, "Problem to enable the Admin Device features", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}