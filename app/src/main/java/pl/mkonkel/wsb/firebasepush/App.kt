package pl.mkonkel.wsb.firebasepush

import android.app.Application
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener{
                if (!it.isSuccessful) {
                    Log.e("A", "Nie udało sie uzyskać FCM Token")
                }
                val token:String? = it.result?.token
                Log.d("App", "FCM TOKEN: $token")
            }

    }
}