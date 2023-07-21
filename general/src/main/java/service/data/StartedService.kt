package service.data

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class StartedService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return  null
    }

    open class ServiceBinder : Binder() {

    }
}