package com.rabbitimpulsorademercados.gestionservicios;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServiceLock extends Service {
    public ServiceLock() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}