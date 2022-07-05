package com.rabbitimpulsorademercados.gestionservicios;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;

public class ServiceLock extends Service {





    public ServiceLock() {
        //private Button lock, disable, enable;

         DevicePolicyManager devicePolicyManager;
         ActivityManager activityManager;
        ComponentName compName;
        devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        compName = new ComponentName(this, mensaje.class);

        boolean isActive = devicePolicyManager.isAdminActive(compName);

        boolean active = devicePolicyManager.isAdminActive(compName);

        if (active) {
            //Se bloquea el equipo
            devicePolicyManager.lockNow();
        } else {
            //Sino tiene activado los permisos de administrador los requiere.
            //Toast.makeText(this, "You need to enable the Admin Device Features", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Para poder utilizar esta aplicacion se requiere tener acceso como administror del dispisitivo.");
            startActivity(intent);
        }

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



}