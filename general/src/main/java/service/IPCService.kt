package service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.hardik.playground.IAIDLInterface
import android.os.Process
import android.text.TextUtils
import service.data.Client
import service.data.RecentClient

class IPCService : Service() {
    private var mConnectionCount: Int = 0
    val NOT_SENT = "Not sent!"


    override fun onBind(intent: Intent?): IBinder {
        mConnectionCount++
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        RecentClient.client = null
        return super.onUnbind(intent)
    }

    private val binder = object : IAIDLInterface.Stub(){
        override fun getPid(): Int {
            return Process.myPid()
        }

        override fun getConnectionCount(): Int {
            return mConnectionCount
        }

        override fun setDisplayedValue(packageName: String?, pid: Int, data: String?) {
            val clientData =
                if (data == null || TextUtils.isEmpty(data)) NOT_SENT
                else data

            RecentClient.client = Client(
                packageName ?: NOT_SENT,
                pid.toString(),
                clientData,
                "AIDL"
            )
        }

    }
}