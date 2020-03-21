package pl.mkonkel.wsb.firebasepush

import android.app.Application
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId

class App : Application() {
    override fun onCreate() {
        super.onCreate()

// TODO: Add FirebaseInstanceId Callback here
//        It is goot to log or print the token somewhere - it will be needed later.
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    Log.e("App", "Nie udało się uzyskać FCM Token")
                }

                val token = it.result?.token
                Log.d("App", "FCM Token: $token")
            }
    }
}